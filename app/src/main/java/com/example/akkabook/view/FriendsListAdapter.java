package com.example.akkabook.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akkabook.R;
import com.example.akkabook.dataModel.FriendModel;

import java.util.List;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder> {

    List<FriendModel> friends;



    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new FriendsViewHolder(view);
    }

    public void updateFriends(List<FriendModel> friendModels){
        friends.clear();
        friends.addAll(friendModels);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder {

        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
