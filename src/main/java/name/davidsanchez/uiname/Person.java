package name.davidsanchez.uiname;

import java.util.Map;

/**
 * @author David Sanchez
 * @version 1.0.0
 * @since 2015-05-31
 */
public class Person {
    private Map<String, Object> raw;

    public Person(Map<String, Object> raw) {
        this.raw = raw;
    }

    public String getName() {
        return (String) raw.get("name");
    }

    public String getSurname() {
        return (String) raw.get("surname");
    }

    public String getGender() {
        return (String) raw.get("gender");
    }

    public String getCountry() {
        return (String) raw.get("country");
    }
}
