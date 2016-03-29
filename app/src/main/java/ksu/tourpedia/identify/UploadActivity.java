package ksu.tourpedia.identify;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;

import ksu.tourpedia.identify.imgurmodel.Upload;

public class UploadActivity extends AppCompatActivity {

    File image;
    private Upload upload; // Upload object containing image and meta data
    public Context context=this;
    public static ViewFlipper viewFlipper;

    UploadStart uploadStart ;
    public static TextView message;
    public static TextView  description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_activity);
        viewFlipper =(ViewFlipper)findViewById(R.id.viewFlipper);

        message=(TextView) findViewById(R.id.uploadingMessage);
        description=(TextView) findViewById(R.id.textView);


        uploadStart = new UploadStart(context);
        uploadStart.uploadImage();



    }





}
