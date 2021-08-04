package com.example.whether;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Creating Objects:-
    EditText input_text;
    Button search;
    TextView display_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Objects:-
        input_text=findViewById(R.id.input_text);
        search=findViewById(R.id.search);
        display_text=findViewById(R.id.display_text);

        //Operations to perform after clicking on search button:-
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(MainActivity.this, "Button was Clicked", Toast.LENGTH_SHORT).show();

                String apikey="5376cdd0914b706c1769156a816ab6ed\n";

                //Taking input from the user and converting it into string:-
                String city=input_text.getText().toString();

                String url="https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5376cdd0914b706c1769156a816ab6ed";

                //Asking request for the volley library for getting the content:-
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());

                // Creating a request to get the JASON object from the given url:-
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            // Taking content from the main in the JASON file and storing it in a object named object:-
                            JSONObject object=response.getJSONObject("main");

                            //Now from the main folder taking a specific data of temp and storing it in a string name temperature:-
                            String temperature = object.getString("temp");

                            //Converting the string temperature into double to carry out arithmetic operations:-
                            Double temp=Double.parseDouble(temperature)-273.15;

                            //Converting the double value into string and displaying it:-
                            display_text.setText("Temperature : " + temp.toString().substring(0,5) + " °C");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //If error occurs then it app will show a Toast:-
                        Toast.makeText(MainActivity.this, "Please enter valid City name ", Toast.LENGTH_SHORT).show();
                    }
                });

                //Adding next request from the user in the input text:-
                queue.add(request);
            }
        });
    }
}