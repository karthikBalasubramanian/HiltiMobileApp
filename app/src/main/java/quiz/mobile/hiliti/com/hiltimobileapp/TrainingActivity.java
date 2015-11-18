package quiz.mobile.hiliti.com.hiltimobileapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapter;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.TrainingMaterialsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.model.ViewModel;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;
import quiz.mobile.hiliti.com.hiltimobileapp.task.TrainingAsyncTask;

public class TrainingActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener, TrainingMaterialsCallBackListener {

    private View content;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TrainingPojo> jsonResponse = new ArrayList<TrainingPojo>();
    private ArrayList<ViewModel> viewModels = new ArrayList<ViewModel>();
    ViewModel viewModel = null;
    TrainingPojo trainingPojo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initToolbar();
        initRecyclerView();
        content = findViewById(R.id.trainingView);
        if (jsonResponse.isEmpty()) new TrainingAsyncTask(this).execute();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        setRecyclerAdapter(recyclerView);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.m("count of adapters" + recyclerViewAdapter.getItemCount());
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.trainingToolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onItemClick(View view, TrainingPojo trainingPojo) {
        ToolTrainingActivity.navigate(this, view.findViewById(R.id.networkImageView),trainingPojo);
        /*Snackbar.make(content,trainingPojo.getTitle()+ " pressed",Snackbar.LENGTH_LONG).show();*/
    }

    @Override
      public void getTrainingMaterialsList(ArrayList<TrainingPojo> trainingPojos) {
        jsonResponse = trainingPojos;
        recyclerViewAdapter.setViewModels(jsonResponse);
    }

}
