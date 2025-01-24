package com.example.image_to_text20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Buletin extends AppCompatActivity {
private Button add_buletin;
private Button delete_informatii;
private Button vizualizare_buletin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buletin);
        add_buletin = findViewById(R.id.adauga_datebtn);
        delete_informatii = findViewById(R.id.delete_btn_buletin);
        vizualizare_buletin = findViewById(R.id.vizualizare_date_btn);

        add_buletin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });

        delete_informatii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete Notes?");
                builder.setMessage("Are you sure you want to delete it??");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            File file = new File(getFilesDir(), "Buletin.txt");
                            FileWriter fileWriter = new FileWriter(file);
                            fileWriter.write("");
                            fileWriter.flush();
                            fileWriter.close();

                            Toast.makeText(Buletin.this, "The date was deleted susccesfully!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e){
                            Toast.makeText(Buletin.this, "The data counld be deleted!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();


            }

        });

        vizualizare_buletin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBuletinVizualizare();
            }
        });





    }

    public void openBuletinVizualizare(){
        Intent intent = new Intent(this,VizualizareBuletin.class);
        startActivity(intent);



    }
    public void openAdd(){

            Intent intent1 = new Intent(this,Buletin_add.class);
            startActivity(intent1);



    }
}