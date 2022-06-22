package com.example.nasapicturesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.HashMap;

public class ImageDetailScreen extends AppCompatActivity {

    HashMap<Integer,PictureModel> pictureModelMap;
    int position;
    PictureModel pictureModel;
    // creating object of ViewPager
    ViewPager mViewPager;
    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;
    TextView infoText;
    SlidingUpPanelLayout slidingUpPanelLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_detail_screen);
        infoText= findViewById(R.id.infoTextView);

        slidingUpPanelLayout = findViewById(R.id.slidingUpPanel);
        Intent intent = getIntent();
        position  =  intent.getIntExtra("ImageUrl",0);
        Log.e("CHECK",""+position);
        pictureModelMap = (HashMap<Integer, PictureModel>) intent.getSerializableExtra("PictureModelMap");

        pictureModel = pictureModelMap.get(position);
        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.pager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(this, pictureModelMap);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(position);
        infoText.setText(showInfo());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int mposition, float positionOffset, int positionOffsetPixels) {
                position = mposition ;
                infoText.setText(showInfo());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
});



        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {


            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED){

                    infoText.setText(showInfo());

                }
            }
        });



    }

    private StringBuilder showInfo() {
        Log.e("SHOWDETAILS","showInfoDialog");
        pictureModel = pictureModelMap.get(position);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Image Information");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Title:- "+pictureModel.getTitle());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("CopyRight:- "+pictureModel.getCopyright());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Date:- "+pictureModel.getDate());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Explanation :- " +pictureModel.getExplanation());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Media Type :- " +pictureModel.getMedia_type());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Hd Url:- " +pictureModel.getHdurl());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Picture Name :- " +pictureModel.getPicture_name());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Service Version :- " +pictureModel.getService_version());

        return stringBuilder;
    }

    public void onResume(){
        super.onResume();

    }



}