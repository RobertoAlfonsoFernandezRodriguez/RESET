package com.example.reset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

import org.checkerframework.common.subtyping.qual.Bottom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.net.URL;

public class Productos extends AppCompatActivity {
    String CATEGORIA = "";
    int cantidadCampos;
    int cantidad=1;
    boolean par;
    ArrayList<Object> elementList = new ArrayList<>();
    ArrayList<ImageButton> imageButtonList = new ArrayList<>();
    int IDBoton=0;
    private ArrayList<TextView> textViewList = new ArrayList<>();


    TextView tvmenu;

    String Numero, mensaje;

    //vista individual
    ImageView imagenProducto;
    ImageButton  btnup, btndowm,butonwhatsapp;
    TextView preciototalproducto, cantidadproducto,nombreproducto,descripcionproducto;
    Boolean vistaindividual;
    LinearLayout llvistaindividual;
    //SCROLL
    ScrollView scroll;
    boolean whatsappenviado = false;
    DocumentReference mDocRefws = FirebaseFirestore.getInstance().document("RESET/PRIVADO");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //encontrar id vista indivual
        imagenProducto = findViewById(R.id.imagenproducto);
        nombreproducto = findViewById(R.id.nombreproducto);
        descripcionproducto = findViewById(R.id.descripcionproducto);
        preciototalproducto = findViewById(R.id.preciotototalproducto);
        cantidadproducto = findViewById(R.id.cantidadproducto);
        butonwhatsapp = findViewById(R.id.botonwhatsapp);
        btnup = findViewById(R.id.btnup);
        btndowm = findViewById(R.id.btndowm);
        llvistaindividual = findViewById(R.id.linearLayoutvistaindividual);
        llvistaindividual.setVisibility(View.GONE);

        scroll = findViewById(R.id.scroll);
        scroll.setVisibility(View.VISIBLE);

        // lo otro
        tvmenu = findViewById(R.id.Menu);
        Intent intent = getIntent();
        cantidadCampos = intent.getIntExtra("CANTIDAD",0); // El 0 es un valor por defecto en caso de que no se encuentre el extra
        CATEGORIA = intent.getStringExtra("CATEGORIA");
        imageButtonList = new ArrayList<>();
        tvmenu.setText(CATEGORIA);

        FetchData();

        // Obtener el contenedor (LinearLayout) dentro del ScrollView
        LinearLayout containerLayout = findViewById(R.id.containerLayout);

