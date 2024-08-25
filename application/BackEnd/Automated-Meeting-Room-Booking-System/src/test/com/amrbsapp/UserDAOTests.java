package test.com.amrbsapp;



import com.amrbsapp.dao.impl.UserImpl;
import com.amrbsapp.entity.*;
import org.junit.jupiter.api.*;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.List;


import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("AMRBS User App Tests")
public class UserDAOTests {

    private Connection connection;
    private UserImpl userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the in-memory database and DAO
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        userDAO = new UserImpl();

        // Set up schema and initial data
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users (" +
                    "userID INT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "role VARCHAR(255), " +
                    "credits INT)");

            statement.execute("INSERT INTO users (userID, name, email, password, role) VALUES (1, 'John Doe', 'john.doe@example.com', 'password123', 'Admin')");
            statement.execute("INSERT INTO users (userID, name, email, password, role, credits) VALUES (2, 'Jane Smith', 'jane.smith@example.com', 'password456', 'Manager', 100)");
            statement.execute("INSERT INTO users (userID, name, email, password, role) VALUES (3, 'Bob Brown', 'bob.brown@example.com', 'password789', 'Member')");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testGetUserById() throws SQLException {
        User user = userDAO.gerUserById(1, connection);
        assertNotNull(user);
        assertEquals(1, user.getUserID());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Admin", user.getRole().toString());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> users = userDAO.getAllUsers(connection);
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    public void testSaveUser() throws SQLException {
        User newUser = new Member(4, "Alice Green", "alice.green@example.com", "password321", RoleType.MEMBER);
        userDAO.saveUser(newUser, connection);

        User savedUser = userDAO.gerUserById(4, connection);
        assertNotNull(savedUser);
        assertEquals(4, savedUser.getUserID());
        assertEquals("Alice Green", savedUser.getName());
        assertEquals("alice.green@example.com", savedUser.getEmail());
        assertEquals("Member", savedUser.getRole().toString());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User userToUpdate = new Admin(1, "John Doe Updated", "john.doe@example.com", "newpassword123", RoleType.ADMIN);
        userDAO.updateUser(userToUpdate, connection);

        User updatedUser = userDAO.gerUserById(1, connection);
        assertNotNull(updatedUser);
        assertEquals("John Doe Updated", updatedUser.getName());
        assertEquals("newpassword123", updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        userDAO.deleteUser(3, connection);
        User deletedUser = userDAO.gerUserById(3, connection);
        assertNull(deletedUser);
    }

    @Test
    public void testGetUserByEmail() throws SQLException {
        User user = userDAO.getUserByEmail("jane.smith@example.com", connection);
        assertNotNull(user);
        assertEquals(2, user.getUserID());
        assertEquals("Jane Smith", user.getName());
        assertEquals("Manager", user.getRole().toString());
    }

}
