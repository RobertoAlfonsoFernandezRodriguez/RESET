package com.example.reset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/PRIVADO");
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(MainActivity.this, "solo funciona boton", Toast.LENGTH_SHORT).show();

                if(documentSnapshot.exists())
                {



                    contraseña = documentSnapshot.getString("3");





                }
                else
                {

                }
            }
        });
        Button btn1 = findViewById(R.id.button);

        // Configura el evento onClick
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción a realizar cuando se hace clic en la imagen
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn2 = findViewById(R.id.button2);
        EditText editText = findViewById(R.id.editTextText);

        // Configura el evento onClick
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                String password = editText.getText().toString();
                if (password.equals(contraseña)) {
                    Intent intent = new Intent(Login.this, CRUD.class);
                    startActivity(intent);
                }

            }
        });
    }
}