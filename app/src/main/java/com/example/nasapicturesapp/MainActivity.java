package com.example.nasapicturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView coursesGV;
    HashMap<Integer,PictureModel> pictureModelMap;
    PictureModel pictureModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesGV = findViewById(R.id.idGVcourses);




    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO check the internet connection first
        JsonParser jsonParser = new JsonParser();
        String json = jsonParser.getJSON(this);
        JSONArray req = null;
        pictureModelMap = new HashMap<>();
        try {
            req = new JSONArray(json);
            ArrayList<PictureModel> courseModelArrayList = new ArrayList<PictureModel>();


        for (int i = 0; i < req.length(); ++i) {
            JSONObject rec = req.getJSONObject(i);
           // String copyright = rec.getString("copyright");
            pictureModel = new PictureModel();
            pictureModel.setUrl(rec.getString("url"));
            if (rec.has("copyright")) {
                pictureModel.setCopyright(rec.getString("copyright"));
            }
            pictureModel.setDate(rec.getString("date"));
            pictureModel.setExplanation(rec.getString("explanation"));
            pictureModel.setHdurl(rec.getString("hdurl"));
            pictureModel.setMedia_type(rec.getString("media_type"));
            pictureModel.setService_version(rec.getString("service_version"));
            pictureModel.setTitle(rec.getString("title"));

            courseModelArrayList.add(pictureModel);

            pictureModelMap.put(i,pictureModel);
        }



            GridAdapter adapter = new GridAdapter(this, courseModelArrayList);
            coursesGV.setAdapter(adapter);
            coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("JSON_CHECK ","position "+position);

                    Intent intent = new Intent(MainActivity.this, ImageDetailScreen.class);
                    intent.putExtra("ImageUrl",position);
                    intent.putExtra("PictureModelMap",pictureModelMap);
                    startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}