package kata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a1b25cd956e2038h";

    public static void main(String[] args) throws Exception {
        ArrayList<String[]> usersAsStringArray = getUsers();
        List<User> users = mapToUser(usersAsStringArray);

        JSONArray usersFromApiAsJson = getUsersFromApi();
        ArrayList<String[]> usersFromApiAsStringArray = parseUsers(usersFromApiAsJson);
        List<User> usersFromApi = mapToUser(usersFromApiAsStringArray);

        List<User> allUsers = new ArrayList<>(users);
        allUsers.addAll(usersFromApi);

        usersAsStringArray.addAll(usersFromApiAsStringArray);

        printHeader();
        printUsers(allUsers);
        System.out.println(
                "*****************************************************************************************************************************************");
        System.out.println(usersAsStringArray.size() + " users in total!");
    }

    private static void printUsers(List<User> list) {
        for (User item : list) {
            System.out.println(
                    String.format("* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
                            item.getId(),
                            item.getCountry(),
                            item.getZip(),
                            item.getName(),
                            item.getBirthday(),
                            item.getEmail()));
        }
    }

    private static List<User> mapToUser(ArrayList<String[]> users) {
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

    static void printHeader() {
        System.out.println(
                "*****************************************************************************************************************************************");
        System.out.println(String.format(
                "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
                "ID",
                "COUNTRY",
                "ZIP",
                "NAME",
                "DOB",
                "EMAIL"));
        System.out.println(
                "*****************************************************************************************************************************************");
    }

    private static ArrayList<String[]> parseUsers(JSONArray externalUsersJson) {
        BigInteger j = new BigInteger("100000000000");
        ArrayList<String[]> externalUsers = new ArrayList<>();
        for (int i = 0; i < externalUsersJson.length(); i++) {
            j = j.add(new BigInteger("1"));
            externalUsers.add(new String[]{
                    j.toString(), // id
                    externalUsersJson.getJSONObject(i).getString("gender"),
                    externalUsersJson.getJSONObject(i).getJSONObject("name").getString("first") + " "
                    + externalUsersJson
                            .getJSONObject(i)
                            .getJSONObject("name")
                            .getString("last"),
                    externalUsersJson.getJSONObject(i).getJSONObject("location").getString("country"),
                    String.valueOf(
                            externalUsersJson.getJSONObject(i).getJSONObject("location").get("postcode")),
                    externalUsersJson.getJSONObject(i).getString("email"),
                    externalUsersJson.getJSONObject(i).getJSONObject("dob").getString("date"),
            });
        }
        return externalUsers;
    }

    private static JSONArray getUsersFromApi() throws IOException {
        // Parse URL content
        String url = USER_URL;
        String command = "curl -X GET " + url;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();
        InputStream processInputStream = process.getInputStream();
        Scanner webProvider = new Scanner(processInputStream);
        String result = "";
        while (webProvider.hasNextLine()) {
            result += webProvider.nextLine();
        }
        webProvider.close();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray results = jsonObject.getJSONArray("results");
        return results;
    }

    private static ArrayList<String[]> getUsers() {
        // Parse CSV file
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("users.csv");
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
        usersAsStringArray.remove(0); // Remove header column

        return usersAsStringArray;
    }
}
