package com.example.coachinginstitute.magazine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachinginstitute.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {

    private String url;
    private PDFView pdfView;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        setContentView(R.layout.activity_pdf_viewer);
        FloatingActionButton fab = findViewById(R.id.whatsapp_fab);
        fab.setImageResource(R.drawable.ic_whatsappicon_foreground);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "+918449686269"; // Replace with the phone number of the contact you want to chat with
                String message = "Hello, this is a message from my app!"; // Replace with the message you want to send
                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        url = getIntent().getStringExtra("pdfUrl");


        pdfView = findViewById(R.id.pdfView);

        progressBar = findViewById(R.id.pdfProgress);

//        new PdfDownloadToView().execute(url);

        loadFile(url);


    }

    private void loadFile(String url) {

        FileLoader.with(this)
                .load(url)
                .fromDirectory("test4", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        File loadedFile = response.getBody();


                        // do something with the file
                        pdfView.fromFile(loadedFile)
                                .password(null)
                                .defaultPage(0)
                                .enableSwipe(true)
                                .swipeHorizontal(true)
                                .enableDoubletap(true)
                                .spacing(5)
                                .load();

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PdfViewerActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

/*    private class PdfDownloadToView extends AsyncTask<String,Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;

            try {
                URL url =new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }



        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();

        }

    }*/
}