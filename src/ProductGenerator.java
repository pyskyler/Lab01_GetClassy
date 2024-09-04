import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;


public class ProductGenerator {

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        String id;
        String Name;
        String title;
        double cost;
        Product product;

        String filename;

        boolean addAnotherProduct;

        Scanner in = new Scanner(System.in);

        do {
            id = SafeInput.getNonZeroLenString(in, "What is this product's id?");
            Name = SafeInput.getNonZeroLenString(in, "What is this product's name?");
            title = SafeInput.getNonZeroLenString(in, "What is this product's description?");
            cost = SafeInput.getDouble(in, "What is this product's cost?");

            product = new Product(id, Name, title, cost);
            products.add(product);

            addAnotherProduct = SafeInput.getYNConfirm(in, "Do you have another product to add?");
        } while (addAnotherProduct);

        filename = SafeInput.getNonZeroLenString(in, "What would you like the file name to be?");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + String.format("/src/%s.txt", filename));
        System.out.println(file);
        try {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for (Product p : products) {
                String pCsv = p.toCSV();
                writer.write(pCsv, 0, pCsv.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
