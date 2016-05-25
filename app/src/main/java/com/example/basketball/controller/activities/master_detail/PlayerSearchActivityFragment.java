package com.example.basketball.controller.activities.master_detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerSearchActivityFragment extends Fragment implements PlayerCallback {

    private Button buscar;
    private EditText buscador;
    private RecyclerView recyclerView;
    private List<Player> players;

    public PlayerSearchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_search, container, false);
        buscador = (EditText) view.findViewById(R.id.buscador);
        buscar = (Button) view.findViewById(R.id.buscar);
        recyclerView = (RecyclerView) view.findViewById(R.id.player_list);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerManager.getInstance(getContext()).getNombrePlayers(buscador.getText().toString(), PlayerSearchActivityFragment.this);
            }
        });
        return view;
    }


    @Override
    public void onSuccess(List<Player> playerList) {
        players = playerList;
        setupRecyclerView(recyclerView);
    }

    @Override
    public void onFailure(Throwable t) {

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i("setupRecyclerView", "                     " + players);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(players));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Player> mValues;

        public SimpleItemRecyclerViewAdapter(List<Player> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getId().toString());
            holder.mContentView.setText(mValues.get(position).getName());
            holder.birthday.setText(mValues.get(position).getBirthday());
            holder.canastas.setText(String.valueOf(mValues.get(position).getBaskets()));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final TextView canastas;
            public final TextView birthday;
            public Player mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                canastas = (TextView) view.findViewById(R.id.canastas);
                birthday = (TextView) view.findViewById(R.id.birthday);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
