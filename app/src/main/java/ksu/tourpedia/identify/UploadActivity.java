package ksu.tourpedia.identify;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ksu.tourpedia.identify.CloudSight.CloudMain;
import ksu.tourpedia.identify.imgurmodel.ImageResponse;
import ksu.tourpedia.identify.imgurmodel.Upload;
import ksu.tourpedia.identify.services.UploadService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UploadActivity extends AppCompatActivity {

    File image;
    private Upload upload; // Upload object containing image and meta data
    Context context=this;


    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_activity);

        message=(TextView) findViewById(R.id.uploadingMessage);
        image = ImageHandler.imageFile;//TODO: find a better way to pass the file since this looks unreliable.
        uploadImage();
    }


    public void uploadImage() {
    /*
      Create the @Upload object
     */
        if (image == null){
            Log.d("debug","The image is empty");
            return;}
        createUpload(image);

    /*
      Start upload
     */
        new UploadService(this).Execute(upload, new UiCallback());
    }

    private void createUpload(File image) {
        upload = new Upload();

        upload.image = image;
        // upload.title = uploadTitle.getText().toString();
        // upload.description = uploadDesc.getText().toString();
    }

    private class UiCallback implements Callback<ImageResponse> {

        @Override
        public void success(ImageResponse imageResponse, Response response) {
            // clearInput();
            new CloudMain(imageResponse);

            message.setText("Sorry :( GO search for Log with Tag (debug) to see results!! ");
        }

        @Override
        public void failure(RetrofitError error) {
            //Assume we have no connection, since error is null
            if (error == null) {
                // Snackbar.make(findViewById(R.id.rootView), "No internet connection", Snackbar.LENGTH_SHORT).show();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, "No internet connection", duration);
                toast.show();
            }
        }
    }

}
