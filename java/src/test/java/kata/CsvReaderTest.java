package kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvReaderTest {

    @Test
    void readsAndParsesAllUsersInCsvFile() {
        CsvReader csvReader = new CsvReader("CsvReaderTest.readsAndParsesAllUsersInCsvFile.csv");

        List<User> users = csvReader.getUsers();

        assertEquals(2, users.size());
    }
}
