package search;

import lombok.Getter;
import lombok.Setter;

public class Person {

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Created by ipodovinnikov (podovinnii@ae.com) on 4/1/22.
     */

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + email;
    }
}
