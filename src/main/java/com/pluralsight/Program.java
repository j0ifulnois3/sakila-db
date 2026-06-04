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
        dataSource.setPassword("password");

        String sql = "SELECT actor_id,\n" +
                "    first_name,\n" +
                "    last_name,\n" +
                "    last_update\n" +
                "FROM actor";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int actorId = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Instant lastUpdate = rs.getTimestamp("last_update").toInstant();
                System.out.printf("%d %s %s %s", actorId, firstName, lastName, lastUpdate.toInstant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
