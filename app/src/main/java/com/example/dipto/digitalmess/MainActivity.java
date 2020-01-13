package com.example.dipto.digitalmess;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static Member m;
    private FirebaseAuth firebaseAuth;

    public static Member getM() {
        return m;
    }

    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.member_username_login);
        password = (EditText) findViewById(R.id.member_password_signin);
    }
    public void createNewMemberAccount(View view){
        Context context = getApplicationContext();
        Intent i = new Intent(context,Create_Member.class);
        user = username.getText().toString();
        pass = password.getText().toString();
        startActivity(i);
    }

    public void clicked_on_login_button(View view){
        user = username.getText().toString();
        pass = password.getText().toString();

        DatabaseReference databaseReference = firebaseDatabase.getReference("Members");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    m = d.getValue(Member.class);
                    if(m.getUserName().equals(user) && m.getPassword().equals(pass)){
                        Context context = getApplicationContext();
                        Intent i = new Intent(context,Before_Join.class);
                        i.putExtra("caller","MainActivity");
                        startActivity(i);
                        return;
                    }
                }
                showToast("Incorrect Username or Password");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void showToast(String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
