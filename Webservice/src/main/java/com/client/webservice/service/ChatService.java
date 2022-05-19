package com.client.webservice.service;


import com.client.webservice.model.conector.MySQLConnector;
import com.client.webservice.model.dao.Chat;
import com.client.webservice.model.manager.ChatManager;
import com.client.webservice.model.manager.impl.ChatManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ChatService {

    private static ChatManager chatManager;

    public ChatService(ChatManagerImpl cityManager){
        this.chatManager = cityManager;
    }

    public List<Chat> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return chatManager.findAll(con);
        }
    }

    public  Chat findById(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return chatManager.findById(con, id);
        }
    }

    public boolean deleteCity(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return chatManager.delete(con, id);
        }
    }

    public int createCity(Chat chat) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return chatManager.create(con , chat);
        }
    }

    public boolean updateCity(Chat chat) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return chatManager.update(con, chat);
        }
    }
}
