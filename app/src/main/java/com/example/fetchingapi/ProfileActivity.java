package com.example.fetchingapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fetchingapi.model.DataResponse;
import com.example.fetchingapi.model.UserResponse;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private TextView tv_alert;

    private ImageView iv_btnRefresh;

    private CardView cv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        int userID = intent.getIntExtra("userID", 0);
        Log.d("userProfileID","User id On Profile : "  + String.valueOf(userID));


        progressBar = findViewById(R.id.progress_bar);
        tv_alert = findViewById(R.id.tv_alert);
        iv_btnRefresh = findViewById(R.id.btn_refresh);
        cv_profile = findViewById(R.id.cv_profile);

        fetchAPI(String.valueOf(userID));
        iv_btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAPI(String.valueOf(userID));
            }
        });
    }

    private void fetchAPI(String userID) {
        showLoading();
        ApiConfig.getApiService().getUserById(userID).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.isSuccessful() && response.body().getData() != null) {
                    UserResponse userResponse = response.body().getData();
                    setUserProfile(userResponse);

                    hideLoading();;
                } else {
                    showAlert();
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                showAlert();
            }
        });
    }

    public void setUserProfile(UserResponse user) {
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_email = findViewById(R.id.tv_email);
        CircleImageView iv_photo = findViewById(R.id.iv_photo);

        tv_name.setText(user.getFirstName() + " " + user.getLastName());
        tv_email.setText(user.getEmail());
        Glide.with(this).load(user.getAvatarImg()).into(iv_photo);
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        cv_profile.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        iv_btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        cv_profile.setVisibility(View.VISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        iv_btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void showAlert() {
        tv_alert.setVisibility(View.INVISIBLE);
        iv_btnRefresh.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        cv_profile.setVisibility(View.VISIBLE);
    }


}