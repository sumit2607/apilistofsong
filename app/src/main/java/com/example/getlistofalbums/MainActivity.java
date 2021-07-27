package com.example.getlistofalbums;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ResponseModel> responseModel = new ArrayList<>();
    private ProgressBar rotating;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        posts();
        setrecycle();
    }


    private void setrecycle() {
        albumAdapter = new AlbumAdapter(responseModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initviews() {
        recyclerView = findViewById(R.id.recyclerView);
        rotating = findViewById(R.id.rotating);
        rotating.setVisibility(View.VISIBLE);
    }

    private void posts() {


        Apiservice apiservice = Network.getInstance().create(Apiservice.class);
        Call<List<ResponseModel>> call = apiservice.getUser();
        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {

                
                responseModel = response.body();
                albumAdapter.updateData(responseModel);
                rotating.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

}