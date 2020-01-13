package com.example.dipto.digitalmess;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Create_Mess extends Fragment {

    View view;
    public static Present p = new Present();
    public static DatabaseReference databaseReference;

    public Create_Mess() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_mess, container, false);

        Button b = (Button) view.findViewById(R.id.create_mess_account_final);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent i = new Intent(context,Member_Account.class);

                EditText messName = (EditText) getView().findViewById(R.id.mess_name);
                EditText messLocation = (EditText) getView().findViewById(R.id.mess_location);
                Spinner messType = (Spinner) getView().findViewById(R.id.mess_type_spinner);

                if(TextUtils.isEmpty(messLocation.getText()) ||
                        TextUtils.isEmpty(messType.getSelectedItem().toString()) ||
                        TextUtils.isEmpty(messName.getText())){
                    showToast("Fill Up All The Fields");
                }
                else {
                    insert_info_in_mess();
                    startActivity(i);
                }
            }
        });
        return view;
    }

    public void insert_info_in_mess(){

        EditText messName = (EditText) getView().findViewById(R.id.mess_name);
        EditText messLocation = (EditText) getView().findViewById(R.id.mess_location);
        Spinner messType = (Spinner) getView().findViewById(R.id.mess_type_spinner);

        databaseReference = FirebaseDatabase.getInstance().getReference("Mess");
        Mess mess = new Mess();



        mess.setName(messName.getText().toString());
        mess.setLocation(messLocation.getText().toString());
        mess.setMember_type(messType.getSelectedItem().toString());
        String s = databaseReference.push().getKey();
        mess.setMess_id(s);
        Member m = p.getLogged_in_member();
        mess.getMemberslist().add(m);
        mess.setNumber_of_members(mess.getMemberslist().size());
        showToast(m.getFirstName());
        databaseReference.child(s).setValue(mess);

    }
    public void showToast(String string){
        Context context = getContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
