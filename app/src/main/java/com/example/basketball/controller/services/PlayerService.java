package com.example.basketball.controller.services;

import com.example.basketball.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Alfredo on 28/02/2016.
 */
public interface PlayerService {

    @GET("/api/players")
    Call<List<Player>> getAllPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization

    );

    @POST("/api/players")
    Call<Player> postPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Body Player Player

    );

    @PUT("/api/players")
    Call<Player> putPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Body Player Player

    );

    @GET("/api/players/nombre/{nombre}")
    Call<List<Player>> getNombrePlayer(
            @Path("nombre") String nombre,
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization
    );
}
