package com.example.kkenneally.appwamp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button submit, getData;
    EditText Name, Email;
    //String server_url = "http://172.24.10.101/updateStudent.php";
    String server_url = "http://172.24.10.101/update_info.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.button);
        Name = findViewById(R.id.editText);
        Email = findViewById(R.id.editText2);
        builder = new AlertDialog.Builder(MainActivity.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String name, email;
               name = Name.getText().toString();
               email = Email.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response :"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Name.setText("");
                                        Email.setText("");
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }
                 , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("email", email);

                        return params;
                    }
                };

                MySingleton.getInstance(MainActivity.this).addToRequeustQue(stringRequest);
            }
        });

        getDataFromServer();


    }

    void getDataFromServer(){

        //getData.findViewById(R.id.button2);

       /* getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RetrieveActivity.class, );
            }
        });
        */
    }


}
