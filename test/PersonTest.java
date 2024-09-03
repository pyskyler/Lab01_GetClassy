import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("000001", "Charlie", "Morningstar", "Princess", 2000);
    }

    @Test
    void fullName() {
        assertEquals("Charlie Morningstar", person.fullName());
    }

    @Test
    void formalName() {
        assertEquals("Princess Charlie Morningstar", person.formalName());
    }

    @Test
    void getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(Integer.toString(currentYear - 2000), person.getAge());
    }

    @Test
    void testGetAge() {
        assertEquals("20", person.getAge(2020));
    }

    @Test
    void toCSV() {
        assertEquals("000001, Charlie, Morningstar, Princess, 2000", person.toCSV());
    }

    @Test
    void toJSON() {
        String expectedJson = "{\"id\":\"000001\", \"firstName\":\"Charlie\", \"lastName\":\"Morningstar\", \"title\":\"Princess\", \"yearOfBirth\":2000}";
        assertEquals(expectedJson, person.toJSON());
    }

    @Test
    void toXML() {
        String expectedXml = """
                <person>
                  <id>000001</id>
                  <firstName>Charlie</firstName>
                  <lastName>Morningstar</lastName>
                  <title>Princess</title>
                  <yearOfBirth>2000</yearOfBirth>
                </person>""";
        assertEquals(expectedXml, person.toXML());
    }
}