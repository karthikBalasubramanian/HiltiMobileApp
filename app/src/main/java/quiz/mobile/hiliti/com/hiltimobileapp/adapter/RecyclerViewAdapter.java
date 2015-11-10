/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.R;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.model.ViewModel;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<TrainingPojo> items = new ArrayList<TrainingPojo>();
    private OnItemClickListener onItemClickListener;
    ImageLoader mImageLoader;
    VolleySingleton volleySingleton;

    public RecyclerViewAdapter() {
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mImageLoader = volleySingleton.getvSingletonInstance().getImageLoader();
    }

    public void setViewModels(ArrayList<TrainingPojo> viewModels) {
        this.items = viewModels;
        notifyDataSetChanged();
    }
    /*public RecyclerViewAdapter(List<ViewModel> items) {
        this.items = items;
    }*/

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.m("inside onCreateViewHol");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        v.setOnClickListener(this);  // start listening to the view
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.m("inside onBindViewHolder");
        TrainingPojo item = items.get(position); //get viewholder position from the list of view holders
        Log.m("item text" + item.getTitle());
        holder.text.setText(item.getTitle());
        //holder.ImageView.setImageUrl(item.getImageRes(), mImageLoader);

        loadImages(item.getImageRes(), holder);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    TrainingPojo tpojo =(TrainingPojo) v.getTag();
                    onItemClickListener.onItemClick(v, (TrainingPojo) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ImageView;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            ImageView = (ImageView) itemView.findViewById(R.id.networkImageView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, TrainingPojo trainingPojo);

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
