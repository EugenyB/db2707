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
            deleteStudent(connection);
//            updateStudent(connection);
//            Student student = new Student(0, "Vasya", "Lomachenko", 30);
//            addStudent(connection, student);
//            List<Student> students = getStudents(connection);
//            students.forEach(System.out::println);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Student WHERE lastname = ?");
        preparedStatement.setString(1, "Sergeev");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void updateStudent(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Student SET name = ?, age = ? WHERE name = ?");
        preparedStatement.setString(1, "Alexander");
        preparedStatement.setInt(2, 19);
        preparedStatement.setString(3, "Alex");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void addStudent(Connection connection, Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Student (name, lastname, age) VALUES (?, ?, ?)"
        );
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getLastname());
        preparedStatement.setInt(3, student.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private List<Student> getStudents(Connection connection) throws SQLException {
        Statement statement = connection.createStatement()
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
