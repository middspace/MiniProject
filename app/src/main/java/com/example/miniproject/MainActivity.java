package com.example.miniproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView pic;
    Button select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pic = findViewById(R.id.IVPic);
        select = findViewById(R.id.btnLoadImg);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImg();
            }
        });
    }


    public void loadImg(){
        ActivityResultLauncher<String> selectPhoto = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        try {
                            Bitmap selectedImg = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            pic.setImageBitmap(selectedImg);
                        }
                        catch(IOException e){
                            Log.e("IMAHE SELECTION ERROR: ", e.toString());
                        }
                    }
                }
        );
        pickString();
    }

    private String pickString(){
        String[] stgArray = {"Horse", "Bird", "T-Shirt",
                "What", "Indescribable", "That's not anything",
                "Chair", "idk", "Cant tell you", "Go away"};

        Random random = new Random();
        int randNum = random.nextInt(10) + 1;

        return stgArray[randNum];
    }
}