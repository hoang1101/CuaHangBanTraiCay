package com.example.cuahangbantraicay.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cuahangbantraicay.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;


public class PDF extends AppCompatActivity {
    private Button btnXuat;
    String[] infor = new String[]{"Name","Company Name","Address","Phone","Email"};

    Bitmap bitmap,scaleBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pdf);
        btnXuat = findViewById(R.id.xuatfile);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ngocanh);

        scaleBitmap = Bitmap.createScaledBitmap(bitmap,200,200,false);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF();
    }

    private void createPDF() {
        btnXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PdfDocument myPdfDocument= new PdfDocument() ;
                Paint myPaint = new Paint();
                PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250,400,1).create();
                PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

                Canvas canvas =myPage.getCanvas();

//
//                myPaint.setTextAlign(Paint.Align.CENTER);
//                myPaint.setTextSize(12.0f);
//                canvas.drawText("Thong ke san pham",myPageInfo.getPageWidth()/2,30,myPaint);
//
//                myPaint.setTextSize(6.0f);
//                myPaint.setColor(Color.rgb(122,119,119));
//                canvas.drawText("Thang : 4",myPageInfo.getPageWidth()/2,40,myPaint);
//
//
//                myPaint.setTextAlign(Paint.Align.LEFT);
//                myPaint.setTextSize(9.0f);
//                myPaint.setColor(Color.rgb(122,119,119));
//                canvas.drawText("Customer",10,70,myPaint);
//
//                myPaint.setTextAlign(Paint.Align.LEFT);
//                myPaint.setTextSize(9.0f);
//                myPaint.setColor(Color.rgb(122,119,119));
//
//                int startXposition =10;
//                int endXposition =myPageInfo.getPageWidth()-10;
//                int startYposition =100;
//
//                for (int i = 0; i < 5; i++) {
//                    canvas.drawText(infor[i],startXposition,startYposition,myPaint);
//                    canvas.drawLine(startXposition,startYposition+3,endXposition,startYposition+3,myPaint);
//                    startYposition+=20;
//                }
//
//                myPaint.setStyle(Paint.Style.STROKE);
//                canvas.drawRect(10,200,myPageInfo.getPageWidth()-10,300,myPaint);
//                myPaint.setStrokeWidth(0);
//                myPaint.setStyle(Paint.Style.FILL);


                canvas.drawBitmap(scaleBitmap,40,50,myPaint);

                myPdfDocument.finishPage(myPage);





                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "/PDFs/hihi.pdf");


                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(getApplicationContext(), "File exported to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                myPdfDocument.close();
            }
        });
    }
}
