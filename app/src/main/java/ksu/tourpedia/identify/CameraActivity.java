package ksu.tourpedia.identify;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 11/03/16.
 */
public class CameraActivity extends AppCompatActivity {


    File pictureFile;
    public static final int MEDIA_TYPE_IMAGE = 1;
    Uri fileUri;

    private Camera mCamera;
    private CameraPreview mPreview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera);
        setContentView(R.layout.camera_preview);


        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);


        //captureImage();
    }


    public void captureImage(View v) {
        // get an image from the camera
        mCamera.takePicture(null, null, mPicture);
           }

    public void uploadImage(){
        //TODO: upload activity call
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    private PictureCallback mPicture = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            pictureFile = ImageHandler.saveImage(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                Log.d("debug", "Error creating media file, check storage permissions! ");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                uploadImage();
            } catch (FileNotFoundException e) {
                Log.d("debug", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("debug", "Error accessing file: " + e.getMessage());
            }
        }
    };

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            Log.d("debug","getCameraInstance(): Camera is not available (in use or does not exist)");

        }
        return c; // returns null if camera is unavailable
    }

}