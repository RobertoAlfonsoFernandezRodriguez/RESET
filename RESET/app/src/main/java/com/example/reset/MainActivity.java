package com.example.reset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import androidx.core.content.ContextCompat;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageButton> imageButtonList;
    private int contadorclics=0;
    private int vecescontadass=0;
    int cantidadCampos;
    int cantidadCamposCat;
    int IDBoton=0;
    boolean par;
    ArrayList<Object> elementList = new ArrayList<>();


    ImageView image1, image2, image3, image4;
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
        //nombre imagen de reset
        ImageView imageView = findViewById(R.id.imageView);

        // Configura el evento onClick de imagen de reset
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contadorclics ==5)
                {
                    // abrir otra activity
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                contadorclics++;

            }
        });
        //modo oscuro desactivado
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        image1= findViewById(R.id.ibtn1);
        image2= findViewById(R.id.ibtn2);
        image3= findViewById(R.id.ibtn3);
        image4= findViewById(R.id.ibtn4);

        //FetchData();
        imagen(image1,"TPV");
        imagen(image2,"CCTV");


        // Obtener el LinearLayout dentro del ScrollView


        //LinearLayout linearLayoutParent = findViewById(R.id.containerLayout);
        //botones();
        //agregarBotones();
        /*for (int i = 1; i <= 6; i++) {
            createComplexLayout(linearLayoutParent, i);
        }*/

        // Inicializar la lista de ImageButtons
        imageButtonList = new ArrayList<>();

        // Obtener el contenedor (LinearLayout) dentro del ScrollView
        LinearLayout containerLayout = findViewById(R.id.containerLayout);

        // Número de layouts que deseas crear

        //CountData();


        do {
            try {
                CountData();
                Toast.makeText(MainActivity.this, "Exito"+String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                // Espera antes de volver a intentar
                try {
                    Thread.sleep(10); // 1 segundo de espera
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        } while (cantidadCampos > 0 || vecescontadass>100); // Repite mientras no haya éxito

        //Toast.makeText(MainActivity.this, "Exito"+String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
        FetchData();







        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");

        // Intentas obtener los datos de Firestore de manera asíncrona
        mDocRefUpdate.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // Aquí ya tienes el documento obtenido correctamente
                if (documentSnapshot.exists()) {
                    // Obtén los datos del documento, por ejemplo:
                    String categoria = documentSnapshot.getString("1");
                    //Toast.makeText(MainActivity.this, "El valor de countdata es"+categoria, Toast.LENGTH_SHORT).show();
                    CountData();
                    //Toast.makeText(MainActivity.this, "El valor de countdata es" + String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
                    if (cantidadCampos > 0){
                        FetchData();


                        if (cantidadCampos % 2 == 0) {
                            for (int i = 0; i < ((cantidadCampos/2)); i++) {

                                LinearLayout horizontalLayout = createButtonLayout(); // Crear el layout
                                containerLayout.addView(horizontalLayout); // Añadir al contenedor
                                par=true;
                            }

                        } else {
                            for (int i = 0; i < ((cantidadCampos/2)+1); i++) {
                                //IDBoton=1;
                                LinearLayout horizontalLayout = createButtonLayout(); // Crear el layout
                                containerLayout.addView(horizontalLayout); // Añadir al contenedor
                                par=false;
                            }


                        }

                        for (int i = 0; i < cantidadCampos; i++) {
                            imagen( imageButtonList.get(i),elementList.get(i).toString());
                        }
                        if (par== false){
                            imageButtonList.get(imageButtonList.size()-1).setVisibility(View.INVISIBLE);
                        }
                        //imagen( imageButtonList.get(1),storageReference1);
                        // la altura de imageButton1
                    }


                    // Ahora puedes usar el dato "categoria"
                    System.out.println("Categoría obtenida: " + categoria);

                    // O llamar a una función que maneje los datos
                    // ...


                } else {
                    // El documento no existe
                    System.out.println("No se encontró el documento");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Manejar el error si ocurre un fallo en la lectura
                System.out.println("Error al obtener documento: " + e.getMessage());
            }
        });

        //botones compartir maps, telefono y facebook
        // Botón de llamada
        Button btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:123456789")); // Cambia el número
            startActivity(callIntent);
        });

        // Botón de localización
        Button btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(v -> {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            Uri gmmIntentUri = Uri.parse("geo:20.573714,-100.404709?q=RESET+MEXICO");
            mapIntent.setData(gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        // Botón de Facebook
        Button btnFacebook = findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(v -> {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
            startActivity(facebookIntent);
        });

        // Botón de compartir
        Button btnShare = findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "¡Descarga nuestra app en Google Play! https://play.google.com/store/apps/details?id=com.tuapp.ejemplo");
            startActivity(Intent.createChooser(shareIntent, "Compartir vía"));
        });
        // Botón de paginaweb
        Button btnpagweb = findViewById(R.id.btn_paginareset);
        btnpagweb.setOnClickListener(v -> {
            Intent pagwebintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.resetmex.com/"));
            startActivity(pagwebintent);
        });



    }

    // Método para crear un LinearLayout con dos ImageButtons
    private LinearLayout createButtonLayout() {
        // Crear un nuevo LinearLayout programáticamente
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Parámetros de diseño comunes para los ImageButton
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, dpToPx(180));
        buttonParams.weight = 1;
        buttonParams.setMargins(20, 20, 20, 20); // Margen de 20dp alrededor

        // Guardar la posición de IDBoton para los ImageButtons de este layout
        final int currentIDBoton = IDBoton; // Almacenar el valor actual de IDBoton
        IDBoton += 2; // Incrementar el IDBoton para el próximo set de botones

        // Crear el primer ImageButton
        ImageButton imageButton1 = new ImageButton(this);
        imageButton1.setLayoutParams(buttonParams);
        imageButton1.setBackgroundResource(R.drawable.boton); // Establecer el fondo
        imageButton1.setImageResource(R.drawable.ic_launcher_foreground); // Imagen del botón
        imageButton1.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); // Escalado de la imagen

        // Generar ID único para el botón
        imageButton1.setId(View.generateViewId());

        // Añadir el botón a la lista
        imageButtonList.add(imageButton1);

        // Listener para el primer botón
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Usar currentIDBoton en lugar de IDBoton
                //Toast.makeText(MainActivity.this, "ID " + currentIDBoton + ": " + elementList.get(currentIDBoton).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Productos.class);

                // Pasar datos utilizando putExtra
                intent.putExtra("CATEGORIA", elementList.get(currentIDBoton).toString());

                DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/"+elementList.get(currentIDBoton).toString());
                //Toast.makeText(MainActivity.this, "Entra"+String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();


                mDocRefUpdate.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // Obtener los datos del documento como un mapa
                                    Map<String, Object> datos = documentSnapshot.getData();

                                    if (datos != null) {
                                        // Contar la cantidad de elementos (campos) en el documento
                                        cantidadCamposCat = datos.size();
                                        Log.d("Firestore", "Cantidad de campos: " + cantidadCampos);
                                        //Toast.makeText(MainActivity.this,"Main"+ String.valueOf(cantidadCamposCat), Toast.LENGTH_SHORT).show();
                                        intent.putExtra("CANTIDAD", cantidadCamposCat);
                                        startActivity(intent);
                                        if (cantidadCampos > 0) {
                                            //Toast.makeText(MainActivity.this, "Funciona correctamente", Toast.LENGTH_SHORT).show();
                                        }

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


                intent.putExtra("CANTIDAD", cantidadCamposCat);
                // Iniciar la segunda actividad

            }
        });


        // Crear el segundo ImageButton
        ImageButton imageButton2 = new ImageButton(this);
        imageButton2.setLayoutParams(buttonParams);
        imageButton2.setBackgroundResource(R.drawable.boton); // Establecer el fondo
        imageButton2.setImageResource(R.drawable.ic_launcher_foreground); // Imagen del botón
        imageButton2.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); // Escalado de la imagen

        // Generar ID único para el segundo botón
        imageButton2.setId(View.generateViewId());

        // Añadir el segundo botón a la lista
        imageButtonList.add(imageButton2);

        // Listener para el segundo botón
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usar currentIDBoton + 1 para el segundo botón
                //Toast.makeText(MainActivity.this, "ID " + (currentIDBoton + 1) + ": " + elementList.get(currentIDBoton + 1).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Productos.class);

                // Pasar datos utilizando putExtra
                intent.putExtra("CATEGORIA", elementList.get(currentIDBoton + 1).toString());

                DocumentReference mDocRefUpdate2 = FirebaseFirestore.getInstance().document("RESET/"+elementList.get(currentIDBoton+1).toString());
                //Toast.makeText(MainActivity.this, "Entra"+String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();


                mDocRefUpdate2.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // Obtener los datos del documento como un mapa
                                    Map<String, Object> datos = documentSnapshot.getData();

                                    if (datos != null) {
                                        // Contar la cantidad de elementos (campos) en el documento
                                        cantidadCamposCat = datos.size();
                                        Log.d("Firestore", "Cantidad de campos: " + cantidadCampos);
                                        Toast.makeText(MainActivity.this, String.valueOf(cantidadCamposCat), Toast.LENGTH_SHORT).show();
                                        intent.putExtra("CANTIDAD", cantidadCamposCat);
                                        startActivity(intent);
                                        if (cantidadCampos > 0) {
                                            //Toast.makeText(MainActivity.this, "Funciona correctamente", Toast.LENGTH_SHORT).show();
                                        }

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





        };



        });

        // Añadir los ImageButton al LinearLayout horizontal
        horizontalLayout.addView(imageButton1);
        horizontalLayout.addView(imageButton2);




        return horizontalLayout; // Devolver el layout creado
    }





    // Método para convertir dp a píxeles, para el tamaño de los image boton
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
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


    //agregar imagenes desde firebase, deben ser .png
    public void imagen (ImageView img, String url){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference st = storage.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/CATEGORIAS").child(url+".png");

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
    //Guardar todos los elementos en una lista tipo objeto llamada elementlist
    public void FetchData(){
        // Obtener el intent que inició esta actividad
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(Productos.this, "solo funciona boton", Toast.LENGTH_SHORT).show();
                elementList.clear();
                if(documentSnapshot.exists())
                {
                    // Obtener los datos del documento como un mapa
                    Map<String, Object> data = documentSnapshot.getData();

                    // Agregar los valores a la lista
                    if (data != null) {
                        for(int i = 1; i <= cantidadCampos; i++){
                            elementList.add(documentSnapshot.getString(String.valueOf(i)));
                        }

                    }
                }
                else
                {
                }
            }
        });
    }



    //Cuenta cantidad de elementos en un documento y los guarda en cantidadcampos
    public void CountData()
    {
        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        //Toast.makeText(MainActivity.this, "Entra"+String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();


        mDocRefUpdate.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Obtener los datos del documento como un mapa
                            Map<String, Object> datos = documentSnapshot.getData();

                            if (datos != null) {
                                // Contar la cantidad de elementos (campos) en el documento
                                cantidadCampos = datos.size();
                                Log.d("Firestore", "Cantidad de campos: " + cantidadCampos);
                                //Toast.makeText(MainActivity.this, String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
                                if (cantidadCampos > 0) {
                                    //Toast.makeText(MainActivity.this, "Funciona correctamente", Toast.LENGTH_SHORT).show();
                                }

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

    }