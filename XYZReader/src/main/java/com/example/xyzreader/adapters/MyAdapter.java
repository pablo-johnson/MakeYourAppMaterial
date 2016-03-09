package com.example.xyzreader.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;
import com.example.xyzreader.ui.DynamicHeightNetworkImageView;
import com.example.xyzreader.ui.ImageLoaderHelper;

/**
 * Created by Pablo on 8/03/16.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private Cursor mCursor;
    private ArticleClickListener mListener;

    public MyAdapter(Cursor cursor, Context context) {
        mCursor = cursor;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public interface ArticleClickListener{
        void onArticleClick(Uri uri);
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getLong(ArticleLoader.Query._ID);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_article, parent, false);
        final MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onArticleClick(ItemsContract.Items.buildItemUri(getItemId(vh.getAdapterPosition())));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        ((MyViewHolder) holder).titleView.setText(mCursor.getString(ArticleLoader.Query.TITLE));
        ((MyViewHolder) holder).subtitleView.setText(
                DateUtils.getRelativeTimeSpanString(
                        mCursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                        System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_ALL).toString()
                        + " by "
                        + mCursor.getString(ArticleLoader.Query.AUTHOR));
        ((MyViewHolder) holder).thumbnailView.setImageUrl(mCursor.getString(ArticleLoader.Query.THUMB_URL),
                ImageLoaderHelper.getInstance(mContext).getImageLoader());
        ((MyViewHolder) holder).thumbnailView.setAspectRatio(mCursor.getFloat(ArticleLoader.Query.ASPECT_RATIO));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    final class MyViewHolder extends RecyclerView.ViewHolder {
        public DynamicHeightNetworkImageView thumbnailView;
        public TextView titleView;
        public TextView subtitleView;

        public MyViewHolder(View view) {
            super(view);
            thumbnailView = (DynamicHeightNetworkImageView) view.findViewById(R.id.thumbnail);
            titleView = (TextView) view.findViewById(R.id.article_title);
            subtitleView = (TextView) view.findViewById(R.id.article_subtitle);
        }
    }

    public void setListener(ArticleClickListener mListener) {
        this.mListener = mListener;
    }
}