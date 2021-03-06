package com.divyanshu.spacestuff1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarsRoversAdapter extends RecyclerView.Adapter<MarsRoversAdapter.MarsRoversViewHolder> {

    private Context mContext;
    private ArrayList<RoverModel>  roverModelArrayList;

    public MarsRoversAdapter(Context context, ArrayList<RoverModel> roverModels){
        mContext = context;
        roverModelArrayList = roverModels;

    }

    @NonNull
    @Override
    public MarsRoversViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.rover_list_view_items,parent,false);
            return new MarsRoversViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MarsRoversViewHolder holder, int position) {
            RoverModel currentRoverModel = roverModelArrayList.get(position);
            String imageURL = currentRoverModel.getImageView();

              //Higher android devices don't allow http connections!
                imageURL.replace("http:/","https:/");
                 Log.d("BROSEPH",imageURL);

        Picasso.get().load(imageURL).fit().centerInside().placeholder(R.drawable.icon_update).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext.getApplicationContext(), FullScreenImage.class);
                    intent.putExtra("image", imageURL);
                    mContext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return roverModelArrayList.size();
    }


    public class MarsRoversViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public MarsRoversViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mars_rover_image);

        }
    }
}
