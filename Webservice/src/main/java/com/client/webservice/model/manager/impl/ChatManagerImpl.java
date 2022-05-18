package com.client.webservice.model.manager.impl;

import com.client.webservice.model.dao.Chat;
import com.client.webservice.model.manager.ChatManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * City DTO Manager.
 *
 * Contains all the queries used to consult and manipulate Cities data.
 *
 * @author jose.m.prieto.villar
 *
 */
public class ChatManagerImpl implements ChatManager {

    @Override
    public List<Chat> findAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM chat");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<Chat> chats = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                chats.add(new Chat(result));
                // Groups the countried by city
            }
            return chats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Chat findById(Connection con, Integer id) {
        //prepare SQL statement
        String sql = "select * "
                + "from chat "
                + "where id = ? ";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            Chat chat = null;

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                chat = new Chat(result);
            }

            return chat;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean delete(Connection con, Integer id) {
        //prepare SQL statement
        String sql = "DELETE FROM chat WHERE id = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int create(Connection con, Chat entity) {

            //prepare SQL statement
            String sql = "INSERT INTO chat (mensaje, usuario, fecha)" +
                    " values(?,?,?)";
            // Create general statement
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                //Add Parameters
                stmt.setString(1, entity.getMensaje());
                stmt.setInt(2, entity.getUsuario());
                stmt.setString(3, entity.getFecha().toString());
                // Queries the DB

                int affectedRows = stmt.executeUpdate();
                if(affectedRows<=0){
                    return 0;
                }

                ResultSet resultSet = stmt.getGeneratedKeys();
                resultSet.beforeFirst();
                resultSet.next();
                return resultSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }

    @Override
    public boolean update(Connection con, Chat entity) {
        return false;
    }
}