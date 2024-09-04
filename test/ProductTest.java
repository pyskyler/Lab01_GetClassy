import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("000001", "Swatch", "A green pastel square swatch", 119.99);
    }

    @Test
    void toCSV() {
        assertEquals("000001, Swatch, A green pastel square swatch, 119.99", product.toCSV());
    }

    @Test
    void toJSON() {
        String expectedJson = "{\"id\":\"000001\", \"name\":\"Swatch\", \"description\":\"A green pastel square swatch\", \"cost\":119.99}";
        assertEquals(expectedJson, product.toJSON());
    }

    @Test
    void toXML() {
        String expectedXml = "<product>\n" +
                "  <id>000001</id>\n" +
                "  <name>Swatch</name>\n" +
                "  <description>A green pastel square swatch</description>\n" +
                "  <cost>119.99</cost>\n" +
                "</product>";
        assertEquals(expectedXml, product.toXML());
    }

    @Test
    void formattedData() {
        String expectedData = "000001  Swatch         A green pastel square swatch  119.99";
        assertEquals(expectedData, product.formattedData());
    }
}