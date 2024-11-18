package kata;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<User> users = CsvProvider.usersFromCsv();
        List<User> usersFromApi = ApiProvider.usersFromApi();

        List<User> allUsers = new ArrayList<>(users);
        allUsers.addAll(usersFromApi);

        printHeader();
        printUsers(allUsers);
        System.out.println(
                "*****************************************************************************************************************************************");
        System.out.println(allUsers.size() + " users in total!");
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

}
