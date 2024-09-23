package com.example.reset;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Productos extends AppCompatActivity {
    String CATEGORIA = "";
    int cantidadCampos;
    ArrayList<Object> elementList = new ArrayList<>();
    private ArrayList<TextView> textViewList = new ArrayList<>();

    String menu,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30;
    ImageButton ib,ib2,ib3,ib4,ib5,ib6,ib7,ib8,ib9,ib10,ib11,ib12,ib13,ib14,ib15,ib16,ib17,ib18,ib19,ib20,ib21,ib22,ib23,ib24,ib25,ib26,ib27,ib28,ib29,ib30;

    TextView tvmenu;
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
    TextView t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30;
    String Numero, mensaje;


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
        tvmenu = findViewById(R.id.Menu);
        t1 = findViewById(R.id.tv1);
        t2 = findViewById(R.id.tv2);
        t3 = findViewById(R.id.tv3);
        t4 = findViewById(R.id.tv4);
        t5 = findViewById(R.id.tv5);
        t6 = findViewById(R.id.tv6);
        t7 = findViewById(R.id.tv7);
        t8 = findViewById(R.id.tv8);
        t9 = findViewById(R.id.tv9);
        t10 = findViewById(R.id.tv10);
        t11 = findViewById(R.id.tv11);
        t12 = findViewById(R.id.tv12);
        t13 = findViewById(R.id.tv13);
        t14 = findViewById(R.id.tv14);
        t15 = findViewById(R.id.tv15);
        t16 = findViewById(R.id.tv16);
        t17 = findViewById(R.id.tv17);
        t18 = findViewById(R.id.tv18);
        t19 = findViewById(R.id.tv19);
        t20 = findViewById(R.id.tv20);
        t21 = findViewById(R.id.tv21);
        t22 = findViewById(R.id.tv22);
        t23 = findViewById(R.id.tv23);
        t24 = findViewById(R.id.tv24);
        t25 = findViewById(R.id.tv25);
        t26 = findViewById(R.id.tv26);
        t27 = findViewById(R.id.tv27);
        t28 = findViewById(R.id.tv28);
        t29 = findViewById(R.id.tv29);
        t30 = findViewById(R.id.tv30);

        ImageButton ib1 = findViewById(R.id.ibtn1);
        ImageButton ib2 = findViewById(R.id.ibtn2);
        ImageButton ib3 = findViewById(R.id.ibtn3);
        ImageButton ib4 = findViewById(R.id.ibtn4);
        ImageButton ib5 = findViewById(R.id.ibtn5);
        ImageButton ib6 = findViewById(R.id.ibtn6);
        ImageButton ib7 = findViewById(R.id.ibtn7);
        ImageButton ib8 = findViewById(R.id.ibtn8);
        ImageButton ib9 = findViewById(R.id.ibtn9);
        ImageButton ib10 = findViewById(R.id.itbn10);
        ImageButton ib11 = findViewById(R.id.ibtn11);
        ImageButton ib12 = findViewById(R.id.ibtn12);
        ImageButton ib13 = findViewById(R.id.ibtn13);
        ImageButton ib14 = findViewById(R.id.ibtn14);
        ImageButton ib15 = findViewById(R.id.ibtn15);
        ImageButton ib16 = findViewById(R.id.ibtn16);
        ImageButton ib17 = findViewById(R.id.ibtn17);
        ImageButton ib18 = findViewById(R.id.ibtn18);
        ImageButton ib19 = findViewById(R.id.ibtn19);
        ImageButton ib20 = findViewById(R.id.itbn20);
        ImageButton ib21 = findViewById(R.id.ibtn21);
        ImageButton ib22 = findViewById(R.id.ibtn22);
        ImageButton ib23 = findViewById(R.id.ibtn23);
        ImageButton ib24 = findViewById(R.id.ibtn24);
        ImageButton ib25 = findViewById(R.id.ibtn25);
        ImageButton ib26 = findViewById(R.id.ibtn26);
        ImageButton ib27 = findViewById(R.id.ibtn27);
        ImageButton ib28 = findViewById(R.id.ibtn28);
        ImageButton ib29 = findViewById(R.id.ibtn29);
        ImageButton ib30 = findViewById(R.id.itbn30);





        FetchData();
        CountData();

        //AgregarBotones();
        LinearLayout linearLayoutParent = findViewById(R.id.parentLinearLayout);
        //



        //



        // Crear 10 de estas estructuras
        /*for (int i = 1; i <= 4; i++) {
            createComplexLayout(linearLayoutParent, i);
        }
        if (!textViewList.isEmpty()) {
            // Cambiar el texto del primer TextView de la lista
            textViewList.get(0).setText("Texto");
        }*/




        //NoMostrar();
    }
    public void NoMostrar(){
        Intent intent = getIntent();
        CATEGORIA = intent.getStringExtra("CATEGORIA");

        if (true){
            Toast.makeText(Productos.this, "no funciona boton"+CATEGORIA, Toast.LENGTH_SHORT).show();
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            t9.setVisibility(View.GONE);
            t10.setVisibility(View.GONE);
            t11.setVisibility(View.GONE);
            t12.setVisibility(View.GONE);
            t13.setVisibility(View.GONE);
            t14.setVisibility(View.GONE);
            t15.setVisibility(View.GONE);
            t16.setVisibility(View.GONE);
            t17.setVisibility(View.GONE);
            t18.setVisibility(View.GONE);
            t19.setVisibility(View.GONE);
            t20.setVisibility(View.GONE);
            t21.setVisibility(View.GONE);
            t22.setVisibility(View.GONE);
            t23.setVisibility(View.GONE);
            t24.setVisibility(View.GONE);
            t25.setVisibility(View.GONE);
            t26.setVisibility(View.GONE);
            t27.setVisibility(View.GONE);
            t28.setVisibility(View.GONE);
            t29.setVisibility(View.GONE);
            t30.setVisibility(View.GONE);

            ib7.setVisibility(View.GONE);
            ib8.setVisibility(View.GONE);
            ib9.setVisibility(View.GONE);
            ib10.setVisibility(View.GONE);
            ib11.setVisibility(View.GONE);
            ib12.setVisibility(View.GONE);
            ib13.setVisibility(View.GONE);
            ib14.setVisibility(View.GONE);
            ib15.setVisibility(View.GONE);
            ib16.setVisibility(View.GONE);
            ib17.setVisibility(View.GONE);
            ib18.setVisibility(View.GONE);
            ib19.setVisibility(View.GONE);
            ib20.setVisibility(View.GONE);
            ib21.setVisibility(View.GONE);
            ib22.setVisibility(View.GONE);
            ib23.setVisibility(View.GONE);
            ib24.setVisibility(View.GONE);
            ib25.setVisibility(View.GONE);
            ib26.setVisibility(View.GONE);
            ib27.setVisibility(View.GONE);
            ib28.setVisibility(View.GONE);
            ib29.setVisibility(View.GONE);
            ib30.setVisibility(View.GONE);


        } else if (cantidadCampos == 11) {
            t12.setVisibility(View.GONE);
            t13.setVisibility(View.GONE);
            t14.setVisibility(View.GONE);
            t15.setVisibility(View.GONE);
            t16.setVisibility(View.GONE);
            t17.setVisibility(View.GONE);
            t18.setVisibility(View.GONE);
            t19.setVisibility(View.GONE);
            t20.setVisibility(View.GONE);
            t21.setVisibility(View.GONE);
            t22.setVisibility(View.GONE);
            t23.setVisibility(View.GONE);
            t24.setVisibility(View.GONE);
            t25.setVisibility(View.GONE);
            t26.setVisibility(View.GONE);
            t27.setVisibility(View.GONE);
            t28.setVisibility(View.GONE);
            t29.setVisibility(View.GONE);
            t30.setVisibility(View.GONE);


            ib12.setVisibility(View.GONE);
            ib13.setVisibility(View.GONE);
            ib14.setVisibility(View.GONE);
            ib15.setVisibility(View.GONE);
            ib16.setVisibility(View.GONE);
            ib17.setVisibility(View.GONE);
            ib18.setVisibility(View.GONE);
            ib19.setVisibility(View.GONE);
            ib20.setVisibility(View.GONE);
            ib21.setVisibility(View.GONE);
            ib22.setVisibility(View.GONE);
            ib23.setVisibility(View.GONE);
            ib24.setVisibility(View.GONE);
            ib25.setVisibility(View.GONE);
            ib26.setVisibility(View.GONE);
            ib27.setVisibility(View.GONE);
            ib28.setVisibility(View.GONE);
            ib29.setVisibility(View.GONE);
            ib30.setVisibility(View.GONE);
        }
        else if (cantidadCampos == 24)  {
            t25.setVisibility(View.GONE);
            t26.setVisibility(View.GONE);
            t27.setVisibility(View.GONE);
            t28.setVisibility(View.GONE);
            t29.setVisibility(View.GONE);
            t30.setVisibility(View.GONE);


            ib25.setVisibility(View.GONE);
            ib26.setVisibility(View.GONE);
            ib27.setVisibility(View.GONE);
            ib28.setVisibility(View.GONE);
            ib29.setVisibility(View.GONE);
            ib30.setVisibility(View.GONE);
        }
        else if (cantidadCampos == 13) {

            ib14.setVisibility(View.GONE);
            ib15.setVisibility(View.GONE);
            ib16.setVisibility(View.GONE);
            ib17.setVisibility(View.GONE);
            ib18.setVisibility(View.GONE);
            ib19.setVisibility(View.GONE);
            ib20.setVisibility(View.GONE);
            ib21.setVisibility(View.GONE);
            ib22.setVisibility(View.GONE);
            ib23.setVisibility(View.GONE);
            ib24.setVisibility(View.GONE);
            ib25.setVisibility(View.GONE);
            ib26.setVisibility(View.GONE);
            ib27.setVisibility(View.GONE);
            ib28.setVisibility(View.GONE);
            ib29.setVisibility(View.GONE);
            ib30.setVisibility(View.GONE);

            t14.setVisibility(View.GONE);
            t15.setVisibility(View.GONE);
            t16.setVisibility(View.GONE);
            t17.setVisibility(View.GONE);
            t18.setVisibility(View.GONE);
            t19.setVisibility(View.GONE);
            t20.setVisibility(View.GONE);
            t21.setVisibility(View.GONE);
            t22.setVisibility(View.GONE);
            t23.setVisibility(View.GONE);
            t24.setVisibility(View.GONE);
            t25.setVisibility(View.GONE);
            t26.setVisibility(View.GONE);
            t27.setVisibility(View.GONE);
            t28.setVisibility(View.GONE);
            t29.setVisibility(View.GONE);
            t30.setVisibility(View.GONE);
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


    public void AgregarBotones () {
        LinearLayout parentLinearLayout = findViewById(R.id.parentLinearLayout);

        // Crear un nuevo LinearLayout horizontal
        LinearLayout newHorizontalLayout = new LinearLayout(this);
        newHorizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Definir los parámetros de layout para el nuevo LinearLayout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newHorizontalLayout.setLayoutParams(layoutParams);

        // Crear TextViews o cualquier otro elemento que quieras agregar dentro del LinearLayout horizontal
        TextView textView1 = new TextView(this);
        textView1.setText("Elemento 1");
        textView1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView textView2 = new TextView(this);
        textView2.setText("Elemento 2");
        textView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Agregar los elementos al LinearLayout horizontal
        newHorizontalLayout.addView(textView1);
        newHorizontalLayout.addView(textView2);

        // Finalmente, agregar el LinearLayout horizontal al final del LinearLayout principal
        parentLinearLayout.addView(newHorizontalLayout);
    }


    public void back (View view){
        Intent intent = new Intent(Productos.this, MainActivity.class);

        // Iniciar la segunda actividad
        startActivity(intent);
    }

    public void FetchData(){
        // Obtener el intent que inició esta actividad
        Intent intent = getIntent();
        CATEGORIA = intent.getStringExtra("CATEGORIA");
        //Toast.makeText(Productos.this, CATEGORIA, Toast.LENGTH_LONG).show();
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);

        //Toast.makeText(Productos.this, "no funciona boton", Toast.LENGTH_SHORT).show();
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(Productos.this, "solo funciona boton", Toast.LENGTH_SHORT).show();

                if(documentSnapshot.exists())
                {
                    /*for (int i = 1; i <= 10; i++) {
                        String s = documentSnapshot.getString(String.valueOf(String.valueOf(i)));
                        "t" + String.valueOf(i) = setText(s);
                    }*/
                    //InspiringQuote myQuote = documentSnapshot.toObject(InspiringQuote.class);
                    t1.setText(documentSnapshot.getString("1"));
                    t2.setText(documentSnapshot.getString("2"));
                    t3.setText(documentSnapshot.getString("3"));
                    t4.setText(documentSnapshot.getString("4"));
                    t5.setText(documentSnapshot.getString("5"));
                    t6.setText(documentSnapshot.getString("6"));
                    t7.setText(documentSnapshot.getString("7"));
                    t8.setText(documentSnapshot.getString("8"));
                    t9.setText(documentSnapshot.getString("9"));
                    t10.setText(documentSnapshot.getString("10"));
                    t11.setText(documentSnapshot.getString("11"));
                    t12.setText(documentSnapshot.getString("12"));
                    t13.setText(documentSnapshot.getString("13"));
                    t14.setText(documentSnapshot.getString("14"));
                    t15.setText(documentSnapshot.getString("15"));
                    t16.setText(documentSnapshot.getString("16"));
                    t17.setText(documentSnapshot.getString("17"));
                    t18.setText(documentSnapshot.getString("18"));
                    t19.setText(documentSnapshot.getString("19"));
                    t20.setText(documentSnapshot.getString("20"));
                    t21.setText(documentSnapshot.getString("21"));
                    t22.setText(documentSnapshot.getString("22"));
                    t23.setText(documentSnapshot.getString("23"));
                    t24.setText(documentSnapshot.getString("24"));
                    t25.setText(documentSnapshot.getString("25"));
                    t26.setText(documentSnapshot.getString("26"));
                    t27.setText(documentSnapshot.getString("27"));
                    t28.setText(documentSnapshot.getString("28"));
                    t29.setText(documentSnapshot.getString("29"));
                    t30.setText(documentSnapshot.getString("30"));


                    s1 = documentSnapshot.getString("1");
                    s2 = documentSnapshot.getString("2");
                    s3 = documentSnapshot.getString("3");
                    s4 = documentSnapshot.getString("4");
                    s5 = documentSnapshot.getString("5");
                    s6 = documentSnapshot.getString("6");
                    s7 = documentSnapshot.getString("7");
                    s8 = documentSnapshot.getString("8");
                    s9 = documentSnapshot.getString("9");
                    s10 = documentSnapshot.getString("10");
                    s11 = documentSnapshot.getString("11");
                    s12 = documentSnapshot.getString("12");
                    s13 = documentSnapshot.getString("13");
                    s14 = documentSnapshot.getString("14");
                    s15 = documentSnapshot.getString("15");
                    s16 = documentSnapshot.getString("16");
                    s17 = documentSnapshot.getString("17");
                    s18 = documentSnapshot.getString("18");
                    s19 = documentSnapshot.getString("19");
                    s20 = documentSnapshot.getString("20");
                    s21 = documentSnapshot.getString("21");
                    s22 = documentSnapshot.getString("22");
                    s23 = documentSnapshot.getString("23");
                    s24 = documentSnapshot.getString("24");
                    s25 = documentSnapshot.getString("25");
                    s26 = documentSnapshot.getString("26");
                    s27 = documentSnapshot.getString("27");
                    s28 = documentSnapshot.getString("28");
                    s29 = documentSnapshot.getString("29");
                    s30 = documentSnapshot.getString("30");




                    // Obtener los datos del documento como un mapa
                    Map<String, Object> data = documentSnapshot.getData();

                    // Agregar los valores a la lista
                    if (data != null) {
                        elementList.addAll(data.values());
                        //Toast.makeText(Productos.this, "lista correcta"+elementList.toString(), Toast.LENGTH_LONG).show();
                    }

                    tvmenu.setText(CATEGORIA);






                    //mQuoteTextView.setText(s1 + " - "+ s2 + " - "+ s3 );
                    //Toast.makeText(Productos.this, "no hay texto", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //Toast.makeText(Productos.this, "Quote not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void b1 (View view){
        whatsapp(s1);
    }
    public void b2 (View view){
        whatsapp(s2);
    }
    public void b3 (View view){
        whatsapp(s3);
    }
    public void b4 (View view){
        whatsapp(s4);
    }
    public void b5 (View view){
        whatsapp(s5);
    }
    public void b6 (View view){
        whatsapp(s6);
    }
    public void b7 (View view){
        whatsapp(s7);
    }
    public void b8 (View view){
        whatsapp(s8);
    }
    public void b9 (View view){
        whatsapp(s9);
        }
    public void b10 (View view){
        whatsapp(s10);
    }
    public void b11 (View view){
        whatsapp(s11);
    }
    public void b12 (View view) {
        whatsapp(s12);
    }
    public void b13 (View view) {
        whatsapp(s13);
    }
    public void b14 (View view) {
        whatsapp(s14);
    }
    public void b15 (View view) {
        whatsapp(s15);}
    public void b16 (View view){
        whatsapp(s16);
    }
    public void b17 (View view){
        whatsapp(s17);
    }
    public void b18 (View view){
        whatsapp(s18);
    }
    public void b19 (View view){
        whatsapp(s19);
    }
    public void b20 (View view){
        whatsapp(s20);
    }
    public void b21 (View view){
        whatsapp(s21);
    }
    public void b22 (View view){
        whatsapp(s22);
    }
    public void b23 (View view){
        whatsapp(s23);
    }
    public void b24 (View view){
        whatsapp(s24);
        }
    public void b25 (View view){
        whatsapp(s25);
    }
    public void b26 (View view){
        whatsapp(s26);
    }
    public void b27 (View view){
        whatsapp(s27);
    }
    public void b28 (View view){
        whatsapp(s28);}
    public void b29 (View view){
        whatsapp(s29);
    }
    public void b30 (View view){
        whatsapp(s30);
    }


    public void whatsapp (String producto){
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/PRIVADO");


        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
                    //Toast.makeText(MainActivity.this, "Quote not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);

        String url ="whatsapp://send?phone="+Numero+"&text="+mensaje+producto;
        sendIntent.setData(android.net.Uri.parse(url));
        startActivity(sendIntent);

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