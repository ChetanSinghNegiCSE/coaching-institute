package com.example.admincoaching;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPapers extends AppCompatActivity {
    private Spinner paperCategory; //imageCategory;
    private CardView addPreviousPaper;
    private String category;

    private final int REQ = 1;

    private Uri paperData; //pdfData;

    private EditText paperTitle; //pdfTitle;

    private Button uploadPaperBtn; //uploadPdfBtn;
    private DatabaseReference reference;
    private StorageReference storageReference;
    String downloadUrl = "";//
    private ProgressDialog pd;
    private TextView paperTextView; // pdfTextView;
    private String pdfName, Title;
    private String decodedFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_papers);
        reference = FirebaseDatabase.getInstance().getReference().child("Previous Papers");
        storageReference = FirebaseStorage.getInstance().getReference().child("Previous Papers");

        pd = new ProgressDialog(this);


        addPreviousPaper = findViewById(R.id.uploadPreviousPaper);
        paperTitle = findViewById(R.id.paperTitle);
        uploadPaperBtn = findViewById(R.id.uploadPaperBtn);
        paperTextView = findViewById(R.id.paperTextView);
        paperCategory = findViewById(R.id.paperCategory);

        //Start Spinner
        String[] items = new String[]{"Select Category"
                ,"Defence","JEE","NEET","SSC","Banking","Group C(UK)", "Other Exam"};
        paperCategory.setAdapter(new ArrayAdapter<String>(this
                , android.R.layout.simple_spinner_dropdown_item, items));

        paperCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = paperCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //end Spinner

        addPreviousPaper.setOnClickListener(view -> openGallery());
        uploadPaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = paperTitle.getText().toString();
                if (Title.isEmpty()) {
                    paperTitle.setError("Empty");
                    paperTitle.requestFocus();
                } else if (paperData == null) {
                    Toast.makeText(UploadPapers.this, "Please Upload paper", Toast.LENGTH_SHORT).show();
                } else if (category.equals("Select Category")) {
                    Toast.makeText(UploadPapers.this, "Please Select Paper Category", Toast.LENGTH_SHORT).show();
                } else {
                    uploadPaper();

                }
            }
        });
    }

    private void uploadPaper() {
        pd.setTitle("Please Wait...");
        pd.setMessage("Uploading paper");
        pd.show();
        StorageReference reference = storageReference.child("Previous Year Papers /" + decodedFileName + "-" + System.currentTimeMillis() + ".pdf");
        reference.putFile(paperData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete()) ;
                        {
                            Uri uri = uriTask.getResult();
                            uploadData(String.valueOf(uri));


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(UploadPapers.this, "Something want wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadUrl) {
        reference = reference.child(category);
        String uniqueKey = reference.push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle", Title);
        data.put("pdfUrl", downloadUrl);

        reference.child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadPapers.this, "Paper Uploaded Successfully", Toast.LENGTH_SHORT).show();
                paperTitle.setText("");
              /*  PushNotification notification = new PushNotification(new NotificationData(paperTitle.getText().toString(), "New Previous Paper is Uploaded Check it", R.id.titleNoti), TOPIC);
                sendNotification(notification);*/
                startActivity(new Intent(UploadPapers.this, UploadPapers.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPapers.this, "Filed to Upload paper", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf"); //*/* =  All type of document
        intent.setAction(Intent.ACTION_GET_CONTENT);
       /* Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select txt file"),
                    0);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog

        }*/

        startActivityForResult(Intent.createChooser(intent, "Select Paper "), REQ);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            paperData = data.getData();
            Toast.makeText(this, "Paper Selected", Toast.LENGTH_SHORT).show();
            if (paperData.toString().startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = UploadPapers.this.getContentResolver().query(paperData, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (paperData.toString().startsWith("file://")) {
                pdfName = new File(paperData.toString()).getName();
                try {
                     decodedFileName = URLDecoder.decode(pdfName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            paperTextView.setText(pdfName);

        }
    }

/*    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UploadPapers.this, "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UploadPapers.this, "error ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {

                Toast.makeText(UploadPapers.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }*/

}