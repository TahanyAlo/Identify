package ksu.tourpedia.identify;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

//public class WikiJsoup extends AppCompatActivity {


// @Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiki_layout);

        
        
        
        new summary().execute();
    }*/
public class Summary extends AsyncTask<Void, Void, Void> {
    String s;
    public static String url ="";
    TextView t;
    String result;

    @Override

    protected void onPreExecute(){
       // UploadActivity.setContentView(R.layout.wiki_layout);
        t = WikiJsoup.t;

        super.onPreExecute();
    }
    @Override

    protected Void doInBackground (Void... params){
        try {
            Document doc = Jsoup.connect(url).get();
            s = doc.title()+"\n";
            for ( Element table : doc.select("table.infobox")){
                //  Elements rows = table.getElementsByTag("tr");
                for (Element row : table.getElementsByTag("tr")){
                    //  Elements ths = row.getElementsByTag("th");
                    for (Element th : row.getElementsByTag("th"))
                        s += th.text() + ":\n";

                    Elements tds = row.getElementsByTag("td");
                    if (tds.size() == 0)
                        s+= "================== \n";
                    else
                        for (Element td : tds)
                            s += td.text()+"\n";
                    // else
                    //     s += "--------------------------- \n";



                }}




            //Element e = doc.select("#firstHeading").first();
            // for(int i=0 ; i<e.size() ; i++)
            // s+= "\n" + e.get(i).text();
            //s = ""+e;
            //t.setText(e.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid){
        t.setText(s);
       // UploadActivity.showResults(result);
        //super.onPostExecute(aVoid);
    }
}


//}