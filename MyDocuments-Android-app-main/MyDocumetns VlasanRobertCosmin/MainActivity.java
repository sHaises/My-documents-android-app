package com.example.image_to_text20;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.google.android.gms.tasks.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
   /*
    private Button materialBTN;
  private ImageView imagetv;
    private Button recBtn;
    private EditText Textedit;
    private static final String TAG = "MAIN_TAG";
    private Uri imageUri = null;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE=101;
    private String[] cameraPermissions;
    private String[] storagePermissions;
    private ProgressDialog progressDialog;
private Button saveButton;
private TextRecognizer textRecoggnizer;
*/
private Button buletin_button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buletin_button  =findViewById(R.id.Buletin_btn);
        buletin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openBuletin();
            }
        });



       /* materialBTN = findViewById(R.id.TakeBtn);
        recBtn = findViewById(R.id.RecBtn1);
        cameraPermissions = new String[]{android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        imagetv = findViewById(R.id.imageTv);
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        Textedit = findViewById(R.id.textEd);

        textRecoggnizer= TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
materialBTN.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showInputImageDialog();
    }
});



saveButton = findViewById(R.id.savebtn);
saveButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(Textedit.getText().toString().trim().isEmpty())
            Toast.makeText(MainActivity.this, "Nu sau introdus date!", Toast.LENGTH_SHORT).show();
        else {
            File file = new File(getFilesDir(), "Buletin.txt");
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(Textedit.getText().toString() + "\n");
                fileWriter.flush();
                fileWriter.close();

                Toast.makeText(MainActivity.this, "Datlee au fost salvate cu  succes!", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Nu sau putut salva datele!", Toast.LENGTH_SHORT).show();
            }
        }



    }
});
recBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(imageUri == null)
        {
            Toast.makeText(MainActivity.this, "Pick an image", Toast.LENGTH_SHORT).show();

        }
        else {

                recognizeTextfromImage();
        }
    }


});
*/

    }



/*
    private void recognizeTextfromImage(){
        Log.d(TAG, "recognizeTextfromImage: ");
    progressDialog.setMessage("Recognizing text...");
        progressDialog.show();

    try{
        InputImage inputimage = InputImage.fromFilePath(this,imageUri);
        Task<Text> textTaskResult = textRecoggnizer.process(inputimage).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {

                progressDialog.dismiss();

                String recognizeText = text.getText();
                Log.d(TAG, "onSuccess: recognizeTex t"+recognizeText);
                Textedit.setText(recognizeText);



            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Log.e(TAG, "onFailure: ", e);
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });



    } catch (IOException e) {
        progressDialog.dismiss();
        Log.e(TAG, "recognizeTextfromImage: ",e);
        Toast.makeText(this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    }

    private void showInputImageDialog(){
        PopupMenu popup = new PopupMenu(this,materialBTN);
        popup.getMenu().add(Menu.NONE,1,1,"CAMERA");
        popup.getMenu().add(Menu.NONE,2,2,"Gallery");
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if(id == 1){
                        Log.d(TAG, "onMenuItemClick: Camera Clicked...");
                        if(checkCameraPermission()){
                            pickImageCamera();
                        }
                        else {
                            requestCameraPermissions();

                        }

                    } else if (id == 2 ) {
                        Log.d(TAG, "onMenuItemClick: Gallery Clicked:");
                        if(checkStoragePermission()){

                        pickImageGallery();

                        }
                        else
                        {
                            requestStoragePwrmission();
                        }

                    }


                return true;
            }
        });




    }

    private void pickImageGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);



    }

    private ActivityResultLauncher<Intent>galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        imagetv.setImageURI(imageUri);

                    }
                    else {

                        Toast.makeText(MainActivity.this,"Canceleed...",Toast.LENGTH_SHORT).show();

                    }
                }
            }
    );
    public void pickImageCamera()
    {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Sample Titile");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Sample description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        cameraActivityResultLauncher.launch(intent);


    }

    private  ActivityResultLauncher<Intent>cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {


                    if(result.getResultCode()==Activity.RESULT_OK)
                    {
                        imagetv.setImageURI(imageUri);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Canceleed...",Toast.LENGTH_SHORT).show();

                    }

                }
            }
    );
    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePwrmission()
    {

        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);

    }
private boolean checkCameraPermission(){

        boolean cameraResult = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean storageResult = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return cameraResult && storageResult;
}

private void requestCameraPermissions()
{
    ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);

}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted)
                    {
                        pickImageCamera();
                    }
                    else
                    {
                        Toast.makeText(this,"Camera & Storage permission",Toast.LENGTH_SHORT).show();

                    }
                }
                else {

                    Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show();
                }


            }
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickImageGallery();
                    }
                    else {
                        Toast.makeText(this, "Storage permision is requierd", Toast.LENGTH_SHORT).show();

                    }




                }

            }

        }

    }





*/


    public void openBuletin(){
        Intent intent1 = new Intent(this,Buletin.class);
        startActivity(intent1);

    }

}