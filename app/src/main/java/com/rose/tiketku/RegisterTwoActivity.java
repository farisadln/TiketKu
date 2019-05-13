package com.rose.tiketku;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class RegisterTwoActivity extends AppCompatActivity {


    LinearLayout btn_back;
    Button btn_continue, btn_add_photo;
    ImageView pic_photo_register_user;
    EditText bio, nama_lengkap;

    Uri photo_location;
    Integer photo_max = 1;

    DatabaseReference reference;
    StorageReference storage;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        getUsernameLocal();

        pic_photo_register_user = findViewById(R.id.pic_photo_register_user);
        bio = findViewById(R.id.bio);
        btn_continue = findViewById(R.id.btn_continue);
        btn_back = findViewById(R.id.btn_back);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        btn_add_photo = findViewById(R.id.btn_add_photo);



        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhoto();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goprev = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(goprev);
            }
        });



        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_continue.setEnabled(false);
                btn_continue.setText("Loading ...");

//                 Set firebase
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username_key_new);
                storage = FirebaseStorage.getInstance().getReference()
                        .child("Photousers").child(username_key_new);

                //verify
                if (photo_location != null){
                    StorageReference storageReferences =
                            storage.child(System.currentTimeMillis() + "." + getFileExtension(photo_location));

                    storageReferences.putFile(photo_location)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    String uri_photo = taskSnapshot.getStorage().getDownloadUrl().toString();
                                    reference.getRef().child("url_photo_profile").setValue(uri_photo);
                                    reference.getRef().child("nama_lengkap").setValue(nama_lengkap.getText().toString());
                                    reference.getRef().child("bio").setValue(bio.getText().toString());

                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Intent gosuccess = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                            startActivity(gosuccess);
                        }
                    });
                }


                Intent gosucces = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                startActivity(gosucces);
            }
        });

    }

    String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void findPhoto(){
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null){

            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pic_photo_register_user);

        }
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");

    }
}
