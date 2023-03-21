package com.example.admincoaching.key;

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

import com.example.admincoaching.R;
import com.example.admincoaching.UploadPapers;
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

public class AddAnsKey extends AppCompatActivity {
    private String  category,title,pdfName;

    private CardView addPreviousPaper;


    private final int REQ = 1;

    private Uri paperData; //pdfData;



    private Button uploadPaperBtn; //uploadPdfBtn;
    private DatabaseReference reference;
    private StorageReference storageReference;
    String downloadUrl = "";//
    private ProgressDialog pd;
    private TextView paperTextView; // pdfTextView;
    /*private String pdfName, Title;*/
    private String decodedFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ans_key);

        category=getIntent().getStringExtra("category");
        title = getIntent().getStringExtra("ansTitle");
        reference = FirebaseDatabase.getInstance().getReference().child("Answer Key");
        storageReference = FirebaseStorage.getInstance().getReference().child("Answer Key");

        pd = new ProgressDialog(this);


        addPreviousPaper = findViewById(R.id.uploadPreviousPaper);
        uploadPaperBtn = findViewById(R.id.uploadPaperBtn);
        paperTextView = findViewById(R.id.paperTextView);



        addPreviousPaper.setOnClickListener(view -> openGallery());
        uploadPaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (paperData == null) {
                    Toast.makeText(AddAnsKey.this, "Please Select  Answer", Toast.LENGTH_SHORT).show();
                }else {
                    uploadPaper();

                }
            }
        });
    }

    private void uploadPaper() {
        pd.setTitle("Please Wait...");
        pd.setMessage("Uploading paper");
        pd.show();
        StorageReference reference = storageReference.child("Answer_Key /" + decodedFileName + "-" + System.currentTimeMillis() + ".pdf");
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
                        Toast.makeText(AddAnsKey.this, "Something want wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadUrl) {
        reference = reference.child(category);
        String uniqueKey = reference.push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle", title);
        data.put("pdfUrl", downloadUrl);

        reference.child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(AddAnsKey.this, "Paper Uploaded Successfully", Toast.LENGTH_SHORT).show();
                /*paperTitle.setText("");*/
              /*  PushNotification notification = new PushNotification(new NotificationData(paperTitle.getText().toString(), "New Previous Paper is Uploaded Check it", R.id.titleNoti), TOPIC);
                sendNotification(notification);*/
                startActivity(new Intent(AddAnsKey.this, AddAnsKey.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AddAnsKey.this, "Filed to Upload paper", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Answer key Selected", Toast.LENGTH_SHORT).show();
            if (paperData.toString().startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = AddAnsKey.this.getContentResolver().query(paperData, null, null, null, null);
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