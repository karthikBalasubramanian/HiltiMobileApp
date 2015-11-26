package quiz.mobile.hiliti.com.hiltimobileapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapter;
import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapterLeaderboard;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.LeaderBoardCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;
import quiz.mobile.hiliti.com.hiltimobileapp.task.LeaderboardAsyncTask;
import quiz.mobile.hiliti.com.hiltimobileapp.widget.GridRecyclerView;

public class Leaderboard extends AppCompatActivity implements LeaderBoardCallBackListener {
Button btnExit;

    ArrayList<UserProfile> leaderList = new ArrayList<UserProfile>();
    private GridRecyclerView recyclerView;
    private RecyclerViewAdapterLeaderboard recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        initToolbar();
        initRecyclerView();

        if (leaderList.isEmpty()) new LeaderboardAsyncTask(this).execute();



        btnExit = (Button) findViewById(R.id.btn_exitLeaderboard);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leaderboard, menu);
        return true;
    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.leaderBoardToolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getLeaderList(ArrayList<UserProfile> userProfilePojos) {
        this.leaderList = userProfilePojos;
        recyclerViewAdapter.setViewModels(this.leaderList);
    }

    private void initRecyclerView() {
        recyclerView = (GridRecyclerView) findViewById(R.id.scores);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        setRecyclerAdapter(recyclerView);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        recyclerViewAdapter = new RecyclerViewAdapterLeaderboard();
//        recyclerViewAdapter.setOnItemClickListener(recyclerViewAdapter);
        this.recyclerView.setAdapter(recyclerViewAdapter);
        Log.m("count of adapters" + recyclerViewAdapter.getItemCount());
    }
}
