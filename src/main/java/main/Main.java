package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Properties properties = new Properties();
        properties.setProperty("user", "eugeny");
        properties.setProperty("password", "123");
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mainacad1", properties);
            List<Student> students = getStudents(connection);
            students.forEach(System.out::println);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Student> getStudents(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            int age = resultSet.getInt("age");
            students.add(new Student(id,name,lastname,age));
        }
        return students;
    }
}
