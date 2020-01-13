package com.example.dipto.digitalmess;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Create_Id extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_id);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference("Member/"+user.getUid().toString().trim());
    }

    public void clickedOnNext(View view){
        Context context = getApplicationContext();
        Intent i = new Intent(context,Before_Join.class);
        i.putExtra("caller","Create_Id");
        startActivity(i);
    }
    void give_new_member_a_id(){
        TextView idtxt = (TextView) findViewById(R.id.id);
        TextView nametxt = (TextView) findViewById(R.id.user_full_name);
        idtxt.setText(user.getUid().toString().trim());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                member = dataSnapshot.getValue(Member.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        nametxt.setText(member.firstName + " " + member.lastName);
    }
    public void showToast(String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
