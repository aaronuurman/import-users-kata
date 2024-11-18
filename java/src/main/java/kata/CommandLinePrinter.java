package kata;

import java.util.List;

class CommandLinePrinter {
    public void print(List<User> allUsers) {
        printHeader();
        printUsers(allUsers);
        printSeparator();
        System.out.println(allUsers.size() + " users in total!");
    }

    static void printHeader() {
        printSeparator();
        System.out.println(String.format(
                "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
                "ID",
                "COUNTRY",
                "ZIP",
                "NAME",
                "DOB",
                "EMAIL"));
        printSeparator();
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

    private static void printSeparator() {
        System.out.println(
                "*****************************************************************************************************************************************");
    }
}
