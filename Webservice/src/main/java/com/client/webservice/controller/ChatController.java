package com.client.webservice.controller;


import com.client.webservice.controller.dto.Notification;
import com.client.webservice.model.manager.impl.ChatManagerImpl;
import com.client.webservice.service.ChatService;
import com.client.webservice.model.dao.Chat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.maven.model.Build;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Path("/notifications")
public class ChatController {

    private final ChatService chatService;

    public ChatController() {
        this.chatService = new ChatService(new ChatManagerImpl());
    }


    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(new GenericEntity<List<Chat>>(chatService.findAll()) {}).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response findById(@PathParam("id") Integer id) {
        try {
            if (id == null) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(chatService.findById(id)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }

    }
    @DELETE
    @Path("/delete/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCity(@PathParam("id") Integer id) {
        try {
            Chat cityToDelete = chatService.findById(id);
            if (cityToDelete != null) {
                if (chatService.deleteCity(id)) {
                    return Response.status(200).entity(cityToDelete).build();
                }else{
                    return Response.status(304).entity("Mensaje Was Not Deleted").build();
                }
            } else{
                return Response.status(404).entity("Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @POST
    @Path("/create")
    //@Produces(MediaType.APPLICATION_JSON)
    /**
    @Consumes(MediaType.APPLICATION_JSON)
    **/
    public Response createCity(@QueryParam("usuario")int usuario, @QueryParam("mensaje")String mensaje, @QueryParam("fecha")String fecha){

        try {
            int createdId = chatService.createCity(Chat.builder().usuario(usuario).mensaje(mensaje).fecha(fecha).build());
            if(createdId > 0){
                return Response.status(201).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating The City").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCity(Chat city){
        try {
            if(chatService.updateCity(city)){
                return Response.status(200).entity(chatService.findById(city.getId())).build();
            } else {
                return Response.status(500).entity("Internal Error During City Update").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
    @GET
    @Path("notification/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find_All() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(Notification.builder().id(14).title("Juanlu").body("Hola").build()).build();
    }

}