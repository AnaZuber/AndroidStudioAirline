package com.example.airline.ui.location;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.airline.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LocationActivity extends Fragment {

    private EditText address;
    private EditText city;
    private EditText postalCode;
    private EditText country;
    private Button save;
    private LocationViewModel locationViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        locationViewModel =
                new ViewModelProvider(this).get(LocationViewModel.class);
        View root = inflater.inflate(R.layout.activity_location, container, false);
        save = (Button) root.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                address = (EditText) (root.findViewById(R.id.address));
                city = (EditText) (root.findViewById(R.id.city));
                postalCode = (EditText) (root.findViewById(R.id.postalCode));
                country = (EditText) (root.findViewById(R.id.country));
                locationViewModel.setAddress(address.getText().toString());
                locationViewModel.setCity(city.getText().toString());
                locationViewModel.setPostalCode(postalCode.getText().toString());
                locationViewModel.setCountry(country.getText().toString());
                Log.d("idemo", locationViewModel.getCity());
                postRequest();
            }
        });
        return root;
    }

    public void sendRequest() {
        String url = "http://bjelicaluka.live:4000/locations";
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

    public void postRequest() {
        String url = "http://bjelicaluka.live:4000/locations";
        RequestQueue rq = Volley.newRequestQueue(this.getContext());

        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("address", locationViewModel.getAddress());
            jsonBody.put("city", locationViewModel.getCity());
            jsonBody.put("postalCode", locationViewModel.getPostalCode());
            jsonBody.put("country", locationViewModel.getCountry());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("BACKEND ERROR", error.getMessage());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            rq.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




