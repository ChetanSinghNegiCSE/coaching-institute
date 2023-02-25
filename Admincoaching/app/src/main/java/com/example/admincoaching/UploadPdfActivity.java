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
import java.util.HashMap;

public class UploadPdfActivity extends AppCompatActivity {



    private CardView addPdf;
    private Spinner ebookCategory;
    private String category;
    private final int REQ =1;

    private Uri pdfData;


    private EditText pdfTitle;

    private Button uploadPdfBtn;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadUrl = "";
    private ProgressDialog pd;
    private TextView pdfTextView;
    private String pdfName , Title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Monthly Magazine");
        storageReference = FirebaseStorage.getInstance().getReference().child("Monthly Magazine");

        pd = new ProgressDialog(this);

        addPdf =findViewById(R.id.addPdf);
        pdfTitle = findViewById(R.id.pdfTitle);
        uploadPdfBtn=findViewById(R.id.uploadPdfBtn);
        pdfTextView = findViewById(R.id.pdfTextView);
        ebookCategory = findViewById(R.id.ebookCategory);

        //Start Spinner
        String[] items = new String[]{"Select Category", "General"
                ,"Defence","JEE","NEET","SSC","Banking","Group C(UK)"};
        ebookCategory.setAdapter(new ArrayAdapter<String>(this
                , android.R.layout.simple_spinner_dropdown_item,items));

        ebookCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = ebookCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //end Spinner

        addPdf.setOnClickListener(view -> openGallery());
        uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = pdfTitle.getText().toString();
                if(Title.isEmpty()){
                    pdfTitle.setError("Empty");
                    pdfTitle.requestFocus();
                }else if (pdfData == null){
                    Toast.makeText(UploadPdfActivity.this, "Please Select pdf", Toast.LENGTH_SHORT).show();
                } else  if(category.equals("Select Category")){
                    Toast.makeText(UploadPdfActivity.this, "Please Select Ebook Sem", Toast.LENGTH_SHORT).show();
                }else {
                    uploadPdf();

                }
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please Wait...");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference = storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        {
                            Uri uri = uriTask.getResult();
                            uploadData(String.valueOf(uri));


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(UploadPdfActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadUrl) {
        databaseReference = databaseReference.child(category);
        final String uniqueKey = databaseReference.push().getKey();
        //child("pdf").
        HashMap data = new HashMap();
        data.put("pdfTitle",Title);
        data.put("pdfUrl",downloadUrl);

        databaseReference.child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            //.child("pdf")
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Pdf Uploaded Successfully", Toast.LENGTH_SHORT).show();
                pdfTitle.setText("");
               /* PushNotification notification = new PushNotification(new NotificationData(pdfTitle.getText().toString(),"New Ebook is Uploaded Check it", R.id.titleNoti), TOPIC);
                sendNotification(notification);*/
                startActivity(new Intent(UploadPdfActivity.this, UploadPdfActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Filed to Upload pdf", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent =new Intent();
        intent.setType("application/pdf"); //*/* = for all type of document
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ);
    }


    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            pdfData = data.getData();
            Toast.makeText(this, "pdf Selected", Toast.LENGTH_SHORT).show();
            if(pdfData.toString().startsWith("content://")){
                Cursor cursor =null;
                try {
                    cursor= UploadPdfActivity.this.getContentResolver().query(pdfData,null,null,null,null);
                    if(cursor!=null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (pdfData.toString().startsWith("file://")){
                pdfName = new File(pdfData.toString()).getName();
            }

            pdfTextView.setText(pdfName);
        }
    }
/*
    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UploadPdfActivity.this, "success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UploadPdfActivity.this, "error ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {

                Toast.makeText(UploadPdfActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
*/

}
