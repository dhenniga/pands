package com.pands.dev.pands;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private Context mContext;
    private List<PostValue> postList;
    private LayoutInflater inflater;


    public PostAdapter(Context context, List<PostValue> postList) {
        this.postList = postList;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvID = (TextView) itemView.findViewById(R.id.tvID);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostValue currentPost = postList.get(position);
        holder.tvID.setText(currentPost.getID());
        holder.tvTitle.setText(currentPost.getTitle());

    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }
}
