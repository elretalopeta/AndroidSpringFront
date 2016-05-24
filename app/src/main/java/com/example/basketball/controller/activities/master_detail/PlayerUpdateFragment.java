package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerUpdateFragment extends Fragment {

    public static String ARG_ITEM_ID = "item_id";

    private EditText nombre, canastas;
    private Button update;
    private static Button fecha;
    private Player mItem;

    public PlayerUpdateFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getArguments().getString(ARG_ITEM_ID);
        mItem = PlayerManager.getInstance(this.getContext()).getPlayer(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player_update, container, false);
        nombre = (EditText) view.findViewById(R.id.nombre);
        canastas = (EditText) view.findViewById(R.id.canastas);
        fecha = (Button) view.findViewById(R.id.fecha);
        update = (Button) view.findViewById(R.id.update);
        setPlayer();
        return view;
    }

    public void setPlayer(){
        //nombre.setText(mItem.getName());
        //canastas.setText(mItem.getBaskets());
        //fecha.setText(mItem.getBirthday());
    }
}
