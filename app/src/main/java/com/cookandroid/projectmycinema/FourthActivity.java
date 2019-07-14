package com.cookandroid.projectmycinema;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;

public class FourthActivity extends AppCompatActivity {

    Button btnback;
    TextView testtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);

        Intent intent = getIntent();
        testtv = (TextView) findViewById(R.id.testtv);
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        testtv.setMovementMethod(new ScrollingMovementMethod());

        String path="/data/data/com.cookandroid.projectmycinema/files";
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();

        for(File tempFile : fileList) {
            if(tempFile.isFile()) {
                String tempPath=tempFile.getParent();
                String tempFileName=tempFile.getName();
                System.out.println("FileName="+tempFileName);

                FileInputStream fis = null;
                try {
                    fis = openFileInput(tempFileName);
                    byte[] fileData = new byte[fis.available()];
                    fis.read(fileData);
                    fis.close();
                    String str = new String(fileData, "EUC-KR");
                    String line="------------------------------------------------------------------------------------------";
                    testtv.append(tempFileName+"\n"+str+"\n"+line+"\n");

                } catch (Exception e) { // UnsupportedEncodingException , FileNotFoundException , IOException

                    System.out.println("일기없음");
                    e.printStackTrace();
                }

            }
        }


    }
}

