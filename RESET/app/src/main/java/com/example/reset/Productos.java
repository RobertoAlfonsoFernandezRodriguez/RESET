package com.example.reset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Productos extends AppCompatActivity {
    String CATEGORIA = "";

    String menu,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
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
        FetchData();





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

                    s1 = documentSnapshot.getString("1");
                    s2 = documentSnapshot.getString("2");
                    s3 = documentSnapshot.getString("3");
                    s4 = documentSnapshot.getString("4");
                    s5 = documentSnapshot.getString("5");
                    s6 = documentSnapshot.getString("6");
                    s7 = documentSnapshot.getString("7");

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

    public void whatsapp (String producto){


        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String url ="whatsapp://send?phone="+"524427186302"+"&text="+"Buenos dias, me interesa obtener un producto de "+producto;
        sendIntent.setData(android.net.Uri.parse(url));
        startActivity(sendIntent);

    }

}