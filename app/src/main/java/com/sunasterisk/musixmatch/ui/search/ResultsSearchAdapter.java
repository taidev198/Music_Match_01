package com.sunasterisk.musixmatch.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public class ResultsSearchAdapter extends RecyclerView.Adapter<ResultsSearchAdapter.MyViewHolder> {
    private List<Track> mTracks;
    private Context mContext;
    public ResultsSearchAdapter(Context context, List<Track> tracks){
        mContext = context;
        this.mTracks = tracks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.track_card, viewGroup, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mTitle.setText(mTracks.get(i).getTrackName());
        myViewHolder.mSubtitle.setText(mTracks.get(i).getAlbumName());
        myViewHolder.mImageMore.setOnClickListener((e)->{
            myViewHolder.createOption(myViewHolder.mImageMore);
        });
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener{

        private TextView mTitle;
        private TextView mSubtitle;
        private ImageView mImageMore;
        private boolean mChecker;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSubtitle = itemView.findViewById(R.id.subtitle_info);
            mTitle = itemView.findViewById(R.id.title_info);
            mImageMore = itemView.findViewById(R.id.image_more);
            itemView.setOnClickListener(this);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(this);

        }

        /**listen event when view clicked*/
        @Override
        public void onClick(View v) {
            if (!mChecker)   System.out.println(getAdapterPosition());
        }

        /**response for long click*/
        @Override
        public boolean onLongClick(View v) {
            System.out.println("long click");
            mChecker =false;
            createOption(null);
            return true;//true if the item consumed long click, else return false
        }

        public void createOption(View anchor){
            anchor =  mImageMore;
            PopupMenu popup = new PopupMenu(mContext, anchor);
            popup.inflate(R.menu.options_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.play_item:
                        //handle menu1 click
                        return true;
                    case R.id.play_next_item:
                        //handle menu2 click
                        return true;
                    case R.id.add_to_playlist:
                        //handle menu3 click
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        }

    }
}
