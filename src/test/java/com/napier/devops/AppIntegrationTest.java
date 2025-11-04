package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee("255530"); // pass as String now
        assertEquals(255530, emp.emp_no);
        assertEquals("Ronghao", emp.first_name);
        assertEquals("Garigliano", emp.last_name);
    }

    @Test
    void testNullEmployee()
    {
        Employee emp = app.getEmployee("999999"); // pass as String
        assertNull(emp);
    }

    @Test
    void testAddEmployee()
    {
        Employee emp = new Employee();
        emp.emp_no = 500000;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";

        app.addEmployee(emp);

        // Retrieve using String parameter
        emp = app.getEmployee("500000");
        assertEquals(500000, emp.emp_no);
        assertEquals("Kevin", emp.first_name);
        assertEquals("Chalmers", emp.last_name);
    }
}
