package quiz.mobile.hiliti.com.hiltimobileapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;
import quiz.mobile.hiliti.com.hiltimobileapp.widget.CustomNetworkImageView;

public class ToolTrainingActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "quiz.mobile.hiliti.com.hiltimobileapp.extraImage";
    private static final String EXTRA_TITLE = "quiz.mobile.hiliti.com.hiltimobileapp.extraTitle";
    private static final String EXTRA_FEATURE = "quiz.mobile.hiliti.com.hiltimobileapp.extraFeature";
    private static final String EXTRA_APPLICATION = "quiz.mobile.hiliti.com.hiltimobileapp.extraApplication";
    ImageLoader mImageLoader;
    VolleySingleton volleySingleton;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static void navigate(AppCompatActivity activity, View transitionImage, TrainingPojo trainingPojo) {
        Intent intent = new Intent(activity, ToolTrainingActivity.class);
        intent.putExtra(EXTRA_IMAGE, trainingPojo.getImageRes());
        intent.putExtra(EXTRA_TITLE, trainingPojo.getTitle());
        intent.putExtra(EXTRA_FEATURE,trainingPojo.getFeatures());
        intent.putExtra(EXTRA_APPLICATION,trainingPojo.getApplications());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_tool_training);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolTraining));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.primary_text), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        String imageRes = getIntent().getStringExtra(EXTRA_IMAGE);
        String feature = getIntent().getStringExtra(EXTRA_FEATURE);
        //feature = feature.replace(".", System.getProperty("line.separator"));
        String applications = getIntent().getStringExtra(EXTRA_APPLICATION);
        //applications = applications.replace(".", System.getProperty("line.separator"));
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mImageLoader = volleySingleton.getImageLoader();
        final ImageView imageView = (ImageView) findViewById(R.id.toolImage);
        setImageAsBits(mImageLoader,imageView,imageRes);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                applyPalette(palette);
            }});
        TextView toolTitle = (TextView) findViewById(R.id.toolTitle);
        TextView features = (TextView) findViewById(R.id.features);
        TextView application = (TextView) findViewById(R.id.applications);
        toolTitle.setText(itemTitle);
        features.setText(feature);
        application.setText(applications);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        supportStartPostponedEnterTransition();
    }

    private void setImageAsBits(ImageLoader imageLoader, final ImageView imageView, final String imageUrl){
        imageLoader.get(imageUrl,new ImageLoader.ImageListener()
        {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b)
            {
                if (imageContainer.getBitmap() != null)
                {
                    imageView.setImageBitmap(imageContainer.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        });
    }

}
