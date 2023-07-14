package com.dashboard.dashboardapp.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import java.sql.*;
import java.time.LocalDate;

import org.junit.*;

class PatientDataTest {


    public class PatientDatabaseTest {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
        private static final String USER = "username";
        private static final String PASS = "password";

        private Connection conn;

        @Before
        public void setUp() throws SQLException {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }

        @After
        public void tearDown() throws SQLException {
            if (conn != null) {
                conn.close();
            }
        }

        @Test
        public void testRetrievePatientData() throws SQLException {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, first_name, last_name, gender, date_of_birth, height, phone_number, email_address FROM patients";
            ResultSet rs = stmt.executeQuery(sql);

            // Assert that at least one row was retrieved
            Assert.assertTrue(rs.next());

            // Assert that the retrieved data is correct
            assertEquals(1, rs.getInt("id"));
            assertEquals("tinashe", rs.getString("first_name"));
            assertEquals("mugaro", rs.getString("last_name"));
            assertEquals("Male", rs.getString("gender"));
            assertEquals(LocalDate.of(1988, 12, 6), rs.getDate("date_of_birth").toLocalDate());
            assertEquals("70", rs.getString("height"));
            assertEquals("70", rs.getString("weight"));
            assertEquals("120", rs.getString("systolicBloodPressure"));
            assertEquals("90", rs.getString("diastolicBloodPressure"));
            assertEquals("88", rs.getString("blood_glucose"));
            assertEquals("1234567890", rs.getString("phone_number"));
            assertEquals("tinashe@zimttech.org", rs.getString("email_address"));

            rs.close();
            stmt.close();
        }

        public class HeightRangeTest {
            @Test
            public void testHeightRangeShort() {
                HeightRange range = new HeightRange(0, 150);
                assertEquals("Short", range.toString());
                assertEquals(75, range.getMidpoint());
            }

            @Test
            public void testHeightRangeAverage() {
                HeightRange range = new HeightRange(150, 180);
                assertEquals("Average", range.toString());
                assertEquals(165, range.getMidpoint());
            }

            @Test
            public void testHeightRangeTall() {
                HeightRange range = new HeightRange(180, 300);
                assertEquals("Tall", range.toString());
                assertEquals(240, range.getMidpoint());
            }
        }

    }

}