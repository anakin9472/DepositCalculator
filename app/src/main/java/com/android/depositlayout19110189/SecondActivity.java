package com.android.depositlayout19110189;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String profit = intent.getStringExtra("profitKey");
        String totalMoney = intent.getStringExtra("totalMoney");
        EditText profitMoney = (EditText)findViewById(R.id.editMoneyReceive);
        EditText totalMoneyReceive = (EditText)findViewById(R.id.editTextTotalMoney);
        profitMoney.setText(profit);
        totalMoneyReceive.setText(totalMoney);

        btn = findViewById(R.id.buttonTakePicture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent1 = new Intent();
                    intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


}