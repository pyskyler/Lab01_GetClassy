import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;


public class PersonGenerator {

    public static void main(String[] args) {
        ArrayList<String> persons = new ArrayList<>();
        String id;
        String firstName;
        String lastName;
        String title;
        int yearOfBirth;
        String person;

        String filename;

        boolean addAnotherPerson;

        Scanner in = new Scanner(System.in);

        do {
            id = SafeInput.getNonZeroLenString(in, "What is this person's id?");
            firstName = SafeInput.getNonZeroLenString(in, "What is this person's first name?");
            lastName = SafeInput.getNonZeroLenString(in, "What is this person's last name?");
            title = SafeInput.getNonZeroLenString(in, "What is this person's title?");
            yearOfBirth = SafeInput.getInt(in, "What is this person's birth year?");

            person = String.format("%s, %s, %s, %s, %4d", id, firstName, lastName, title, yearOfBirth);
            persons.add(person);

            addAnotherPerson = SafeInput.getYNConfirm(in, "Do you have another person to add?");
        } while (addAnotherPerson);

        filename = SafeInput.getNonZeroLenString(in, "What would you like the file name to be?");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + String.format("/src/%s.txt",filename));
        System.out.println(file);
        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String p : persons)
            {
                writer.write(p, 0, p.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    }

