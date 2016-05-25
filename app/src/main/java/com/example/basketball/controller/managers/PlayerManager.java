package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Player;
import com.example.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerManager {
    private static PlayerManager ourInstance;
    private List<Player> players;

    private Player playerito;

    private Retrofit retrofit;
    private Context context;
    private PlayerService playerService;

    private PlayerManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        playerService = retrofit.create(PlayerService.class);
    }

    public static PlayerManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new PlayerManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getAllPlayers(final PlayerCallback playerCallback) {
        Call<List<Player>> call = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    public synchronized void putPlayers(Player player, final PlayerPostCallback playerPostCallback) {
        Call<Player> call = playerService.putPlayer(UserLoginManager.getInstance(context).getBearerToken(), player);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                playerito = response.body();
                int code = response.code();
                if (code == 200 || code == 201) {
                    playerPostCallback.onSuccess("Añadido Correctamente");
                } else {
                    playerPostCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);
                playerPostCallback.onFailure(t);
            }
        });
    }


    public synchronized void postPlayers(Player player, final PlayerPostCallback playerPostCallback) {

        Call<Player> call = playerService.postPlayer(UserLoginManager.getInstance(context).getBearerToken(), player);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                playerito = response.body();
                int code = response.code();
                if (code == 200 || code == 201) {
                    playerPostCallback.onSuccess("Añadido Correctamente");
                } else {
                    playerPostCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);
                playerPostCallback.onFailure(t);
            }
        });
    }


    public synchronized void getNombrePlayers(String nombre, final PlayerCallback playerCallback) {

        Call<List<Player>> call = playerService.getNombrePlayer(nombre, UserLoginManager.getInstance(context).getBearerToken());
        call.enqueue(new Callback<List<Player>>() {

            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();
                int code = response.code();
                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);
                playerCallback.onFailure(t);
            }
        });
    }

    public Player getPlayer(String id) {
        for (Player player : players) {
            if (player.getId().toString().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
