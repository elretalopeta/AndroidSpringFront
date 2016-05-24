package com.example.basketball.controller.managers;

import com.example.basketball.model.Player;

import java.util.List;

/**
 * Created by usu27 on 23/5/16.
 */
public interface PlayerPostCallback {
    void onSuccess(String succes);
    void onFailure(Throwable t);
}
