package com.pands.dev.pands.product;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pands.dev.pands.MainActivity;
import com.pands.dev.pands.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context mContext;
    private List<ProductValue> postList;
    private LayoutInflater inflater;
    public View productView;


    public ProductAdapter(Context context, List<ProductValue> postList) {
        this.postList = postList;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPrice, tvTitle, tvPriceColTwo,tvTitleColTwo;
        LinearLayout fragment_container, llProductDetailsContainer;
        RelativeLayout rlProductColTwoGalleryView;
        ImageView ivProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            llProductDetailsContainer = (LinearLayout) itemView.findViewById(R.id.llProductDetailsContainer);

            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvPriceColTwo = (TextView) itemView.findViewById(R.id.tvPriceColTwo);

            tvTitle = (TextView) itemView.findViewById(R.id.tvProductTitle);
            tvTitleColTwo = (TextView) itemView.findViewById(R.id.tvTitleColTwo);

            rlProductColTwoGalleryView = (RelativeLayout) itemView.findViewById(R.id.rlProductColTwoGalleryView);

            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            fragment_container = (LinearLayout) itemView.findViewById(R.id.fragment_container);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        productView = inflater.inflate(R.layout.list_item_post, parent, false);
        final ViewHolder viewHolder = new ViewHolder(productView);

        final Typeface RalewayExtraLight = Typeface.createFromAsset(mContext.getAssets(), "Raleway-ExtraLight.otf");
        final Typeface RalewayMedium = Typeface.createFromAsset(mContext.getAssets(), "Raleway-Medium.otf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(mContext.getAssets(), "PlayfairDisplay-Italic.otf");

        viewHolder.tvTitle.setTypeface(PlayFairDisplayItalic);
        viewHolder.tvPrice.setTypeface(RalewayExtraLight);

        viewHolder.tvTitleColTwo.setTypeface(RalewayMedium);
        viewHolder.tvPriceColTwo.setTypeface(RalewayExtraLight);

        MainActivity mainActivity = new MainActivity();
        int colNum = mainActivity.numberOfColumns;

        if (colNum == 2) {
            viewHolder.fragment_container.setVisibility(View.GONE);
            viewHolder.rlProductColTwoGalleryView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.fragment_container.setVisibility(View.VISIBLE);
            viewHolder.rlProductColTwoGalleryView.setVisibility(View.GONE);
        }

        return new ViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductValue currentPost = postList.get(position);
        holder.tvPrice.setText("€" + ((String.valueOf(currentPost.getPrice()))));
        holder.tvTitle.setText(currentPost.getTitle());

        holder.tvPriceColTwo.setText("€" + ((String.valueOf(currentPost.getPrice()))));
        holder.tvTitleColTwo.setText(currentPost.getTitle());

        if (currentPost.getStock_quantity() == 0){
            holder.llProductDetailsContainer.setAlpha(0.3f);
        }


        String imageURL = currentPost.getFeatured_src();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        Picasso.with(mContext).load(imageURL).resize(displayMetrics.widthPixels/2, displayMetrics.widthPixels/2).centerInside().into(holder.ivProduct);

    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }
}
