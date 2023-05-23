package com.example.fetchingapi;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fetchingapi.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private final ArrayList<UserResponse> users = new ArrayList<>();


    public void addUser (List<UserResponse> users) {
        if(users.size() > 0){
            for (UserResponse user: users) {
                Log.d("userAdapter", user.getFirstName());
                System.out.println(user.getFirstName());
            }
        }
        this.users.addAll(users);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_user, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(UserResponse user) {
            TextView tv_email = itemView.findViewById(R.id.tv_email);
            TextView tv_name = itemView.findViewById(R.id.tv_name);
            CircleImageView iv_photo = itemView.findViewById(R.id.iv_photo);
            
            String fullname = user.getFirstName() + " " + user.getLastName();
            
            tv_email.setText(user.getEmail());
            tv_name.setText(fullname);
            Glide.with(itemView.getContext()).load(user.getAvatarImg()).into(iv_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent profileIntent = new Intent(view.getContext(), ProfileActivity.class);
                    Log.d("userID", String.valueOf(user.getId()));
                    profileIntent.putExtra("userID", user.getId());
                    view.getContext().startActivity(profileIntent);
                }
            });
        }

//        public void onClick(View view) {
//            Intent profileIntent = new Intent(view.getContext(), ProfileActivity.class);
//
//        }
    }
}
