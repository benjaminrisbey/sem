package com.napier.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class App {

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     * @param location Database host:port
     */
    public static void connect(String location) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for DB to start
                Thread.sleep(5000);

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example"
                );

                System.out.println("Successfully connected");
                break;

            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Get a single employee record by employee number.
     * @param ID Employee number (emp_no)
     * @return Employee object if found, else null
     */
    @RequestMapping("employee")
    public Employee getEmployee(@RequestParam(value = "id") String ID) {
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT emp_no, first_name, last_name " +
                    "FROM employees " +
                    "WHERE emp_no = " + ID;

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    /**
     * Display employee details in console.
     * @param emp Employee object
     */
    public void displayEmployee(Employee emp) {
        if (emp != null) {
            System.out.println(emp.emp_no + " " + emp.first_name + " " + emp.last_name + "\n" +
                    emp.title + "\n" +
                    "Salary:" + emp.salary + "\n" +
                    emp.dept + "\n" +
                    "Manager: " + emp.manager + "\n");
        }
    }

    /**
     * Add a new employee to the database.
     * @param emp Employee object
     */
    public void addEmployee(Employee emp) {
        try {
            Statement stmt = con.createStatement();

            String strUpdate = "INSERT INTO employees (emp_no, first_name, last_name, birth_date, gender, hire_date) " +
                    "VALUES (" + emp.emp_no + ", '" + emp.first_name + "', '" + emp.last_name + "', " +
                    "'9999-01-01', 'M', '9999-01-01')";

            stmt.execute(strUpdate);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to add employee");
        }
    }

    /**
     * Get all current employees and their salaries.
     * @return List of employees with salary information
     */
    @RequestMapping("salaries")
    public ArrayList<Employee> getAllSalaries() {
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                    "FROM employees, salaries " +
                    "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' " +
                    "ORDER BY employees.emp_no ASC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Employee> employees = new ArrayList<>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }

            return employees;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Get employees and salaries by department.
     * @param deptName String
     * @return List of employees in the department
     */

    @RequestMapping("salaries_department")
    public ArrayList<Employee> getSalariesByDepartment(@RequestParam(value = "dept") String deptName) {
        try {
            // Convert deptName string to Department object
            Department dept = getDepartment(deptName);

            Statement stmt = con.createStatement();

            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                    "FROM employees, salaries, dept_emp, departments " +
                    "WHERE employees.emp_no = salaries.emp_no " +
                    "AND employees.emp_no = dept_emp.emp_no " +
                    "AND dept_emp.dept_no = departments.dept_no " +
                    "AND salaries.to_date = '9999-01-01' " +
                    "AND departments.dept_no = '" + dept.dept_no + "' " +
                    "ORDER BY employees.emp_no ASC " +
                    "LIMIT 50";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Employee> employees = new ArrayList<>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }

            return employees;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Get department details by name.
     * @param dept_name Department name
     * @return Department object or null if not found
     */


    @RequestMapping("department")
    public Department getDepartment(@RequestParam(value = "dept") String dept_name) {
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT dept_no, dept_name " +
                    "FROM departments " +
                    "WHERE dept_name = '" + dept_name + "'";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");
                return dept;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department details");
            return null;
        }
    }

    /**
     * Print a list of employees in a formatted table.
     * @param employees List of employees
     */
    public void printSalaries(ArrayList<Employee> employees) {
        if (employees == null) {
            System.out.println("No employees");
            return;
        }

        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));

        for (Employee emp : employees) {
            if (emp == null) continue;

            String emp_string = String.format("%-10s %-15s %-20s %-8s",
                    emp.emp_no, emp.first_name, emp.last_name, emp.salary);

            System.out.println(emp_string);
        }
    }

    /**
     * Get employees by role/title.
     * @param title Employee role/title
     * @return List of employees matching the role
     */
    @RequestMapping("salaries_title")
    public ArrayList<Employee> getSalariesByTitle(@RequestParam(value = "title") String title) {
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name,\n" +
                    "titles.title, salaries.salary, departments.dept_name, dept_manager.emp_no\n" +
                    "FROM employees, salaries, titles, departments, dept_emp, dept_manager\n" +
                    "WHERE employees.emp_no = salaries.emp_no\n" +
                    "  AND salaries.to_date = '9999-01-01'\n" +
                    "  AND titles.emp_no = employees.emp_no\n" +
                    "  AND titles.to_date = '9999-01-01'\n" +
                    "  AND dept_emp.emp_no = employees.emp_no\n" +
                    "  AND dept_emp.to_date = '9999-01-01'\n" +
                    "  AND departments.dept_no = dept_emp.dept_no\n" +
                    "  AND dept_manager.dept_no = dept_emp.dept_no\n" +
                    "  AND dept_manager.to_date = '9999-01-01'\n" +
                    "  AND titles.title = '" + title +"'";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Employee> employees = new ArrayList<>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                emp.title = rset.getString("titles.title");

                Department dept = new Department();
                dept.dept_name = rset.getString("departments.dept_name");

                Employee manager = new Employee();
                manager.emp_no = rset.getInt("dept_manager.emp_no");

                dept.manager = manager;
                emp.dept = dept;

                employees.add(emp);
            }

            return employees;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get role details");
            return null;
        }
    }

    /**
     * Output employee information to a Markdown file.
     * @param employees List of employees
     * @param filename Name of the output file
     */
    public void outputEmployees(ArrayList<Employee> employees, String filename) {
        if (employees == null) {
            System.out.println("No employees");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Emp No | First Name | Last Name | Title | Salary | Department | Manager |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- | --- |\r\n");

        for (Employee emp : employees) {
            if (emp == null) continue;

            String deptName = (emp.dept != null) ? emp.dept.dept_name : "";
            String managerInfo = (emp.dept != null && emp.dept.manager != null) ? String.valueOf(emp.dept.manager.emp_no) : "";

            sb.append("| " + emp.emp_no + " | " +
                    emp.first_name + " | " + emp.last_name + " | " +
                    emp.title + " | " + emp.salary + " | " +
                    deptName + " | " + managerInfo + " |\r\n");
        }

        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to start the application and connect to database.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            connect("localhost:33060");
        } else {
            connect(args[0]);
        }

        SpringApplication.run(App.class, args);
    }
}
