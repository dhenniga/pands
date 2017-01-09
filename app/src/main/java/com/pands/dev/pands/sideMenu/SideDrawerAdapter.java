package com.pands.dev.pands.sideMenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pands.dev.pands.JSONHelper;
import com.pands.dev.pands.MainActivity;
import com.pands.dev.pands.R;
import com.squareup.picasso.Picasso;



import java.util.List;

public class SideDrawerAdapter extends RecyclerView.Adapter<SideDrawerAdapter.ViewHolder> {

    private Context mContext;
    private List<SideDrawerValue> sideDrawerList;
    private LayoutInflater inflater;
    public View sideDrawerView;

    public SideDrawerAdapter(Context context, List<SideDrawerValue> sideDrawerList) {
        this.sideDrawerList = sideDrawerList;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenuItemCount, tvMenuItemName;
        ImageView ivMenuItemThumbnail;
        LinearLayout llImageContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            ivMenuItemThumbnail = (ImageView) itemView.findViewById(R.id.ivMenuItemThumbnail);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tvMenuItemName);
            tvMenuItemCount = (TextView) itemView.findViewById(R.id.tvMenuItemCount);
            llImageContainer = (LinearLayout) itemView.findViewById(R.id.llImageContainer);

        }
    }



    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final Typeface RalewayMedium = Typeface.createFromAsset(mContext.getAssets(), "Raleway-Medium.otf");

        sideDrawerView = inflater.inflate(R.layout.side_drawer_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(sideDrawerView);

        viewHolder.tvMenuItemName.setTypeface(RalewayMedium);


        return new ViewHolder(sideDrawerView);
    }



    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SideDrawerValue sideDrawerPost = sideDrawerList.get(position);

        holder.tvMenuItemName.setText(sideDrawerPost.getName());
        holder.tvMenuItemCount.setText(((String.valueOf(sideDrawerPost.getCount()))));
        holder.tvMenuItemName.setAllCaps(true);

        String imageURL = sideDrawerPost.getImage();
        if (!imageURL.equals("")) {
            Picasso.with(mContext).load(imageURL).resize(60, 60).into(holder.ivMenuItemThumbnail);
        }

        if (sideDrawerPost.getParent() == 0) {

            final Typeface PlayFairDisplayRegular = Typeface.createFromAsset(mContext.getAssets(), "PlayfairDisplaySC-Regular.otf");
            holder.llImageContainer.setVisibility(View.GONE);
            holder.tvMenuItemName.setTypeface(PlayFairDisplayRegular);
            holder.tvMenuItemName.setTextColor(Color.parseColor("#ffffff"));
            holder.tvMenuItemName.setGravity(Gravity.BOTTOM);
            holder.tvMenuItemName.setPadding(0, 90, 0, 20);
            holder.tvMenuItemName.setTextSize(16f);
            holder.tvMenuItemName.setAllCaps(true);
            holder.tvMenuItemCount.setVisibility(View.GONE);
        }

    }



    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return sideDrawerList == null ? 0 : sideDrawerList.size();
    }

}
