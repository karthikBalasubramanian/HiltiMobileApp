package quiz.mobile.hiliti.com.hiltimobileapp.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.R;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;

/**
 * Created by Poorna on 26/11/2015.
 */
public class RecyclerViewAdapterLeaderboard extends RecyclerView.Adapter<RecyclerViewAdapterLeaderboard.ViewHolder> {

    private ArrayList<UserProfile> items = new ArrayList<UserProfile>();
    private OnItemClickListener onItemClickListener;
    ImageLoader mImageLoader;
    VolleySingleton volleySingleton;

    public RecyclerViewAdapterLeaderboard() {
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mImageLoader = volleySingleton.getvSingletonInstance().getImageLoader();
    }

    public void setViewModels(ArrayList<UserProfile> viewModels) {
        this.items = viewModels;
        notifyDataSetChanged();

    }
    /*public RecyclerViewAdapter(List<ViewModel> items) {
        this.items = items;
    }*/



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.m("inside onCreateViewHol");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_recycler, parent, false);
        //v.setOnClickListener(this);  // start listening to the view
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.m("inside onBindViewHolder");
        UserProfile item = items.get(position); //get viewholder position from the list of view holders
        Log.m("item text" + item.getFirstName() + item.getLastName());
        holder.name.setText(item.getFirstName() + " " + item.getLastName());
        holder.score.setText(Integer.toString(item.getTotalScore()));
        //holder.ImageView.setImageUrl(item.getImageRes(), mImageLoader);
    Log.m("display pic"+item.getDisplayPic());
        loadImages(item.getDisplayPic(), holder);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    @Override
//    public void onClick(final View v) {
//        // Give some time to the ripple to finish the effect
//        if (onItemClickListener != null) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    UserProfile tpojo =(UserProfile) v.getTag();
//                    onItemClickListener.onItemClick(v, (UserProfile) v.getTag());
//                }
//            }, 200);
//        }
//    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public android.widget.ImageView ImageView;
        public TextView name;
        public TextView score;

        public ViewHolder(View itemView) {
            super(itemView);
            ImageView = (ImageView) itemView.findViewById(R.id.leader_networkImageView);
            name = (TextView) itemView.findViewById(R.id.leader_name);
            score = (TextView) itemView.findViewById(R.id.leader_score);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, UserProfile leaderPojo);

    }

    private void loadImages(String urlThumbnail, final ViewHolder holder) {

        mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.ImageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}