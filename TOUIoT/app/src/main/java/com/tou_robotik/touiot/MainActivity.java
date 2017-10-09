package com.tou_robotik.touiot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {

    private Button ocak;
    private Button klima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocak = (Button) findViewById(R.id.ocak_button);
        klima = (Button) findViewById(R.id.klima_button);

        new RequestTask().execute("http://www.espakilliev.site88.net/tou_robotik/kaydet.php" +
                "?status=" + 0 +
                "&start=" + 0 +
                "&stop=" + 0 +
                "&ocak1=" + 0 +
                "&ocak2=" + 0 +
                "&ocak3=" + 0 +
                "&ocak4=" + 0);

        ocak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AyarActivity.class);
                startActivity(intent);
            }
        });
    }

    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();

                    responseString = "200";

//                    Toast.makeText(AyarActivity.this,"Sistem çalışmaya başladı.",Toast.LENGTH_LONG).show();
                } else{
//                    Toast.makeText(AyarActivity.this,"Hata oluştu! Lütfen tekrar deneyin.",Toast.LENGTH_LONG).show();

                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());


                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }
}
