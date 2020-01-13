package com.example.dipto.digitalmess;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class View_personal_info extends Fragment {
    View view;
    public static Present present = new Present();
    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public View_personal_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_view_personal_info, container, false);
        final String s = getActivity().getIntent().getExtras().getString("caller");

        Create_Member m = new Create_Member();
        DatabaseReference mydatabaseReference = firebaseDatabase.getReference("Members");


        mydatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Member m = new Member();


                Log.v("Priom",s);

                if(s.equals("MainActivity")){
                    MainActivity temp = new MainActivity();
                    m=temp.getM();
                    present.setLogged_in_member(m);
                    showToast(m.getFirstName());
                }
                else{
                    Create_Member temp = new Create_Member();
                    m=temp.getM();
                    present.setLogged_in_member(m);
                    showToast(m.getFirstName());
                }
                TextView name = (TextView) view.findViewById(R.id.member_name);
                TextView username = (TextView) view.findViewById(R.id.member_username);
                TextView gender = (TextView) view.findViewById(R.id.member_gender);
                TextView activity = (TextView) view.findViewById(R.id.member_working_activity);
                TextView phone = (TextView) view.findViewById(R.id.member_phone);
                TextView email = (TextView) view.findViewById(R.id.member_email);

                name.setText(m.getFirstName() + " " + m.getLastName());
                username.setText(m.getUserName());
                gender.setText(m.getGender());
                activity.setText(m.getWorking_activity());
                phone.setText(m.getPhoneNumber());
                email.setText(m.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
    public void showToast(String string){
        Context context = getContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        Log.v("Priom","On Start per_info");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("Priom","On Resume per_info");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("Priom","On Pause per_info");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("Priom","On Stop per_info");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Priom","On Destroy per_info");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("Priom","On Detach per_info");
    }*/
}
