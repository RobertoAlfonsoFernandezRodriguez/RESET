package com.example.reset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CRUD extends AppCompatActivity {
    //subir imagenes
    String nombreimagen;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference storageReference;
    private Button EditarImagen,EditarCategorias;
    private ImageView imageView;
    //categoria
    boolean boleanocategoria=false;
    //todo lo demas
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
    Button editarcategorias, editarsubcategorias;
    private DocumentReference mDatabase2;
    private FirebaseFirestore db;






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
        storageReference = FirebaseStorage.getInstance().getReference("RESET/REDES");
        EditarImagen = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView3);

        //displayImageFromFirebase();
        editarcategorias = findViewById(R.id.button4);
        editarsubcategorias = findViewById(R.id.button5);
        // Botón para seleccionar la imagen
        //findViewById(R.id.button4).setOnClickListener(view -> openFileChooser());

        // Botón para subir la imagen
        //findViewById(R.id.button3).setOnClickListener(view -> ordenar());

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
        editarsubcategorias.setVisibility(View.GONE);
        editarcategorias.setVisibility(View.GONE);
        EditarImagen.setVisibility(View.GONE);


        //Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
        //Map<String, Object> data = new HashMap<>();
        //createNewDocument("subcategorias", data);
        //agregardocument();
        //eliminardocumento();
        cambiarNombreDocumento("supernuevo","nuevo");


    }
    public void cambiarNombreDocumento(final String idOriginal, final String nuevoId) {
        // Inicializa la conexión a Firestore
        db = FirebaseFirestore.getInstance();

        // Referencia al documento original que deseas renombrar
        final DocumentReference docRefOriginal = db.collection("RESET").document(idOriginal);

        // Obtén los datos del documento original
        docRefOriginal.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Si el documento existe, obtén los datos
                    Map<String, Object> data = documentSnapshot.getData();

                    // Crea un nuevo documento con el nuevo ID
                    db.collection("RESET").document(nuevoId)
                            .set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Documento creado con el nuevo ID, muestra un Toast
                                    Toast.makeText(getApplicationContext(), "Documento renombrado exitosamente!", Toast.LENGTH_SHORT).show();

                                    // Elimina el documento original
                                    docRefOriginal.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Documento original eliminado, muestra un Toast
                                            Toast.makeText(getApplicationContext(), "Documento original eliminado exitosamente!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Error al eliminar el documento original, muestra un Toast
                                            Toast.makeText(getApplicationContext(), "Error al eliminar el documento original", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Error al crear el documento con el nuevo ID, muestra un Toast
                                    Toast.makeText(getApplicationContext(), "Error al crear el documento con el nuevo ID", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // El documento original no existe, muestra un Toast
                    Toast.makeText(getApplicationContext(), "El documento original no existe", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Error al obtener los datos del documento original, muestra un Toast
                Toast.makeText(getApplicationContext(), "Error al obtener los datos del documento original", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregardocument(){
        db = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        db.collection("RESET").document("nuevo").set(data);

    }
    public void eliminardocumento() {
        // Inicializa la conexión a Firestore
        db = FirebaseFirestore.getInstance();

        // Elimina el documento con el ID "nuevo" en la colección "RESET"
        db.collection("RESET").document("nuevo")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Documento eliminado exitosamente, muestra un Toast
                        Toast.makeText(getApplicationContext(), "Documento eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al eliminar el documento, muestra un Toast
                        Toast.makeText(getApplicationContext(), "Error al eliminar el documento", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Referencia al documento en la ruta "RESET/CATEGORIAS"
    private DocumentReference mDocRef2 = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");

    // Método para crear un nuevo documento con datos personalizados
    public void createNewDocument(String documentID, Map<String, Object> data) {
        // Usar la referencia para crear un documento con el ID especificado
        mDocRef2.collection(documentID)
                .document(documentID)
                .set(data) // Establecer los datos del nuevo documento
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Documento con ID '" + documentID + "' creado con éxito.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error al crear documento", e);
                    }
                });
    }
    private void createEmptyDocument() {
        // Crear un documento vacío (sin campos)
        db.collection("RESET")
                .document("NEGRO") // Usar el ID específico "usuario1"
                .set(new HashMap<String, Object>()) // Pasar un mapa vacío para crear un documento sin datos
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Documento vacío con ID 'usuario1' creado con éxito.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error al crear documento", e);
                    }
                });
    }
    public void metodocambiarimagen(View view){
        opcion = 7;
        storageReference = FirebaseStorage.getInstance().getReference("RESET/"+CATEGORIA);
        nuevovalor.setText(ELEMENTO);
        Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
        openFileChooser();
    }
    public void metodoeditarsubcategorias(View view){
        siguiente.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);


        btregresar.setVisibility(View.VISIBLE);

        mDocRef = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        CountData();
        FetchData(1);
        Spinner(1);
    }
    public void metodoeditarcategorias(View view){
    Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
        spinner2.setVisibility(View.VISIBLE);

        guardar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.VISIBLE);
        agregar.setVisibility(View.VISIBLE);
        btregresar.setVisibility(View.VISIBLE);

        mDocRef = FirebaseFirestore.getInstance().document("RESET/CATEGORIAS");
        CountData();
        FetchData(2);
        Spinner(2);
        boleanocategoria=true;
        Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
        Toast.makeText(CRUD.this,ELEMENTO, Toast.LENGTH_SHORT).show();
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            nombreimagen = nuevovalor.getText().toString();
            StorageReference fileReference = storageReference.child( nombreimagen+".png");
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        Toast.makeText(CRUD.this, "Subida Exitosamente", Toast.LENGTH_SHORT).show();
                        displayImageFromFirebase(); // Display the newly uploaded image
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(CRUD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }}
    private void displayImageFromFirebase() {
        StorageReference imageRef = storageReference.child( nombreimagen+".png");
        Toast.makeText(CRUD.this,ELEMENTO, Toast.LENGTH_SHORT).show();
        Toast.makeText(CRUD.this,nombreimagen, Toast.LENGTH_SHORT).show();


        final File localFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "downloaded_image.jpg");
        imageRef.getFile(localFile)
                .addOnSuccessListener(taskSnapshot -> {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CRUD.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }




    public void add(View view){
        opcion=4;
        nuevovalor.setVisibility(View.VISIBLE);
    }
    public void edit(View view){
        if(ELEMENTO=="+ NUEVO PRODUCTO"){
            Toast.makeText(CRUD.this,"opcion no valida", Toast.LENGTH_SHORT).show();
            return;
        }
        opcion=5;
        nuevovalor.setVisibility(View.VISIBLE);
        EditarImagen.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        nombreimagen=ELEMENTO;
        storageReference = FirebaseStorage.getInstance().getReference("RESET/"+CATEGORIA);
        displayImageFromFirebase();


    }
    public void delete(View view) {

        if(ELEMENTO=="+ NUEVO PRODUCTO"){
            Toast.makeText(CRUD.this,"opcion no valida", Toast.LENGTH_SHORT).show();
            return;
        }
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
        editarsubcategorias.setVisibility(View.GONE);
        editarcategorias.setVisibility(View.GONE);
        EditarImagen.setVisibility(View.GONE);
        spinnerOptions.clear();
        spinnerOptions2.clear();
        opcion=0;
        recreate();
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
            if(boleanocategoria == true){
                ELEMENTO=CATEGORIA;
                CATEGORIA="CATEGORIAS";
                //createEmptyDocument();
            }
            mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(cantidadCampos+1),nuevovalor.getText().toString());
            mDocRef.update(datatoUpdate);

            storageReference = FirebaseStorage.getInstance().getReference("RESET/"+CATEGORIA);
            openFileChooser();



        }
        else if(opcion==5){
            //editar
            if(boleanocategoria == true){
                CATEGORIA="CATEGORIAS";
            }
            mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),nuevovalor.getText().toString());
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Elemento: "+nuevovalor.getText().toString()+ " editado", Toast.LENGTH_SHORT).show();
            nombreimagen=nuevovalor.getText().toString();
            Toast.makeText(CRUD.this,ELEMENTO, Toast.LENGTH_SHORT).show();
            renameImageInFirebase(ELEMENTO,nombreimagen);
            displayImageFromFirebase();


        }
        else if(opcion==6){
            //eliminar
            /*mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),"ELIMINADO");
            mDocRef.update(datatoUpdate);
            Toast.makeText(CRUD.this,"Elemento: "+ELEMENTO+ " eliminado", Toast.LENGTH_SHORT).show();*/
            if(boleanocategoria == true){
                CATEGORIA="CATEGORIAS";
            }
            mDocRef = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);
            Map<String, Object> datatoUpdate = new HashMap<String,Object>();
            datatoUpdate.put(String.valueOf(indicepos),FieldValue.delete());
            mDocRef.update(datatoUpdate);
            ordenar();

            deleteImageFromFirebase();
        }
        else if(opcion==7){
            //cambiar imagen


        }





    }
    private void deleteImageFromFirebase() {
        // Referencia a la imagen que deseas eliminar en Firebase Storage
        storageReference = FirebaseStorage.getInstance().getReference("RESET/"+CATEGORIA);
        StorageReference imageRef = storageReference.child(ELEMENTO + ".png");

        // Eliminar la imagen
        imageRef.delete()
                .addOnSuccessListener(aVoid -> {
                    // La imagen se eliminó exitosamente
                    Toast.makeText(CRUD.this, "Imagen eliminada con éxito", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Hubo un error al eliminar la imagen
                    Toast.makeText(CRUD.this, "Error al eliminar la imagen: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    private void renameImageInFirebase(String oldName, String newName) {
        // Referencias a los archivos viejo y nuevo
        storageReference = FirebaseStorage.getInstance().getReference("RESET/"+CATEGORIA);
        StorageReference oldRef = storageReference.child(oldName + ".png");
        StorageReference newRef = storageReference.child(newName + ".png");

        // Descargar el archivo original
        oldRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(bytes -> {
            // Subir el archivo con el nuevo nombre
            newRef.putBytes(bytes).addOnSuccessListener(taskSnapshot -> {
                // Eliminar el archivo viejo después de subir el nuevo
                oldRef.delete().addOnSuccessListener(aVoid -> {
                    Toast.makeText(CRUD.this, "Imagen renombrada con éxito", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(CRUD.this, "Error al eliminar la imagen antigua: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(CRUD.this, "Error al subir la imagen nueva: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            //Toast.makeText(CRUD.this, "Error al descargar la imagen original: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    public void ordenar(){
        mDatabase2 = FirebaseFirestore.getInstance().document("RESET/"+CATEGORIA);

        // Leer los datos actuales del documento en Firestore
        mDatabase2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Obtener los datos del documento como un Map
                        Map<String, Object> dataMap = document.getData();

                        if (dataMap != null) {
                            // Contar el número de elementos en el documento
                            int count = dataMap.size();
                            Log.d("FirestoreReassign", "El documento tiene " + count + " elementos.");

                            // Crear un nuevo mapa para los datos con claves reasignadas
                            Map<String, Object> newDataMap = new HashMap<>();
                            int newKey = 1; // Empezar desde 1

                            // Asignar nuevas claves secuenciales desde 1 hasta el número total de elementos
                            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                                newDataMap.put(String.valueOf(newKey), entry.getValue());
                                newKey++;
                            }

                            // Actualizar el documento en Firestore con las nuevas claves
                            mDatabase2.set(newDataMap).addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    // Los datos fueron actualizados correctamente
                                    Log.d("FirestoreReassign", "Datos actualizados con claves del 1 al " + count + ".");
                                } else {
                                    // Hubo un error al actualizar los datos
                                    Log.e("FirestoreReassign", "Error al actualizar los datos", updateTask.getException());
                                }
                            });

                        } else {
                            Log.d("FirestoreReassign", "El documento no contiene datos.");
                        }
                    } else {
                        Log.d("FirestoreReassign", "El documento no existe.");
                    }
                } else {
                    Log.e("FirestoreReassign", "Error al obtener el documento", task.getException());
                }
            }
        });
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
        btregresar.setVisibility(View.VISIBLE);
        editarsubcategorias.setVisibility(View.VISIBLE);
        editarcategorias.setVisibility(View.VISIBLE);



    }
    public void siguiente(View view){
        if(CATEGORIA=="Selecciona una categoria"){
            Toast.makeText(CRUD.this,"opcion no valida", Toast.LENGTH_SHORT).show();
            return;
        }
        spinner2.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.VISIBLE);
        agregar.setVisibility(View.VISIBLE);
        //Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
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
            spinnerOptions.add("+ NUEVA CATEGORIA");
            mySpinner.setAdapter(adapter);

            // Configura un listener para manejar la selección de elementos
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Acción al seleccionar un elemento
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    CATEGORIA = selectedItem;
                    //Toast.makeText(CRUD.this,CATEGORIA, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(CRUD.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
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
            spinnerOptions2.add("+ NUEVO PRODUCTO");
            mySpinner.setAdapter(adapter);

            // Configura un listener para manejar la selección de elementos
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Acción al seleccionar un elemento
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    ELEMENTO = selectedItem;
                    indicepos = spinnerOptions2.indexOf(ELEMENTO);
                    //Toast.makeText(CRUD.this,ELEMENTO, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(CRUD.this, "elemento en posicion"+String.valueOf(indicepos), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(CRUD.this, "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();

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
                                //Toast.makeText(CRUD.this, String.valueOf(cantidadCampos), Toast.LENGTH_SHORT).show();
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