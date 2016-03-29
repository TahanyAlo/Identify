package ksu.tourpedia.identify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by DELL on 26/03/16.
 */
public class WikiJsoup extends AppCompatActivity {

    public static TextView t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiki_layout);
        t = (TextView) findViewById(R.id.textView);

    }}
