import java.util.Calendar;

public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private int yearOfBirth;

    public Person(String id, String firstName, String lastName, String title, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
    /**
     * Returns the full name of the person in the format "firstName lastName"
     * @return the full name of the person
     */
    public String fullName() {
        return firstName + " " + lastName;
    }
    /**
     * Returns the formal name of the person in the format "title firstName lastName"
     * @return the formal name of the person
     */
    public String formalName() {
        return title + " " + fullName();
    }
    /**
     * Returns the age of the person
     * @return the age of the person
     */
    public String getAge() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int age = year - yearOfBirth;
        return Integer.toString(age);
    }
    /**
     * Returns the age of the person in the given year
     * @param year the year to calculate the age for
     * @return the age of the person in the given year
     */
    public String getAge(int year) {
        int age = year - yearOfBirth;
        return Integer.toString(age);
    }
    /**
     * Returns the person's data in CSV format
     * @return the person's data in CSV format
     */
    public String toCSV() {
        return String.format("%s, %s, %s, %s, %4d", id, firstName, lastName, title, yearOfBirth);
    }
    /**
     * Returns the person's data in JSON format
     * @return the person's data in JSON format
     */
    public String toJSON() {
        char DQ = '\u0022';
        return "{" + DQ + "id" + DQ + ":" + DQ + id + DQ + ", " +
                DQ + "firstName" + DQ + ":" + DQ + firstName + DQ + ", " +
                DQ + "lastName" + DQ + ":" + DQ + lastName + DQ + ", " +
                DQ + "title" + DQ + ":" + DQ + title + DQ + ", " +
                DQ + "yearOfBirth" + DQ + ":" + yearOfBirth + "}";
    }
    /**
     * Returns the person's data in XML format
     * @return the person's data in XML format
     */
    public String toXML() {
        return "<person>\n" +
                "  <id>" + id + "</id>\n" +
                "  <firstName>" + firstName + "</firstName>\n" +
                "  <lastName>" + lastName + "</lastName>\n" +
                "  <title>" + title + "</title>\n" +
                "  <yearOfBirth>" + yearOfBirth + "</yearOfBirth>\n" +
                "</person>";
    }
    /**
     * Returns the person's data as a line in a formatted table
     * @return the person's data as a line in a formatted table
     */
    public String formattedData() {
        return String.format("%-8s%-15s%-15s%-6s%6d", id, firstName, lastName, title, yearOfBirth);
    }
}
