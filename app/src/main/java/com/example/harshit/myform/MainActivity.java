package com.example.harshit.myform;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText logId,pWd;
    Button logIn,Reg;

    public static  String DATA_URL = "http://hkapswillrock.000webhostapp.com/newLogin.php"
    ;

    private ProgressDialog loading;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logId=(EditText)findViewById(R.id.logId);
        pWd=(EditText)findViewById(R.id.editText3);
        logIn=(Button)findViewById(R.id.button);
        Reg=(Button)findViewById(R.id.button2);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email=logId.getText().toString().trim();
                final String password=pWd.getText().toString().trim();



                loading = ProgressDialog.show(MainActivity.this,"Please wait...","Fetching...",false,false);

                StringRequest stringRequest=new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("Please enter all the values")||response.equals("Incorrect email id")||response.equals("error loading'")||response.equals("Incorrect user id")) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }

                        else
                        showJSON(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();

                        Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params=new HashMap<String, String>();
                        params.put("email",email);
                        params.put("userid",password);
                        return params;
                    }
                };

                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);


                //show user details

            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show reg. form

                Intent i=new Intent(MainActivity.this,NewRegistrationActivity.class);
                startActivity(i);

            }
        });



    }

    private void showJSON(String response) {

        String firstname="";
        String lastname="";
        String state="";
        String gender="";
        String category="";
        String mobile="";
        String email="";
        String height="";
        String bmi="";
        String qualification="";
        String region="";
        String userid="";



        try {
            JSONObject jsonObject=new JSONObject(response);

            JSONArray result=jsonObject.getJSONArray("result");

            JSONObject userData=result.getJSONObject(0);

            firstname=userData.getString("firstname");
            lastname=userData.getString("lastname");
            state=userData.getString("state");
            gender=userData.getString("gender");
            category=userData.getString("category");
            mobile=userData.getString("mobile");
            email=userData.getString("email");
            height=userData.getString("height");
            bmi=userData.getString("bmi");
            region=userData.getString("region");
            qualification=userData.getString("qualification");
            userid=userData.getString("userid");






        } catch (JSONException e) {
            e.printStackTrace();
        }

       // Toast.makeText(MainActivity.this,firstname,Toast.LENGTH_SHORT).show();

      //  user=new User(firstname,lastname,state,gender,category,mobile,email);

        Intent i1=new Intent(MainActivity.this,UserPage.class);
        i1.putExtra("fname",firstname);

        i1.putExtra("lname",lastname);

        i1.putExtra("state",state);

        i1.putExtra("gender",gender);

        i1.putExtra("category",category);

        i1.putExtra("mob",mobile);

        i1.putExtra("email",email);

        i1.putExtra("logID",userid);
        i1.putExtra("height",height);
        i1.putExtra("bmi",bmi);
        i1.putExtra("qualification",qualification);
        i1.putExtra("region",region);

        startActivity(i1);








    }
}
