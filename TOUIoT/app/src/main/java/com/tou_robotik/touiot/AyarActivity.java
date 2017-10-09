package com.tou_robotik.touiot;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class AyarActivity extends AppCompatActivity {

    private ImageButton ocak1;
    private ImageButton ocak2;
    private ImageButton ocak3;
    private ImageButton ocak4;

    private Button baslat;

    private SeekBar ocak1Seek;
    private SeekBar ocak2Seek;
    private SeekBar ocak3Seek;
    private SeekBar ocak4Seek;

    private TextView ocak1_derece;
    private TextView ocak2_derece;
    private TextView ocak3_derece;
    private TextView ocak4_derece;

    private boolean ocak1Status = false;
    private boolean ocak2Status = false;
    private boolean ocak3Status = false;
    private boolean ocak4Status = false;

    private CheckBox zamanlayici;
    private EditText zamanlayiciStop;

    private CheckBox saat;
    private EditText saatStart;
    private EditText saatStop;

    private static final int BUFFER_SIZE = 4096;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar);

        ocak1 = (ImageButton) findViewById(R.id.ocak1);
        ocak1.setBackgroundColor(Color.RED);
        ocak1Seek = (SeekBar) findViewById(R.id.ocak1Seek);

        ocak2 = (ImageButton) findViewById(R.id.ocak2);
        ocak2.setBackgroundColor(Color.RED);
        ocak2Seek = (SeekBar) findViewById(R.id.ocak2Seek);

        ocak3 = (ImageButton) findViewById(R.id.ocak3);
        ocak3.setBackgroundColor(Color.RED);
        ocak3Seek = (SeekBar) findViewById(R.id.ocak3Seek);

        ocak4 = (ImageButton) findViewById(R.id.ocak4);
        ocak4.setBackgroundColor(Color.RED);
        ocak4Seek = (SeekBar) findViewById(R.id.ocak4Seek);

        zamanlayici = (CheckBox) findViewById(R.id.zamanlayici);
        zamanlayiciStop = (EditText) findViewById(R.id.zamanlayiciStop);

        saat = (CheckBox) findViewById(R.id.saat);
        saatStart = (EditText) findViewById(R.id.saatStart);
        saatStop = (EditText) findViewById(R.id.saatStop);

        ocak1_derece = (TextView) findViewById(R.id.ocak1_derece);
        ocak2_derece = (TextView) findViewById(R.id.ocak2_derece);
        ocak3_derece = (TextView) findViewById(R.id.ocak3_derece);
        ocak4_derece = (TextView) findViewById(R.id.ocak4_derece);

        zamanlayici.setChecked(true);
        saatStart.setEnabled(false);
        saatStop.setEnabled(false);
        zamanlayiciStop.setEnabled(true);

        zamanlayici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saat.setChecked(false);

                saatStart.setEnabled(false);
                saatStop.setEnabled(false);

                zamanlayiciStop.setEnabled(true);

            }
        });

        saat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zamanlayici.setChecked(false);

                saatStart.setEnabled(true);
                saatStop.setEnabled(true);

                zamanlayiciStop.setEnabled(false);
            }
        });

        zamanlayiciStop.setText("00:30");
        baslat = (Button) findViewById(R.id.baslat);

        ocak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ocak1Status) {
                    ocak1Status = false;
                    ocak1.setBackgroundColor(Color.RED);
                    ocak1Seek.setProgress(0);
                } else {
                    ocak1Status = true;
                    ocak1.setBackgroundColor(Color.GREEN);
                }
            }
        });

        ocak1Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    ocak1.setBackgroundColor(Color.RED);
                    ocak1Status = false;
                } else {
                    ocak1.setBackgroundColor(Color.GREEN);
                    ocak1Status = true;
                }
                ocak1_derece.setText(ocak1Seek.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ocak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ocak2Status) {
                    ocak2Status = false;
                    ocak2.setBackgroundColor(Color.RED);
                    ocak2Seek.setProgress(0);
                } else {
                    ocak2Status = true;
                    ocak2.setBackgroundColor(Color.GREEN);
                }
            }
        });

        ocak2Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    ocak2.setBackgroundColor(Color.RED);
                    ocak2Status = false;
                } else {
                    ocak2.setBackgroundColor(Color.GREEN);
                    ocak2Status = true;
                }
                ocak2_derece.setText(ocak2Seek.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ocak3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ocak3Status) {
                    ocak3Status = false;
                    ocak3.setBackgroundColor(Color.RED);
                    ocak3Seek.setProgress(0);

                } else {
                    ocak3Status = true;
                    ocak3.setBackgroundColor(Color.GREEN);
                }
            }
        });

        ocak3Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    ocak3.setBackgroundColor(Color.RED);
                    ocak3Status = false;
                } else {
                    ocak3.setBackgroundColor(Color.GREEN);
                    ocak3Status = true;
                }
                ocak3_derece.setText(ocak3Seek.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ocak4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ocak4Status) {
                    ocak4Status = false;
                    ocak4.setBackgroundColor(Color.RED);
                } else {
                    ocak4Status = true;
                    ocak4.setBackgroundColor(Color.GREEN);
                    ocak4Seek.setProgress(0);
                }
            }
        });

        ocak4Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    ocak4.setBackgroundColor(Color.RED);
                    ocak4Status = false;
                } else {
                    ocak4.setBackgroundColor(Color.GREEN);
                    ocak4Status = true;
                }
                ocak4_derece.setText(ocak4Seek.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        baslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ocak1Value = ocak1Seek.getProgress();
                int ocak2Value = ocak2Seek.getProgress();
                int ocak3Value = ocak3Seek.getProgress();
                int ocak4Value = ocak4Seek.getProgress();

                String start = "00:00";
                String stop = "00:00";
                int status = 0;

                if (zamanlayici.isChecked() && zamanlayiciStop.getText().toString().equals("")) {
                    Toast.makeText(AyarActivity.this, "Lütfen zaman giriniz!", Toast.LENGTH_LONG).show();
                } else if (saat.isChecked() && saatStart.getText().toString().equals("")) {
                    Toast.makeText(AyarActivity.this, "Lütfen başlangıç zamanını giriniz!", Toast.LENGTH_LONG).show();
                } else if (saat.isChecked() && saatStop.getText().toString().equals("")) {
                    Toast.makeText(AyarActivity.this, "Lütfen bitiş zamanını giriniz!", Toast.LENGTH_LONG).show();
                } else {

                    if (ocak1Value == 0 && ocak2Value == 0 && ocak3Value == 0 && ocak4Value == 0) {
                        status = 0;
                    } else if (zamanlayici.isChecked()) {

                        status = 1;
                        Calendar c = Calendar.getInstance();

                        start = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
                        stop = zamanlayiciStop.getText().toString();
                    } else {
                        status = 2;
                        start = saatStart.getText().toString();
                        stop = saatStop.getText().toString();

                        String[] splittedStart = start.split(":");
                        String[] splittedStop = stop.split(":");

                        int startInt = Integer.parseInt(splittedStart[0]) * 3600 +  Integer.parseInt(splittedStart[1]) * 60;
                        int stopInt = Integer.parseInt(splittedStop[0]) * 3600 +  Integer.parseInt(splittedStop[1]) * 60;

                        int diff = stopInt - startInt;

                        int diffHour = diff / 3600;
                        int diffMinute = (diff - (diffHour * 3600)) / 60;

                        stop = diffHour + ":" + diffMinute;

                    }

//                    new RequestTask().execute("http://www.espakilliev.site88.net/tou_robotik/kaydet.php" +
//                            "?status=" + status +
//                            "&start=" + start +
//                            "&stop=" + stop +
//                            "&ocak1=" + ocak1Value +
//                            "&ocak2=" + ocak2Value +
//                            "&ocak3=" + ocak3Value +
//                            "&ocak4=" + ocak4Value
//                    );
                    new RequestTask().execute("http://www.espakilliev.site88.net/tou_robotik2/kaydet.php" +
                                    "?status=" + status +
                                    "&start=" + start +
                                    "&stop=" + stop +
                                    "&ocak1=" + ocak1Value +
                                    "&ocak2=" + ocak2Value +
                                    "&ocak3=" + ocak3Value +
                                    "&ocak4=" + ocak4Value
                                    + "&girilen_sifre=6060986_150213036_150213047"
                    );
                }
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

            if(result.equals("200"))
                Toast.makeText(AyarActivity.this,"Sistem çalışmaya başladı.",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(AyarActivity.this,"Hata oluştu. Lütfen daha sonra tekrar deneyin!",Toast.LENGTH_LONG).show();
        }
    }
/*
    public void uploadFile()
    {
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "espakilliev.site88.net";
        String user = "a1942069";
        String pass = "6060986";
        String filePath = "E:/Work/Project.zip";
        String uploadPath = "/MyProjects/archive/Project.zip";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
*/
}
