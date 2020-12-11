package com.example.airline.ui.myProfile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.airline.R;
import com.example.airline.ui.login.LoginViewModel;

import org.json.JSONObject;

public class MyProfileActivity extends Fragment {

    private EditText firstname;
    private EditText lastname;
    private Button save;
    private MyProfileViewModel myProfileViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myProfileViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);
        View root = inflater.inflate(R.layout.activity_myprofile, container, false);
        save = (Button) root.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                firstname = (EditText) (root.findViewById(R.id.firstname));
                lastname = (EditText) (root.findViewById(R.id.lastname));
                myProfileViewModel.setFirstname(firstname.getText().toString());
                myProfileViewModel.setLastname(lastname.getText().toString());
                Log.d("idemo", myProfileViewModel.getFirstname());
                sendRequest();
            }
        });
        return root;
    }

    public void sendRequest() {
        String url = "http://bjelicaluka.live:4000/tickets";
        RequestQueue rq = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ana je govno", response.toString());
                        //textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("BACKEND ERROR", error.getMessage());
                    }
                });

        rq.add(jsonObjectRequest);
    }
}



