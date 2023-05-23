package com.example.fetchingapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fetchingapi.model.UserResponse;
import com.example.fetchingapi.model.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvName, tvEmail;
    private ImageView ivAvatar;

    private RecyclerView rv_user;
    private UserAdapter userAdapter;

    private ProgressBar progressBar;

    private TextView tv_alert;

    private ImageView iv_btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_user = findViewById(R.id.rv_user);
        progressBar = findViewById(R.id.progress_bar);
        tv_alert = findViewById(R.id.tv_alert);
        iv_btnRefresh = findViewById(R.id.btn_refresh);

        userAdapter = new UserAdapter();

        fetchAPI();
        iv_btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAPI();
            }
        });
    }

    private void fetchAPI() {
        showLoading();
        ApiConfig.getApiService().getUsers("1").enqueue(new Callback<UsersResponse>() {

            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                    if(response.isSuccessful() && response.body() != null) {
                        userAdapter.addUser(response.body().getData());

                        rv_user.setAdapter(userAdapter);
                        rv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        Log.d("Main Activity", response.body().toString());

                        for (UserResponse user : response.body().getData()) {
                            Log.d("users", "Name : " + user.getFirstName() + " " + user.getLastName());
                        }

                        hideLoading();
                    }
                    else {
                        showAlert();
                    }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                showAlert();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rv_user.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        iv_btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        rv_user.setVisibility(View.VISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        iv_btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void showAlert() {
        tv_alert.setVisibility(View.VISIBLE);
        iv_btnRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        rv_user.setVisibility(View.INVISIBLE);
    }
}