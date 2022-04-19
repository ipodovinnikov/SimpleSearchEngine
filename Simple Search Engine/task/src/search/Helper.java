package search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Helper {

    /**
     * Created by ipodovinnikov (podovinnii@ae.com) on 4/1/22.
     */

    public List<Person> gatherData(String... args) throws IOException {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[1]))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String name = "";
                String email = "";
                if (line.split(" ").length > 2) {
                    name = line.split(" ")[0] + " " + line.split(" ")[1];
                    email = line.split(" ")[2];
                } else if (line.split(" ").length == 2) {
                    name = line.split(" ")[0];
                    email = line.split(" ")[1];
                } else if (line.split(" ").length < 2) {
                    name = line.split(" ")[0];
                }
                personList.add(new Person(name, email));
            }
        }
        return personList;
    }

    public static class Reader {
        public static String read() {
            return new Scanner(System.in).nextLine();
        }
    }
}
