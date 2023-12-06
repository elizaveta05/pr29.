package com.example.pr29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4;
    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;
    final int CAMERA_REQUEST = 1;
    final int PIC_CROP = 2;
    private Uri picUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(this);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(this);

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(this);

        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageButton1){
            proto();
        }else if (view.getId()==R.id.imageButton2){
            web();
        }else if (view.getId()==R.id.imageButton3){
            telephone();
        }else if (view.getId()==R.id.imageButton4){
            map();
        }
    }

    private void map(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:55.044009,82.917108"));
        startActivity(intent);
    }
    private void telephone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:89231327040"));
        startActivity(intent);
    }

    private void web() {

        Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.yandex.com"));
        startActivity(intent);

    }

    private void proto() {
        try {
            // Намерение для запуска камеры
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, CAMERA_REQUEST);
        } catch (ActivityNotFoundException e) {
            // Выводим сообщение об ошибке
            String errorMessage = "Ваше устройство не поддерживает съемку";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Вернулись от приложения Камера
            if (requestCode == CAMERA_REQUEST) {
                // Получим Uri снимка
                if (data != null && data.getExtras() != null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    // Примените полученное изображение к ImageView
                    ImageView imageView1 = findViewById(R.id.imageView);
                    imageView1.setImageBitmap(photo);
                }
            }
        }
    }



}