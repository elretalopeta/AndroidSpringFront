package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PlayerAddFragment extends Fragment implements View.OnClickListener, PlayerPostCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText nombre, canastas;
    private Button insertar;
    private static Button fecha;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //public static Date birthday = null;
    public static String birthday = null;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerAddFragment newInstance(String param1, String param2) {
        PlayerAddFragment fragment = new PlayerAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayerAddFragment() {
        // Required empty public constructor
    }

    public static void setBirtday(int year, int month, int day){

        fecha.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
        birthday = year+"-"+month+"-"+day;

      }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear, container, false);
        nombre = (EditText) view.findViewById(R.id.nombre);
        canastas = (EditText) view.findViewById(R.id.canastas);
        insertar = (Button) view.findViewById(R.id.insertar);
        fecha = (Button) view.findViewById(R.id.fecha);

        insertar.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        Player player = new Player (String.valueOf(nombre.getText()), Integer.parseInt(String.valueOf(canastas.getText())), birthday);
        PlayerManager.getInstance(getContext()).postPlayers(player, PlayerAddFragment.this);
    }

    @Override
    public void onSuccess(String succes) {
        Toast.makeText(getContext(), succes, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
