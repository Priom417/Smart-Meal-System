package com.example.dipto.digitalmess;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Create_Member extends AppCompatActivity {
    String val[] = new String[10];
    public static Member m;
    public static DatabaseReference memberDatabase;
    public FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    EditText editTextFName;
    EditText editTextLName;
    EditText editTextPhoneNumber;
    EditText editTextEmail;
    EditText editTextUserName;
    EditText editTextPassword;
    Spinner workingActivity;
    Spinner gender;




    public Member getM() {
        return m;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);
        Button button = (Button) findViewById(R.id.create_member_account_button);
        editTextFName = (EditText) findViewById(R.id.member_first_name);
        editTextLName = (EditText) findViewById(R.id.member_last_name);
        editTextPhoneNumber = (EditText) findViewById(R.id.member_phone_number);
        editTextEmail = (EditText) findViewById(R.id.member_email);
        editTextUserName = (EditText) findViewById(R.id.member_username_create);
        editTextPassword = (EditText) findViewById(R.id.member_password_create);
        workingActivity = (Spinner) findViewById(R.id.member_working_activity);
        gender = (Spinner) findViewById(R.id.member_gender);
        progressDialog = new ProgressDialog(this);
        memberDatabase = FirebaseDatabase.getInstance().getReference("Members");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void showToast(String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public boolean any_empty_field(){
        val[0] = gender.getSelectedItem().toString();
        val[1] = editTextFName.getText().toString();
        val[2] = workingActivity.getSelectedItem().toString();
        val[3] = editTextLName.getText().toString();
        val[4] = editTextPhoneNumber.getText().toString();
        val[5] = editTextPassword.getText().toString();
        val[6] = editTextEmail.getText().toString();
        val[7] = editTextUserName.getText().toString();
        for(int i=0;i<=7;i++){
            if(TextUtils.isEmpty(val[i])){
                return true;
            }
        }
        return false;
    }

    public void Save_Member_info_In_Firebase(){
            Log.v("Priom","Save data in firebase called");
            showToast("Save data in firebase");
            m = new Member();
            firebaseAuth = FirebaseAuth.getInstance();
            m.gender = val[0];
            m.firstName = val[1];
            m.lastName = val[3];
            m.working_activity = val[2];
            m.phoneNumber = val[4];
            m.password = val[5];
            m.email = val[6];
            m.userName = val[7];

            FirebaseUser user = firebaseAuth.getCurrentUser();
            m.member_id = user.getUid();
            memberDatabase.child(m.member_id).setValue(m);
    }

    private void createAccount(String email, String password){
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Create_Member.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showToast("Account Create Success.......");
                            Save_Member_info_In_Firebase();
                            Context context = getApplicationContext();
                            Intent i = new Intent(context,Create_Id.class);
                            startActivity(i);
                        } else {
                            showToast("Account Create Faild.......");
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void clickedOnCreateAccountButton(View view){
        if(any_empty_field()){
            showToast("Fill Up All The Fields");
        }
        else{
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            createAccount(email,password);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Priom","onStart create_member");
        //Create_User_Auth_And_Save_Data_In_Firebase();


    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Priom","onDestroy create_member");
    }
    @Override
    protected void onPause() {
        super.onPause();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //createAccount(email,password);
        //Create_User_Auth_And_Save_Data_In_Firebase();
        Log.v("Priom","onPause create_member");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Priom","onResume create_member");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("Priom","onRestart create_member");
    }
}
