package com.example.miniproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView pic;
    Button select;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the elements and assigning them to variables
        pic = findViewById(R.id.IVPic);
        select = findViewById(R.id.btnLoadImg);
        text = findViewById(R.id.tvGuess);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sets the click event for load image button
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"Pick an image"), 1);

            }

        });
    }

    //this method handles grabbing the image and converting it into a bitmap, also displays the string
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                pic.setImageBitmap(bitmap); //sets the image view to be the bitmap created from the user's image
                text.setText(pickString()); //picks and sets the text to be the randoms string

            } catch (FileNotFoundException e) {
                System.out.println("Here!\n"); //debugging
                e.printStackTrace();
            }
        }

    }


    //helper method for picking a random string
    private String pickString(){
        String[] stgArray = {"Horse", "Bird", "T-Shirt",
                "What", "Indescribable", "That's not anything",
                "Chair", "idk", "Cant tell you", "Go away"};

        Random random = new Random();
        int randNum = random.nextInt(10) + 1;

        return stgArray[randNum];
    }
}