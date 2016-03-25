package ksu.tourpedia.identify.CloudSight;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import ksu.tourpedia.identify.UploadActivity;
import ksu.tourpedia.identify.imgurmodel.ImageResponse;

/**
 * Created by DELL on 07/03/16.
 */


public class CloudMain extends AsyncTask<String,Void,String> { //TODO: Change it to AsyncTask !!

    private static final String API_KEY = "ZbSTE2YuIA-jJzfqfPe9EQ";

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    //static ImageResponse response;


    @Override
    protected String doInBackground(String... url) {

        CSApi api = new CSApi(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                API_KEY
        );
        CSPostConfig imageToPost = CSPostConfig.newBuilder()
                .withRemoteImageUrl(""+url[0])//http://i.imgur.com/xHGpFRL.gif   response.data.link
                .build();

        try {

            CSPostResult portResult = api.postImage(imageToPost);

            // System.out.println("Post result: " + portResult);
            Log.d("debug", "Post result: " + portResult);

            Thread.sleep(30000);

            CSGetResult scoredResult = api.getImage(portResult);

            //  System.out.println(scoredResult)
            Log.d("debug", "" + scoredResult);

             return scoredResult.getStatus();
            }
        catch ( Exception e) {
            //System.out.println("Error");
            Log.d("debug", "Post result exception: " + e.fillInStackTrace());

        }


        return "empty";
    }

  @Override
    protected void onPostExecute(String result){

      UploadActivity.showResults(result);
  }

}
