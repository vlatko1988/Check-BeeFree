package com.example.vlatkopopovic.checkandbeefree.RecyclerIconAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vlatkopopovic.checkandbeefree.IconActivity;
import com.example.vlatkopopovic.checkandbeefree.MainActivity;
import com.example.vlatkopopovic.checkandbeefree.R;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerViewMainAdapter;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vlatko Popovic on 23-Aug-17.
 */

public class RecyclerIconAdapter extends RecyclerView.Adapter<RecyclerIconAdapter.ViewHolder> {
    List<RecyclerIconList> listItems;
    Context context;
    IconActivity i;

    public RecyclerIconAdapter(List<RecyclerIconList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_icon_item, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecyclerIconList listItem = listItems.get(position);

        Glide.with(context).load(listItem.getIcon()).into(holder.image);



        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = listItem.getIcon();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",result);
                ((Activity)context).setResult(Activity.RESULT_OK,returnIntent);

                ((Activity)context).finish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView image;


        public ViewHolder(final View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iconChoose);


        }
    }






}
