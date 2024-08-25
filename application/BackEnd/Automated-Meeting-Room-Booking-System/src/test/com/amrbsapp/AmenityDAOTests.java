package test.com.amrbsapp;

import com.amrbsapp.dao.impl.AmenityImpl;
import com.amrbsapp.entity.Amenity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AMRBS Amenity App Tests")

public class AmenityDAOTests {

    private static Connection connection;
    private AmenityImpl amenityDAO;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        // Set up the database connection
        String url = "jdbc:mysql://localhost:3306"; // Ensure you're using a test DB
        String username = "root";
        String password = "**********"; // Use the appropriate password
        connection = DriverManager.getConnection(url, username, password);
    }

    @BeforeEach
    public void setUp() {
        amenityDAO = new AmenityImpl();
    }

    @Test
    public void testSaveAmenity() throws Exception {
        Amenity amenity = new Amenity(0, "Test Amenity", 30); // amenityID set to 0 (or any default value)

        // Save the amenity
        amenityDAO.saveAmenity(amenity, connection);

        // Retrieve the amenity by name to verify it was saved
        List<Amenity> amenities = amenityDAO.getAllAmenities(connection);
        boolean found = false;
        for (Amenity a : amenities) {
            if ("Test Amenity".equals(a.getName()) && a.getCreditCost() == 30) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetAmenity() throws Exception {
        // Assume amenity with ID 1 exists in your test database
        Amenity amenity = amenityDAO.getAmenity(1, connection);

        assertNotNull(amenity);
        assertEquals(1, amenity.getAmenityID());
        assertEquals("WiFi", amenity.getName()); // Replace with actual data from your DB
        assertEquals(10, amenity.getCreditCost()); // Replace with actual data from your DB
    }

    @Test
    public void testUpdateAmenity() throws Exception {
        // Assuming an amenity with ID 1 exists
        Amenity amenity = new Amenity(1, "Updated Amenity", 50);

        // Update the amenity
        amenityDAO.updateAmenity(amenity, connection);

        // Retrieve the updated amenity to verify
        Amenity updatedAmenity = amenityDAO.getAmenity(1, connection);
        assertEquals("Updated Amenity", updatedAmenity.getName());
        assertEquals(50, updatedAmenity.getCreditCost());
    }

    @Test
    public void testDeleteAmenity() throws Exception {
        // Assuming an amenity with ID 2 exists
        amenityDAO.deleteAmenity(2, connection);

        // Try to retrieve the amenity to ensure it was deleted
        Amenity deletedAmenity = amenityDAO.getAmenity(2, connection);
        assertNull(deletedAmenity);
    }


    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
