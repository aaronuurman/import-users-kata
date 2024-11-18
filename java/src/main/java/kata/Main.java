package kata;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<User> users = new CsvReader().getUsers();
        List<User> usersFromApi = new ApiClient().getUsers();

        List<User> allUsers = new ArrayList<>(users);
        allUsers.addAll(usersFromApi);

        new CommandLinePrinter().print(allUsers);
    }
}
