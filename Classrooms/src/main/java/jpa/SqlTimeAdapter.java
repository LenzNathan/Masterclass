package jpa;

import jakarta.json.bind.adapter.JsonbAdapter;
import java.sql.Time;
import java.time.LocalTime;

public class SqlTimeAdapter implements JsonbAdapter<Time, String> {

    @Override
    public String adaptToJson(Time obj) {
        return obj.toString();  // Gibt "08:00:00" zurück
    }

    @Override
    public Time adaptFromJson(String obj) {
        return Time.valueOf(LocalTime.parse(obj));  // Erwartet z.B. "08:00:00"
    }
}
