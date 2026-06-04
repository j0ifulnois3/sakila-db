package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        String sql = """
                SELECT actor_id,
                    first_name,
                    last_name,
                    last_update
                FROM actor;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int actorId = resultSet.getInt("actor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Instant lastUpdate = resultSet.getTimestamp("last_update").toInstant();
                System.out.printf(" %d %s %s %s", actorId, firstName, lastName, lastUpdate.toString());
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve actors. Please try again.");
            e.printStackTrace();
        }


    }
}