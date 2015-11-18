package quiz.mobile.hiliti.com.hiltimobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;

import com.squareup.picasso.Picasso;

import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;
import quiz.mobile.hiliti.com.hiltimobileapp.utility.CircleTransform;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        setupDrawerLayout();
        content = findViewById(R.id.content);

        //

        //change to network URL
        String userEmail = getIntent().getStringExtra("userEmail");
        String userName = getIntent().getStringExtra("userName");
        Log.m(getIntent().getStringExtra("displayPic"));
        Log.m(getIntent().getStringExtra("userName"));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeader = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        TextView userView = ((TextView) navHeader.findViewById(R.id.userNameMain));
        userView.setText(userName);
        TextView emailView = ((TextView) navHeader.findViewById(R.id.emailMain));
        emailView.setText(userEmail);
        ImageView imageView = ((ImageView) navHeader.findViewById(R.id.avatar));
        imageView.setImageResource(R.drawable.profile_pic);
        navigationView.addHeaderView(navHeader);
        Log.m(navigationView.getMenu().getItem(0).getTitle().toString());




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.action_overflow);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView view = (NavigationView) findViewById(R.id.nav_view);
        if (view != null) {
            view.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            if (menuItem.getTitle().toString().equalsIgnoreCase("Training")) {
                                menuItem.setChecked(true);
                                startNewActivity();

                            } else if (menuItem.getTitle().toString().equalsIgnoreCase("Take Quiz")) {
                                menuItem.setChecked(true);
                                startNewActivityTakeQuiz();

                            } else {
                                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                                menuItem.setChecked(true);

                            }
                            drawerLayout.closeDrawers();
                            return true;
                        }
                    });
        }

    }

    private void startNewActivity() {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    private void startNewActivityTakeQuiz() {
        Intent intent = new Intent(this, TakeQuizOptions.class);
        startActivity(intent);
    }
}
