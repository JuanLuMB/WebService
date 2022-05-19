package com.client.webservice.model.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class Chat {

    int id;
    String mensaje;
    int usuario;
    String fecha;

    public Chat(ResultSet result) {
        try {
            this.mensaje = result.getString("mensaje");
            this.usuario = result.getInt("usuario");
            this.id = result.getInt("id");
            this.fecha = (result.getString("fecha"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
