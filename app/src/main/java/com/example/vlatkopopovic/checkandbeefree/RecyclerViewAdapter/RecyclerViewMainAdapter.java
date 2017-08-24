package com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vlatkopopovic.checkandbeefree.Database.SQLite;
import com.example.vlatkopopovic.checkandbeefree.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Vlatko Popovic on 19-Aug-17.
 */

public class RecyclerViewMainAdapter extends RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolder> {

    List<RecyclerListItem> listItems;
    Context context;
    private SQLite dbAdapter;


    public RecyclerViewMainAdapter(List<RecyclerListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RecyclerListItem listItem = listItems.get(position);
        holder.title.setText(listItem.getTitle());
        holder.question.setText(listItem.getQuestion());
        Glide.with(context).load(listItem.getImage()).into(holder.image);
        switch (listItem.getSwitchButton()) {
            case 0:
                holder.switchButton.setChecked(false);
                break;
            case 1:
                holder.switchButton.setChecked(true);
                break;
            default:
                holder.switchButton.setChecked(false);
                break;
        }

        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Snackbar.make(compoundButton, listItem.getTitle() + " is set to ON", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    initializeDatabase();
                    dbAdapter.updateSwitch(1, listItem.getTitle());


                } else {
                    Snackbar.make(compoundButton, listItem.getTitle() + " is set to OFF", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    initializeDatabase();
                    dbAdapter.updateSwitch(0, listItem.getTitle());


                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView question;
        public ImageView image;
        public Switch switchButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            question = itemView.findViewById(R.id.textViewQuestion);
            image = itemView.findViewById(R.id.imageViewIcon);
            switchButton = itemView.findViewById(R.id.switchButton);


        }


    }


    private void initializeDatabase() {
        dbAdapter = new SQLite(context);
        dbAdapter.open();
    }
}


//holder.image.setImageResource(R.drawable.ic_menu_send);


// holder.image.setImageBitmap(decodeFile(listItem.getImage()));


//holder.image.setImageURI(listItem.getSlika());

   /* private Bitmap decodeFile(String fPath) {
        // Decode image size
        BitmapFactory.Options opts = new BitmapFactory.Options();
    *//*
     * If set to true, the decoder will return null (no bitmap), but the
     * out... fields will still be set, allowing the caller to query the
     * bitmap without having to allocate the memory for its pixels.
     *//*
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        // memory, the Bitmap can be cleared
        opts.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the
        // future

        BitmapFactory.decodeFile(fPath, opts);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 20;

        // Find the correct scale value.
        int scale = 1;

        if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) opts.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) opts.outWidth
                    / (float) REQUIRED_SIZE);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
        }

        // Decode bitmap with inSampleSize set
        opts.inJustDecodeBounds = false;

        opts.inSampleSize = scale;

        Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(
                Bitmap.Config.RGB_565, false);

        return bm;

    }*/