package com.example.reset;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CRUD extends AppCompatActivity {
    int i;
    int opcion;
    int indicepos;
    Button WhatsApp;
    Button Admin;
    Button Datos;
    TextView tvnumero;
    TextView tvmensaje;
    TextView tvcontraseña;
    EditText etnumero;
    EditText etmensaje;
    EditText etcontraseña;
    ToggleButton tbnumero;
    ToggleButton tbmensaje;
    ToggleButton tbcontraseña;

    int cantidadCampos;
    DocumentReference mDocRef;
    List<String> spinnerOptions = new ArrayList<>();
    List<String> spinnerOptions2 = new ArrayList<>();
    String CATEGORIA= "vacio";
    String ELEMENTO= "vacio";

    Button siguiente;
    Spinner spinner;
    Spinner spinner2;
    Button guardar;
    Button editar;
    Button eliminar;
    Button agregar;
    EditText nuevovalor;
    Button btregresar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        siguiente = findViewById(R.id.buscarcategoria);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        guardar = findViewById(R.id.btguardar);


        WhatsApp = findViewById(R.id.btwhatsapp);
        Admin = findViewById(R.id.btadmin);
        Datos = findViewById(R.id.btdatos);
        tvnumero = findViewById(R.id.tvnumero);
        tvmensaje = findViewById(R.id.tvmensaje);
        tvcontraseña = findViewById(R.id.tvcontraseña);
        etnumero = findViewById(R.id.etnumero);
        etmensaje = findViewById(R.id.etmensaje);
        etcontraseña = findViewById(R.id.etcontraseña);
        tbnumero = findViewById(R.id.toggleButton);
        tbmensaje = findViewById(R.id.toggleButton2);
        tbcontraseña = findViewById(R.id.toggleButton3);
        editar = findViewById(R.id.btneditar);
        eliminar = findViewById(R.id.btneliminar);
        agregar = findViewById(R.id.btnAgregar);
        nuevovalor = findViewById(R.id.nuevovalor);
        btregresar = findViewById(R.id.btregresar);


        tvnumero.setVisibility(View.GONE);
        tvmensaje.setVisibility(View.GONE);
        tvcontraseña.setVisibility(View.GONE);
        etnumero.setVisibility(View.GONE);
        etmensaje.setVisibility(View.GONE);
        etcontraseña.setVisibility(View.GONE);
        tbnumero.setVisibility(View.GONE);
        tbmensaje.setVisibility(View.GONE);
        tbcontraseña.setVisibility(View.GONE);
        siguiente.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        spinner2.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);
        editar.setVisibility(View.GONE);
        eliminar.setVisibility(View.GONE);
        agregar.setVisibility(View.GONE);
        nuevovalor.setVisibility(View.GONE);
        btregresar.setVisibility(View.GONE);


        Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();


    }
    public void add(View view){
        opcion=4;
        nuevovalor.setVisibility(View.VISIBLE);
    }
    public void edit(View view){
        opcion=5;
        nuevovalor.setVisibility(View.VISIBLE);
    }
    public void delete(View view) {
        opcion=6;
    }

    public void regresar(View view){
        tvnumero.setVisibility(View.GONE);
        tvmensaje.setVisibility(View.GONE);
        tvcontraseña.setVisibility(View.GONE);
        etnumero.setVisibility(View.GONE);
        etmensaje.setVisibility(View.GONE);
        etcontraseña.setVisibility(View.GONE);
        tbnumero.setVisibility(View.GONE);
        tbmensaje.setVisibility(View.GONE);
        tbcontraseña.setVisibility(View.GONE);
        siguiente.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        spinner2.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);
        editar.setVisibility(View.GONE);
        eliminar.setVisibility(View.GONE);
        agregar.setVisibility(View.GONE);
        nuevovalor.setVisibility(View.GONE);
        btregresar.setVisibility(View.GONE);
        spinnerOptions.clear();
        spinnerOptions2.clear();
        opcion=0;
    }
    public void guardar (View view){
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/PRIVADO");
        if(opcion==1){

            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put("1",etnumero.getText().toString());
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Numero: "+etnumero.getText().toString()+ " guardado", Toast.LENGTH_SHORT).show();
        //etnumero.getText().toString()
        }
        else if(opcion==2){

            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put("2",etmensaje.getText().toString());
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Mensaje: "+etmensaje.getText().toString()+ " guardado", Toast.LENGTH_SHORT).show();
        }
        else if(opcion==3){
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put("3",etcontraseña.getText().toString());
            mDocRef.update(datatoUpdate);

            Toast.makeText(CRUD.this,"Mensaje: "+etcontraseña.getText().toString()+ " guardado", Toast.LENGTH_SHORT).show();
        }
        else if(opcion==4) {
            //agregar
            //if en el documento hay eliminado  ponerlo ahi, si no ponerlo al final
            /*indicepos = spinnerOptions2.indexOf("ELIMINADO");


            if (indicepos != -1) {
                mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
                Map<String, Object> datatoUpdate = new HashMap<String,Object>();
                datatoUpdate.put(String.valueOf(indicepos),nuevovalor.getText().toString());
                mDocRef.update(datatoUpdate);
            } else {
                mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
                Map<String, Object> datatoUpdate = new HashMap<String,Object>();
                datatoUpdate.put(String.valueOf(cantidadCampos+1),nuevovalor.getText().toString());
                mDocRef.update(datatoUpdate);
            }
            Toast.makeText(CRUD.this,"Elemento: "+nuevovalor.getText().toString()+ " Agregado", Toast.LENGTH_SHORT).show();*/
            mDocRef = FirebaseFirestore.getInstance().document("RESET/" + CATEGORIA);


            // Leer el documento una vez
            mDocRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    for ( i = 1; i < cantidadCampos; i++) {
                        // Verificar si la clave "usuarios" está presente
                        if (documentSnapshot.contains(String.valueOf(i))) {

                            Toast.makeText(CRUD.this, String.valueOf(i), Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(CRUD.this, "Falta" + String.valueOf(i), Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }


                } else {
                    Toast.makeText(CRUD.this, "El documento no existe.", Toast.LENGTH_SHORT).show();
                }

            }).addOnFailureListener(e -> {
                // Manejo de errores
            });
            Map<String, Object> datatoUpdate = new HashMap<String, Object>();
            datatoUpdate.put(String.valueOf(i), nuevovalor.getText().toString());
            mDocRef.update(datatoUpdate);
        }
        else if(opcion==5){
            //editar
            mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),nuevovalor.getText().toString());
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Elemento: "+nuevovalor.getText().toString()+ " editado", Toast.LENGTH_SHORT).show();

        }
        else if(opcion==6){
            //eliminar
            /*mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),"ELIMINADO");
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Elemento: "+ELEMENTO+ " eliminado", Toast.LENGTH_SHORT).show();*/
            mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),FieldValue.delete());
            mDocRef.update(datatoUpdate);


        }

       regresar(view);



    }
    public void whatsapp(View view){
        btregresar.setVisibility(View.VISIBLE);
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/PRIVADO");

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists())
                {
                    tvnumero.setVisibility(View.VISIBLE);
                    tvnumero.setText("Numero Actual:"+documentSnapshot.getString("1"));
                    tvmensaje.setVisibility(View.VISIBLE);
                    tvmensaje.setText("Mensaje Actual"+documentSnapshot.getString("2"));

                }
                else
                {

                }
            }
        });


        tbnumero.setVisibility(View.VISIBLE);

        tbmensaje.setVisibility(View.VISIBLE);



        tbnumero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etnumero.setVisibility(View.VISIBLE);
                    guardar.setVisibility(View.VISIBLE);
                    opcion=1;
                } else {
                    etnumero.setVisibility(View.GONE);
                    opcion=0;

                }
            }
        });

        tbmensaje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etmensaje.setVisibility(View.VISIBLE);
                    guardar.setVisibility(View.VISIBLE);
                    opcion=2;
                } else {
                    etmensaje.setVisibility(View.GONE);
                    opcion=0;

                }
            }
        });



    }
    public void admin(View view){
        btregresar.setVisibility(View.VISIBLE);
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("RESET/PRIVADO");
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //Toast.makeText(MainActivity.this, "solo funciona boton", Toast.LENGTH_SHORT).show();

                if(documentSnapshot.exists())
                {
                    tvcontraseña.setVisibility(View.VISIBLE);
                    tvcontraseña.setText("Contraseña Actual:"+documentSnapshot.getString("3"));
                }
                else
                {

                }
            }
        });
        tbcontraseña.setVisibility(View.VISIBLE);

        tbcontraseña.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etcontraseña.setVisibility(View.VISIBLE);
                    guardar.setVisibility(View.VISIBLE);
                    opcion=3;
                } else {
                    etcontraseña.setVisibility(View.GONE);
                    opcion=0;

                }
            }
        });

    }
    public void datos(View view){
        siguiente.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);

        guardar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.VISIBLE);
        agregar.setVisibility(View.VISIBLE);
        btregresar.setVisibility(View.VISIBLE);

        mDocRef = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        CountData();
        FetchData(1);
        Spinner(1);


    }
    public void siguiente(View view){
        spinner2.setVisibility(View.VISIBLE);
        Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
        mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
        CountData();
        FetchData(2);
        Spinner(2);
    }
    public void Spinner(int n){
        Spinner mySpinner;findViewById(R.id.spinner);
        if(n==1){
            mySpinner = findViewById(R.id.spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOptions.add("Selecciona una categoria");
            mySpinner.setAdapter(adapter);

            // Configura un listener para manejar la selección de elementos
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Acción al seleccionar un elemento
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    CATEGORIA = selectedItem;
                    Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
                    Toast.makeText(CRUD.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Acción cuando no se selecciona nada
                    Toast.makeText(CRUD.this, "No seleccionaste nada", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            mySpinner = findViewById(R.id.spinner2);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions2);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOptions2.add("Selecciona una opción");
            mySpinner.setAdapter(adapter);

            // Configura un listener para manejar la selección de elementos
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Acción al seleccionar un elemento
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    ELEMENTO = selectedItem;
                    indicepos = spinnerOptions2.indexOf(ELEMENTO);
                    Toast.makeText(CRUD.this,ELEMENTO, Toast.LENGTH_SHORT).show();
                    Toast.makeText(CRUD.this, "elemento en posicion"+String.valueOf(indicepos), Toast.LENGTH_SHORT).show();
                    Toast.makeText(CRUD.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Acción cuando no se selecciona nada
                    Toast.makeText(CRUD.this, "No seleccionaste nada", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
    public void CountData()
    {


        mDocRef.get()
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
                                Toast.makeText(CRUD.this, String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
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
    public void FetchData(int n){

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists())
                {
                    for (int i = 1; i <= cantidadCampos; i++) {
                        if(n==1)
                            spinnerOptions.add(documentSnapshot.getString(String.valueOf(i)));
                        else{
                            spinnerOptions2.add(documentSnapshot.getString(String.valueOf(i)));
                        }

                    }

                }
                else
                {

                }
            }
        });
    }
}