package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.PlayerPostCallback;
import com.example.basketball.model.Player;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerUpdateFragment extends Fragment implements PlayerPostCallback {

    public static final String ARG_ITEM_UPDATE_ID = "item_id";
    private EditText nombre, canastas;
    private Button update;
    private static Button fecha;
    private Player mItem;
    private static String birthday = null;

    public PlayerUpdateFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String id = getArguments().getString(ARG_ITEM_UPDATE_ID);
        //mItem = PlayerManager.getInstance(this.getContext()).getPlayer(id);
        mItem = PlayerManager.getInstance(this.getContext()).getPlayer(PlayerDetailFragment.id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player_update, container, false);
        nombre = (EditText) view.findViewById(R.id.nombre);
        canastas = (EditText) view.findViewById(R.id.canastas);
        fecha = (Button) view.findViewById(R.id.fecha);
        update = (Button) view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItem.setName(nombre.getText().toString());
                mItem.setBaskets(Integer.parseInt(canastas.getText().toString()));
                mItem.setBirthday(birthday);

                PlayerManager.getInstance(getContext()).putPlayers(mItem, PlayerUpdateFragment.this);
            }
        });
        setPlayer();
        return view;
    }

    public void setPlayer(){
        nombre.setText(mItem.getName().toString());
        canastas.setText(mItem.getBaskets().toString());
        fecha.setText(mItem.getBirthday().toString());
    }

    public static void setBirtday(int year, int month, int day){
        fecha.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        birthday = year+"-"+month+"-"+day;
        if (month < 10){birthday = year+"-0"+month+"-"+day;}
    }

    @Override
    public void onSuccess(String succes) {
        Toast.makeText(getContext(), succes, Toast.LENGTH_SHORT).show();
        Intent home = new Intent(getContext(), PlayerListActivity.class);
        startActivity(home);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
