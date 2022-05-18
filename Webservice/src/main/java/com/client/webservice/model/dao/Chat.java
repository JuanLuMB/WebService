package com.client.webservice.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlRootElement


public class Chat {

    int id;
    String mensaje;
    int usuario;
    LocalDate fecha;

    public Chat() {

    }
    public Chat(ResultSet result) {
        try {
            this.mensaje = result.getString("Mensaje");
            this.usuario = result.getInt("Usuario");
            this.id = result.getInt("ID");
            this.fecha = LocalDate.parse(result.getString("fecha"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
