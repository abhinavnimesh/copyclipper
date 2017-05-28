package com.animator_abhi.copyclipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {

FirebaseUser user;
    DatabaseReference ref;
    public FirebaseDatabase database;
    private Button notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notify= (Button) findViewById(R.id.buttonNotify);
       database=FirebaseDatabase.getInstance();
          ref=database.getReference().child("users");
        FirebaseAuth auth=FirebaseAuth.getInstance();
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setProviders(AuthUI.GOOGLE_PROVIDER)
                .build(),1);
        user=auth.getCurrentUser();
        Prefs.setUserId(getApplicationContext(), user.getUid());
        ref.child(user.getUid()).child("clip").setValue("raja");
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tkn = FirebaseInstanceId.getInstance().getToken();
                Toast.makeText(LoginActivity.this, "Current token ["+tkn+"]",
                        Toast.LENGTH_LONG).show();
                Log.d("App", "Token ["+tkn+"]");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "hey");
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });


    }
}
