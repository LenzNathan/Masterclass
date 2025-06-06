package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jpa.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CalculateLessonPlans {
    private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        List<LessonBegin> lessonBegins = getLessonStartTimes();
        List<Room> rooms = getRooms();
        List<Group> groups = getGroups();
        List<LessonEnd> lessonEnds = getLessonEndTimes();
        List<Subject> subjects = getSubjects();

        assert groups != null;
        assert lessonBegins != null;
        assert rooms != null;
        assert lessonEnds != null;
        assert subjects != null;

        System.out.println("Lesson Begins: " + lessonBegins.size());
        System.out.println("Rooms: " + rooms.size());
        System.out.println("Groups: " + groups.size());
        System.out.println("Lesson Ends: " + lessonEnds.size());
        System.out.println("Subjects: " + subjects.size());

        System.out.println("\nFinished fetching data.");
        System.out.println("Multiplying Data");

        List<Lesson> lessons = new ArrayList<>();

        for (Room r : rooms) { //Räume
            for (int i = 1; i <= 5; i++) {//Wochentage
                for (Subject s : subjects) { //Fächer
                    for (LessonBegin lb : lessonBegins) {//beginn
                        for (LessonEnd le : lessonEnds) {//ende
                            if (lb.getLessonNumber() > le.getLessonNumber()) {
                                System.out.print(pc.prBGRed("."));
                                continue; //skip invalid lessons
                            }
                            if (le.getLessonNumber() - lb.getLessonNumber() > s.getMaxHoursPerDay()) {
                                System.out.print(pc.prBGRed("."));
                                continue; //skip lessons that are too long for the subject
                            }
                            for (Group g : groups) { //for one group / lesson
                                for (Group g2 : groups) {
                                    if (g.getSchulklasse() == g2.getSchulklasse()) {
                                        Lesson lesson = new Lesson();
                                        lesson.setRoom(r);
                                        lesson.setBegin(lb);
                                        lesson.setEnd(le);
                                        lesson.setGroups(List.of(g, g2));//this time we have two groups
                                        lesson.setWeekdayIndex(i);
                                        lesson.setSubject(s);
                                        lessons.add(lesson);
                                        System.out.print(pc.prBGGreen("."));
                                        if (lessons.size() % 500 == 0) {
                                            System.out.println();
                                        }
                                    }
                                } //for two groups / lesson
                                Lesson lesson = new Lesson();
                                lesson.setRoom(r);
                                lesson.setBegin(lb);
                                lesson.setEnd(le);
                                lesson.setGroups(List.of(g));
                                lesson.setWeekdayIndex(i);
                                lesson.setSubject(s);
                                lessons.add(lesson);
                                System.out.print(pc.prBGGreen("."));
                                if (lessons.size() % 500 == 0) {
                                    System.out.println();
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nLessons created: " + lessons.size());

        System.out.println("Finished multiplying data");
        System.out.print("Deleting 'don't care' lessons:");
        List<Lesson> deleteLessons = new ArrayList<>();
        int endBeforeBegin = 0;
        int groupCountTooHigh = 0;
        int multipleClasses = 0;
        int wrongSubjectHours = 0;
        for (Lesson lesson : lessons) {
            if (deleteLessons.size() % 500 == 0) {
                System.out.println();
            }
            if (lesson.getBegin().getLessonNumber() > lesson.getEnd().getLessonNumber()) { //if the lesson ends before it begins
                deleteLessons.add(lesson);
                endBeforeBegin++;
                System.out.print(".");
                continue;
            }
            if (lesson.getGroups().size() > 2) {//not more than 2 groups (practically impossible due to the way the data is multiplied)
                deleteLessons.add(lesson);
                System.out.println("\nRemoved due to group count > 2");
                groupCountTooHigh++;
                //System.out.print(".");
                continue;
            }
            {
                List<Schulklasse> classes = new ArrayList<>();
                for (Group g : lesson.getGroups()) {
                    classes.add(g.getSchulklasse());
                }
                for (Schulklasse s : classes) {
                    if (classes.get(0) != s) {
                        System.out.println("\nRemoved due to different classes");
                        deleteLessons.add(lesson);
                        multipleClasses++;
                        System.out.print(".");
                        continue;
                    }
                }
            }//if there are different classes in the same lesson
            if (lesson.getSubject().getHoursPerWeek() < getDuration(lesson) || lesson.getSubject().getMaxHoursPerDay() < getDuration(lesson)
                    || lesson.getSubject().getMinHoursPerDay() > getDuration(lesson)) { //if the subject has not enough hours per week or too many hours per day
                deleteLessons.add(lesson);
                wrongSubjectHours++;
                System.out.print(".");
                continue;
            }
        }
        lessons.removeAll(deleteLessons);
        System.out.println("\nLessons deleted: " + deleteLessons.size());
        System.out.println("\tendBeforeBegin: " + endBeforeBegin);
        System.out.println("\tgroupCountTooHigh: " + groupCountTooHigh);
        System.out.println("\tmultipleClasses: " + multipleClasses);
        System.out.println("\twrongSubjectHours: " + wrongSubjectHours + "\n");
        System.out.println("Lessons left: " + lessons.size());
        System.out.println();
        System.out.println("Creating LessonPlans");

        System.out.println("testing chooseLesson with Group 1, lesson 1, weekday 1, option 0");
        Lesson l = chooseLesson(lessons, groups.get(0), 1, 1, 0);
        System.out.println("Chosen lesson:" + l.getSubject().getName() + " " +
                l.getBegin().getLessonNumber() + "-" + l.getEnd().getLessonNumber() +
                " in room " + l.getRoom().getBuilding() + l.getRoom().getFloor() + l.getRoom().getNumber());
    }

    public static Lesson chooseLesson(List<Lesson> lessons, Group g, int lessonNumber, int weekdayIndex, int optionIndex) {
        List<Lesson> adequateLessons = lessons;
        adequateLessons.removeIf(l -> l.getWeekdayIndex() != weekdayIndex || l.getBegin().getLessonNumber() != lessonNumber || !l.getGroups().contains(g));
        return adequateLessons.get(optionIndex);
    }

    public static int getDuration(Lesson l) {
        return l.getEnd().getLessonNumber() - l.getBegin().getLessonNumber() + 1;
    }

    public static List<LessonEnd> getLessonEndTimes() {
        return fetchData("/lesson-ends", new TypeReference<List<LessonEnd>>() {
        });
    }

    public static List<LessonBegin> getLessonStartTimes() {
        return fetchData("/lesson-starts", new TypeReference<List<LessonBegin>>() {
        });
    }

    public static List<Room> getRooms() {
        return fetchData("/rooms", new TypeReference<List<Room>>() {
        });
    }

    public static List<Group> getGroups() {
        return fetchData("/groups", new TypeReference<List<Group>>() {
        });
    }

    public static List<Subject> getSubjects() {
        return fetchData("/subjects", new TypeReference<List<Subject>>() {
        });
    }

    private static <T> List<T> fetchData(String endpoint, TypeReference<List<T>> typeReference) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), typeReference);
        } catch (Exception e) {
            System.out.println("Error fetching data from " + endpoint + ": " + e.getMessage());
            return null;
        }
    }
}