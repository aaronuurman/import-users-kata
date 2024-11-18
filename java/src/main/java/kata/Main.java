package kata;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<User> users = CsvReader.getUsers();
        List<User> usersFromApi = ApiClient.getUsers();

        List<User> allUsers = new ArrayList<>(users);
        allUsers.addAll(usersFromApi);

        CommandLinePrinter.SystemOutProcessor(allUsers);
    }
}