        if (cantidadCampos > 0){

            if (cantidadCampos % 2 == 0) {
                for (int i = 0; i < ((cantidadCampos/2)); i++) {

                    LinearLayout horizontalLayout = createButtonLayout(); // Crear el layout
                    containerLayout.addView(horizontalLayout); // Añadir al contenedor
                    par=true;
                }

            } else {
                for (int i = 0; i < ((cantidadCampos/2)+1); i++) {
                    LinearLayout horizontalLayout = createButtonLayout(); // Crear el layout
                    containerLayout.addView(horizontalLayout); // Añadir al contenedor
                    par=false;
                }


            }

            if (par== false){
                imageButtonList.get(imageButtonList.size()-1).setVisibility(View.INVISIBLE);
            }

        }
        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);

        // Intentas obtener los datos de Firestore de manera asíncrona
        mDocRefUpdate.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // Aquí ya tienes el documento obtenido correctamente
                if (documentSnapshot.exists()) {
                    // Obtén los datos del documento, por ejemplo:
                    String categoria = documentSnapshot.getString("1");
                    //Toast.makeText(MainActivity.this, "El valor de countdata es"+categoria, Toast.LENGTH_SHORT).show();
                    //CountData();
                    //Toast.makeText(MainActivity.this, "El valor de countdata es" + String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
                    if (cantidadCampos > 0){
                        FetchData();
                        //Toast.makeText(Productos.this, "lista correcta2"+elementList.toString(), Toast.LENGTH_LONG).show();
                        //imagen( imageButtonList.get(1),"EstudioMercado");

                        for (int i = 0; i < cantidadCampos; i++) {
                            imagen( imageButtonList.get(i),elementList.get(i).toString());
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





    }

    private LinearLayout createButtonLayoutno() {
        // Crear un nuevo LinearLayout programáticamente
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

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
                //Toast.makeText(Productos.this, "ID " + currentIDBoton + ": " + elementList.get(currentIDBoton).toString(), Toast.LENGTH_SHORT).show();

                //whatsapp(elementList.get(currentIDBoton).toString());
                vistaindividual=true;
                vistaindividual(elementList.get(currentIDBoton).toString());
                imagen(imageButtonList.get(currentIDBoton),elementList.get(currentIDBoton).toString());


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
                //Toast.makeText(Productos.this, "ID " + (currentIDBoton + 1) + ": " + elementList.get(currentIDBoton + 1).toString(), Toast.LENGTH_SHORT).show();

                //whatsapp(elementList.get(currentIDBoton+1 ).toString());
                vistaindividual=true;
                vistaindividual(elementList.get(currentIDBoton).toString());
                imagen(imageButtonList.get(currentIDBoton),elementList.get(currentIDBoton).toString());


            }
        });

        // Añadir los ImageButton al LinearLayout horizontal
        horizontalLayout.addView(imageButton1);
        horizontalLayout.addView(imageButton2);




        return horizontalLayout; // Devolver el layout creado
    }
    private LinearLayout createButtonLayout() {
        // Crear un nuevo LinearLayout horizontal programáticamente
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        // Parámetros de diseño comunes para los ImageButton y TextView
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, dpToPx(180));
        buttonParams.weight = 1;
        buttonParams.setMargins(20, 20, 20, 20); // Margen de 20dp alrededor

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(20, 0, 20, 20); // Margen para el TextView

        // Guardar la posición de IDBoton para los ImageButtons de este layout
        final int currentIDBoton = IDBoton; // Almacenar el valor actual de IDBoton
        IDBoton += 2; // Incrementar el IDBoton para el próximo set de botones

        // Crear el primer conjunto de ImageButton y TextView
        LinearLayout verticalLayout1 = new LinearLayout(this);
        verticalLayout1.setOrientation(LinearLayout.VERTICAL);
        verticalLayout1.setLayoutParams(buttonParams);

        // Crear el primer ImageButton
        ImageButton imageButton1 = new ImageButton(this);
        imageButton1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(180)));
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
                vistaindividual = true;
                vistaindividual(elementList.get(currentIDBoton).toString());
                imagen(imageButtonList.get(currentIDBoton), elementList.get(currentIDBoton).toString());
            }
        });

        // Crear el TextView para el primer ImageButton
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(textParams);
        textView1.setText("Texto 1"); // Establece el texto que desees

        // Añadir ImageButton y TextView al LinearLayout vertical
        verticalLayout1.addView(imageButton1);
        verticalLayout1.addView(textView1);

        // Crear el segundo conjunto de ImageButton y TextView
        LinearLayout verticalLayout2 = new LinearLayout(this);
        verticalLayout2.setOrientation(LinearLayout.VERTICAL);
        verticalLayout2.setLayoutParams(buttonParams);

        // Crear el segundo ImageButton
        ImageButton imageButton2 = new ImageButton(this);
        imageButton2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(180)));
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
                vistaindividual = true;
                vistaindividual(elementList.get(currentIDBoton + 1).toString());
                imagen(imageButtonList.get(currentIDBoton + 1), elementList.get(currentIDBoton + 1).toString());
            }
        });

        // Crear el TextView para el segundo ImageButton
        TextView textView2 = new TextView(this);
        textView2.setLayoutParams(textParams);
        textView2.setText("Texto 2"); // Establece el texto que desees

        // Añadir ImageButton y TextView al LinearLayout vertical
        verticalLayout2.addView(imageButton2);
        verticalLayout2.addView(textView2);

        // Añadir los LinearLayouts verticales al LinearLayout horizontal
        horizontalLayout.addView(verticalLayout1);
        horizontalLayout.addView(verticalLayout2);

        return horizontalLayout; // Devolver el layout creado
    }


    public void imagen (ImageView img, String url){
        //funciono imagen

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference st = storage.getReferenceFromUrl("gs://reset-f2675.appspot.com/RESET/"+CATEGORIA).child(url+".png");

        try{
            final File file=File.createTempFile("image","png");
            st.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                    img.setImageBitmap(scaledBitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(Productos.this, "no funciona imagen", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Método para convertir dp a píxeles
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    // Método para crear el LinearLayout complejo con FrameLayout, ImageButton y TextView
    // Método para crear el LinearLayout complejo con FrameLayout, ImageButton y TextView
    private void createComplexLayout(LinearLayout parentLayout, int index) {
        // Crear LinearLayout horizontal
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Crear los dos FrameLayouts dentro del LinearLayout
        for (int i = 0; i < 2; i++) {
            // Crear FrameLayout
            FrameLayout frameLayout = new FrameLayout(this);
            LinearLayout.LayoutParams frameLayoutParams = new LinearLayout.LayoutParams(
                    0, // Ancho a 0 para utilizar weight
                    LinearLayout.LayoutParams.MATCH_PARENT, // Altura que ocupa todo el espacio
                    1f // Distribuir espacio equitativamente
            );
            frameLayoutParams.setMargins(10, 10, 10, 10);
            frameLayout.setLayoutParams(frameLayoutParams);

            // Crear ImageButton
            ImageButton imageButton = new ImageButton(this);
            FrameLayout.LayoutParams imageButtonParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    dpToPx(180) // Altura fija de 180dp
            );
            imageButtonParams.setMargins(10, 10, 10, 10);
            imageButton.setLayoutParams(imageButtonParams);
            imageButton.setBackground(ContextCompat.getDrawable(this, R.drawable.boton));
            imageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground));
            imageButton.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); // Ajuste de escala

            // Crear TextView
            TextView textView = new TextView(this);
            FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            textView.setLayoutParams(textViewParams);
            textView.setText("Texto " + index);
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            textView.setTextSize(14);
            textView.setTypeface(null, android.graphics.Typeface.BOLD);
            //textView.setMargins(0, dpToPx(60), 0, 0); // Margen superior de 60dp

            // Añadir el TextView a la lista para modificarlo después
            textViewList.add(textView);

            // Añadir ImageButton y TextView al FrameLayout
            frameLayout.addView(imageButton);
            frameLayout.addView(textView);

            // Añadir el FrameLayout al LinearLayout horizontal
            horizontalLayout.addView(frameLayout);
        }

        // Añadir el LinearLayout horizontal al layout padre
        parentLayout.addView(horizontalLayout);
    }





    public void back (View view){
        if(vistaindividual==true){
            recreate();
        }
        else{
            Intent intent = new Intent(Productos.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            // Iniciar la segunda actividad
            startActivity(intent);
        }
    }
    public void FetchData(){
        // Obtener el intent que inició esta actividad
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
        //Toast.makeText(Productos.this, "no funciona boton", Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(Productos.this, "lista correcta"+elementList.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                }
            }
        });
    }





    public void whatsappno (String producto){

        //DocumentReference mDocRefws = FirebaseFirestore.getInstance().document("RESET/PRIVADO");
        mDocRefws.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(MainActivity.this, "solo funciona boton", Toast.LENGTH_SHORT).show();

                if(documentSnapshot.exists())
                {
                    Numero = documentSnapshot.getString("1");
                    mensaje = documentSnapshot.getString("2");

                }
                else
                {

                }
            }
        });
        Toast.makeText(Productos.this, "funciona whatsapp", Toast.LENGTH_SHORT).show();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        if (Numero==null){
            //Thread.sleep(100);
            //whatsapp(producto);
            return;
        }

        //codigo para whatsapp
        whatsappenviado = true;
        String url  ="whatsapp://send?phone="+Numero+"&text="+mensaje+producto+" de la categoria "+CATEGORIA;
        sendIntent.setData(android.net.Uri.parse(url));
        startActivity(sendIntent);
        vistaindividual=true;
        vistaindividual(producto);

    }
    public void whatsapp(String producto) {

        mDocRefws.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Numero = documentSnapshot.getString("1");
                    mensaje = documentSnapshot.getString("2");

                    // Asegúrate de que Numero no sea null antes de continuar
                    if (Numero != null) {
                        Toast.makeText(Productos.this, "funciona whatsapp", Toast.LENGTH_SHORT).show();

                        // Código para enviar mensaje por WhatsApp
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_VIEW);
                        String url = "whatsapp://send?phone=" + Numero + "&text=" + mensaje + producto + " de la categoria " + CATEGORIA;
                        sendIntent.setData(android.net.Uri.parse(url));
                        startActivity(sendIntent);

                        whatsappenviado = true;
                        vistaindividual = true;
                        vistaindividual(producto);  // Aquí llamas a tu función personalizada
                    } else {
                        // Si Numero es null, puedes manejarlo aquí
                        Toast.makeText(Productos.this, "No se pudo obtener el número", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Si el documento no existe, maneja el caso aquí
                    Toast.makeText(Productos.this, "Documento no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void vistaindividual(String producto){
        Toast.makeText(Productos.this, "funciona  vistaindividual", Toast.LENGTH_SHORT).show();
        if(vistaindividual==true){
            llvistaindividual.setVisibility(View.VISIBLE);
            scroll.setVisibility(View.GONE);
            Toast.makeText(Productos.this, "funciona  vistaindividual = true", Toast.LENGTH_SHORT).show();
            nombreproducto.setText(producto);
            imagen(imagenProducto,producto);
            cantidadproducto.setText(String.valueOf(1));
            preciototalproducto.setText("$"+String.valueOf(100));


            btnup.setOnClickListener(v -> {
                cantidad++;
                cantidadproducto.setText(String.valueOf(cantidad));
                preciototalproducto.setText("$"+String.valueOf(cantidad*100));

            });
            btndowm.setOnClickListener(v -> {
                if(cantidad==0){
                    Toast.makeText(Productos.this, "Valor invalido", Toast.LENGTH_SHORT).show();
                    return;
                }
                cantidad--;
                cantidadproducto.setText(String.valueOf(cantidad));
                preciototalproducto.setText("$"+String.valueOf(cantidad*100));
            });
            butonwhatsapp.setOnClickListener(v -> {

                whatsapp(producto);

            });


        }
        else{
            llvistaindividual.setVisibility(View.GONE);
            scroll.setVisibility(View.VISIBLE);
        }



    }
    public void CountData()
    {
        DocumentReference mDocRefUpdate = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);

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
                                //Toast.makeText(Productos.this, String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
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