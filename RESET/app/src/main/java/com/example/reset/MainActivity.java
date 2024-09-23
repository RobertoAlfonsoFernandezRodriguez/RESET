package com.example.reset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHOR_KEY = "author";
    public static final String QUOTE_KEY = "quote";
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference1 = storage.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("TPV.png");
    FirebaseStorage storage2 = FirebaseStorage.getInstance();
    StorageReference storageReference2 = storage2.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("CCTV.png");
    FirebaseStorage storage3 = FirebaseStorage.getInstance();
    StorageReference storageReference3 = storage3.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("CONSULTORÍA.png");
    FirebaseStorage storage4 = FirebaseStorage.getInstance();
    StorageReference storageReference4 = storage4.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("SERVICIOS.png");
    FirebaseStorage storage5 = FirebaseStorage.getInstance();
    StorageReference storageReference5 = storage5.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("GARANTIAS.png");
    FirebaseStorage storage6 = FirebaseStorage.getInstance();
    StorageReference storageReference6 = storage6.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child("REDES.png");
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
    TextView mQuoteTextView;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;
    TextView t10;

    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView = findViewById(R.id.imageView);

        // Configura el evento onClick
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción a realizar cuando se hace clic en la imagen
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //mQuoteTextView = findViewById(R.id.textView);
        t1 = findViewById(R.id.tv1);
        t2 = findViewById(R.id.tv2);
        t3 = findViewById(R.id.tv3);
        t4 = findViewById(R.id.tv4);


        ImageButton boton1 = findViewById(R.id.ibtn1);
        ImageButton boton2 = findViewById(R.id.ibtn2);
        ImageButton boton3 = findViewById(R.id.ibtn3);
        ImageButton boton4 = findViewById(R.id.ibtn4);

        image1= findViewById(R.id.ibtn1);
        image2= findViewById(R.id.ibtn2);
        image3= findViewById(R.id.ibtn3);
        image4= findViewById(R.id.ibtn4);

     FetchData();
     imagen(image1,storageReference1);
     imagen(image2,storageReference2);
     imagen(image3,storageReference3);
     imagen(image4,storageReference4);



        //agregarBotones();
    }
    public void agregarBotones(){
        // Acceder al LinearLayout existente en el archivo XML
        //LinearLayout miLayout = findViewById(R.id.miLinearLayout);

        // Crear un botón programáticamente
        Button boton = new Button(this);
        boton.setText("Nuevo Botón");

        // Definir el tamaño del botón
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        boton.setLayoutParams(params);

        // Definir el comportamiento del botón al hacer clic
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "¡Botón programático presionado!", Toast.LENGTH_SHORT).show();
            }
        });

        // Agregar el botón al LinearLayout
        //miLayout.addView(boton);
    }


    public void b1 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "TPV");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b2 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "CCTV");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b3 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "CONSULTORIA");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b4 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "SERVICIOS");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b5 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "GARANTIAS");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b6 (View view){
        Intent intent = new Intent(MainActivity.this, Productos.class);

        // Pasar datos utilizando putExtra
        intent.putExtra("CATEGORIA", "REDES");

        // Iniciar la segunda actividad
        startActivity(intent);
    }
    public void b7 (View view){
    }
    public void b8 (View view){
    }
    public void b9 (View view){
    }
    public void b10 (View view){
    //UpdateData();
    //CountData();
    }

    public void imagen (ImageView img, StorageReference st){

        try{
            final File file=File.createTempFile("image","png");
            st.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 425, 425, false);
                    img.setImageBitmap(scaledBitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "no funciona imagen", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void FetchData(){
        //Toast.makeText(MainActivity.this, "no funciona boton", Toast.LENGTH_SHORT).show();
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(MainActivity.this, "solo funciona boton", Toast.LENGTH_SHORT).show();

                if(documentSnapshot.exists())
                {
                    /*for (int i = 1; i <= 10; i++) {
                        String s = documentSnapshot.getString(String.valueOf(String.valueOf(i)));
                        "t" + String.valueOf(i) = setText(s);
                    }*/
                    //InspiringQuote myQuote = documentSnapshot.toObject(InspiringQuote.class);
                    //t1.setText(documentSnapshot.getString("1"));
                    //t2.setText(documentSnapshot.getString("2"));
                    t3.setText(documentSnapshot.getString("3"));
                    t4.setText(documentSnapshot.getString("4"));



                    //mQuoteTextView.setText(s1 + " - "+ s2 + " - "+ s3 );
                    //Toast.makeText(MainActivity.this, "no hay texto", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //Toast.makeText(MainActivity.this, "Quote not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void UpdateData(){
        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        Map<String, Object> datatoUpdate = new HashMap<String,Object>();
        datatoUpdate.put("7", "pokemon");

        mDocRefUpdate.update(datatoUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Documento actualizado correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error actualizando el documento", e);
                    }
                });

    }
    public void CountData()
    {
        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");

        mDocRefUpdate.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Obtener los datos del documento como un mapa
                            Map<String, Object> datos = documentSnapshot.getData();

                            if (datos != null) {
                                // Contar la cantidad de elementos (campos) en el documento
                                int cantidadCampos = datos.size();
                                Log.d("Firestore", "Cantidad de campos: " + cantidadCampos);
                                Toast.makeText(MainActivity.this, String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("Firestore", "El documento no existe");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error obteniendo el documento", e);
                    }
                });
    }
    /*public void saveQuote(View view) {
        EditText quoteView = findViewById(R.id.editTextText);
        EditText authorView = findViewById(R.id.editTextText2);
        String quoteText = quoteView.getText().toString();
        String authorText = authorView.getText().toString();

        if (quoteText.isEmpty() || authorText.isEmpty()) {return;}
        Map<String, Object> dataToSave = new HashMap<String,Object>();
        dataToSave.put(QUOTE_KEY, quoteText);
        dataToSave.put(AUTHOR_KEY, authorText);
        Toast.makeText(MainActivity.this, "algo jala", Toast.LENGTH_SHORT).show();

        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Quote saved", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
