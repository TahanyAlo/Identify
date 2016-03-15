package ksu.tourpedia.identify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Identify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
    }


   public void useMobileCamera(View view){

       Intent intent=new Intent(this,CameraActivity.class);
       startActivity(intent);
   }

  public void useGlassCamera(View view){

      Toast toast=Toast.makeText(this, "Available soon..", Toast.LENGTH_SHORT);
      toast.show();
  }

}
