package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class notlar extends android.app.Fragment {
    View view;
    private boolean shouldRefreshOnResume = false;
    public static ListAdapter listAdapter;
    public static RecyclerView recyclerView;
    public static ImageButton search;
    veri_kaynagi vk;
    List<not> notes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.notlar,container,false);

        vk = new veri_kaynagi(getActivity());
        vk.ac();
        notes = vk.listele();
        vk.kapat();

        recyclerView = view.findViewById(R.id.recyclerView);
        listAdapter = new ListAdapter(notes,getActivity());
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final TextView not_olustur = view.findViewById(R.id.not_olustur);
        not_olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), not_olustur.class);
                startActivity(intent);
            }
        });

        search = view.findViewById(R.id.not_ara);
        final EditText search_text = view.findViewById(R.id.not_ara_text);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<not> notess = new ArrayList<>();
                String temp = search_text.getText().toString();
                if(! temp.equalsIgnoreCase("")){
                    for (int i = 0;i<notes.size();i++){
                        if(notes.get(i).getBaslik().contains(temp)){
                            notess.add(notes.get(i));
                        }
                    }
                    listAdapter = new ListAdapter(notess,getActivity());
                    recyclerView.setAdapter(listAdapter);
                }
                else{
                    vk.ac();
                    notes = vk.listele();
                    vk.kapat();
                    listAdapter = new ListAdapter(notes,getActivity());
                    recyclerView.setAdapter(listAdapter);
                }
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(shouldRefreshOnResume){
            veri_kaynagi vk = new veri_kaynagi(getActivity());
            vk.ac();
            List<not> notes = vk.listele();
            vk.kapat();

            ListAdapter listAdapter = new ListAdapter(notes,getActivity());
            recyclerView.setAdapter(listAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            shouldRefreshOnResume = false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }
}

