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

class ApiClient {
    private static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a1b25cd956e2038h";

    static List<User> getUsers() throws IOException {
        return parseJson(getUsersFromApi());
    }

    private static List<User> parseJson(JSONArray externalUsersJson) {
        BigInteger initialId = new BigInteger("100000000000");
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < externalUsersJson.length(); i++) {
            initialId = initialId.add(new BigInteger("1"));
            users.add(new User.Builder()
                    .id(initialId.toString())
                    .name(externalUsersJson.getJSONObject(i).getJSONObject("name").getString("first") + " "
                          + externalUsersJson
                                  .getJSONObject(i)
                                  .getJSONObject("name")
                                  .getString("last"))
                    .country(externalUsersJson.getJSONObject(i).getJSONObject("location").getString("country"))
                    .zip(externalUsersJson.getJSONObject(i).getJSONObject("location").get("postcode") + "")
                    .email(externalUsersJson.getJSONObject(i).getString("email"))
                    .birthday(ZonedDateTime.parse(externalUsersJson.getJSONObject(i).getJSONObject("dob").getString("date")).toLocalDate())
                    .build());
        }
        return users;
    }

    private static JSONArray getUsersFromApi() throws IOException {
        String command = "curl -X GET " + USER_URL;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();
        InputStream processInputStream = process.getInputStream();
        Scanner webProvider = new Scanner(processInputStream);
        StringBuilder result = new StringBuilder();
        while (webProvider.hasNextLine()) {
            result.append(webProvider.nextLine());
        }
        webProvider.close();
        JSONObject jsonObject = new JSONObject(result.toString());
        return jsonObject.getJSONArray("results");
    }
}
