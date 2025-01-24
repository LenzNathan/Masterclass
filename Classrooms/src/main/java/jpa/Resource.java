package jpa;

import java.util.ArrayList;

public interface Resource {
    public static ArrayList listAll() {
        return new ArrayList();
    }

    public void persist();

    public Object findById(int id);

    public void delete(int id);
}
