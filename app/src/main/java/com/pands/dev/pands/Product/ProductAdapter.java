package com.pands.dev.pands.product;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pands.dev.pands.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context mContext;
    private List<com.pands.dev.pands.product.ProductValue> postList;
    private LayoutInflater inflater;
    public View productView;


    public ProductAdapter(Context context, List<ProductValue> postList) {
        this.postList = postList;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvTitle;
        ImageView ivProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            tvID = (TextView) itemView.findViewById(R.id.tvPrice);
            tvTitle= (TextView) itemView.findViewById(R.id.tvProductTitle);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        productView = inflater.inflate(R.layout.list_item_post, parent, false);
        final ViewHolder viewHolder = new ViewHolder(productView);

        final Typeface RalewayExtraLight = Typeface.createFromAsset(mContext.getAssets(), "Raleway-ExtraLight.ttf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(mContext.getAssets(), "PlayfairDisplay-Italic.otf");

        viewHolder.tvTitle.setTypeface(PlayFairDisplayItalic);
        viewHolder.tvID.setTypeface(RalewayExtraLight);

        return new ViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductValue currentPost = postList.get(position);
        holder.tvID.setText("€" + ((String.valueOf(currentPost.getId()))));
        holder.tvTitle.setText(currentPost.getTitle());

        String imageURL = currentPost.getFeatured_src();
        Picasso.with(mContext).load(imageURL).into(holder.ivProduct);

    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }
}
