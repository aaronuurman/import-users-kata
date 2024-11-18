package kata;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class CsvReader {

    public String name;

    public CsvReader(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return mapToUser(readCsvFile());
    }

    private List<User> mapToUser(List<String[]> users) {
        return users.stream()
                .map(user -> new User.Builder()
                        .id(user[0])
                        .name(user[2])
                        .country(user[3])
                        .zip(user[4])
                        .email(user[5])
                        .birthday(ZonedDateTime.parse(user[6]).toLocalDate())
                        .build())
                .toList();
    }

    private List<String[]> readCsvFile() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        if (is == null) {
            return Collections.emptyList();
        }
        ArrayList<String[]> usersAsStringArray = new ArrayList<>();
        Scanner csvFile = new Scanner(is);
        while (csvFile.hasNextLine()) {
            String line = csvFile.nextLine();
            // fields: ID, gender, Name ,country, postcode, email, Birthdate
            String[] attributes = line.split(",");
            if (attributes.length == 0) {
                continue;
            }
            usersAsStringArray.add(attributes);
        }
        usersAsStringArray.removeFirst(); // Remove header column

        return usersAsStringArray;
    }
}
