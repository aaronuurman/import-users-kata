package kata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a1b25cd956e2038h";

    public static void main(String[] args) throws Exception {
        ArrayList<String[]> internalUsers = getInternalUsers();
        JSONArray externalUsersJson = getExternalUsers();

        ArrayList<String[]> externalUsers = parseExternalUsers(externalUsersJson);

        /**
         * csv_providers ArrayList<id: number,
         *       email: string
         *       first_name: string
         *       last_name: string>
         */
        internalUsers.addAll(externalUsers); // merge arrays

        // Print users
        printHeader();
        printUsers(internalUsers);
        System.out.println(
                "*****************************************************************************************************************************************");
        System.out.println(internalUsers.size() + " users in total!");
    }

    private static void printUsers(ArrayList<String[]> internalUsers) {
        for (String[] item : internalUsers) {
            System.out.println(String.format(
                    "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
                    item[0],
                    item[3],
                    item[4],
                    item[2],
                    item[6],
                    item[5]));
        }
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

    private static ArrayList<String[]> parseExternalUsers(JSONArray externalUsersJson) {
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

    private static JSONArray getExternalUsers() throws IOException {
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

    private static ArrayList<String[]> getInternalUsers() {
        // Parse CSV file
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("users.csv");
        ArrayList<String[]> csv_providers = new ArrayList<>();
        Scanner csvFile = new Scanner(is);
        while (csvFile.hasNextLine()) {
            String line = csvFile.nextLine();
            // fields: ID, gender, Name ,country, postcode, email, Birthdate
            String[] attributes = line.split(",");
            if (attributes.length == 0) {
                continue;
            }
            csv_providers.add(attributes);
        }
        csv_providers.remove(0); // Remove header column
        return csv_providers;
    }
}
