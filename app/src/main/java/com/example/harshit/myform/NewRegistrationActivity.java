package com.example.harshit.myform;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewRegistrationActivity extends AppCompatActivity {


    Spinner spinnerRegion,grads,catSpinner;
    EditText fName,lName,address,pin,city,stateEt,country,gen,cat,passwordEt,dob,mob,emailEt,bmiEt,heightEt;
    public static final String REGISTER_URL = "http://hkapswillrock.000webhostapp.com/newRegister.php";

    public static final String KEY_FIRSTNAME = "firstname";
   // public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_STATE = "state";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_REGION="region";
    public static final String KEY_QUALIFICATION="qualification";
    public static final String KEY_HEIGHT="height";
    public static final String KEY_BMI="bmi";
    public static final String KEY_DATE="date";
    public static final String KEY_USERID="userid";



    String region,qualification,category;



    User user;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);

        fName=(EditText)findViewById(R.id.fName);
        lName=(EditText)findViewById(R.id.lName);
       // address=(EditText)findViewById(R.id.address);
       // pin=(EditText)findViewById(R.id.pin);
       // city=(EditText)findViewById(R.id.city);
        stateEt=(EditText)findViewById(R.id.state);
       // country=(EditText)findViewById(R.id.nation);
        gen=(EditText)findViewById(R.id.gen);
       // cat=(EditText)findViewById(R.id.);
        //passwordEt=(EditText)findViewById(R.id.password);
       // dob=(EditText)findViewById(R.id.dob);
        mob=(EditText)findViewById(R.id.mob);
        emailEt=(EditText)findViewById(R.id.email);

        bmiEt=(EditText)findViewById(R.id.bmi);
        heightEt=(EditText)findViewById(R.id.height);



        submit=(Button) findViewById(R.id.submit);

        spinnerRegion=(Spinner)findViewById(R.id.region);
        grads=(Spinner)findViewById(R.id.grad);
        catSpinner=(Spinner)findViewById(R.id.catSpinner);









        String[] regs = new String[]{
                "Select Region",
                "Northern",
                "Southern",
                "Eastern",
                "Western"
        };

        String[] degs=new String[]{
                "Qualification",
                "10th Pass",
                "10+2",
                "Graduate and Above"
        }
                ;

        String[] cats=new String[]{"Category","General","SC","ST", "OBC","Others"};

        List<String> catList=new ArrayList<>(Arrays.asList(cats));
       List<String> regList=new ArrayList<>(Arrays.asList(regs));
        List<String> degList=new ArrayList<>(Arrays.asList(degs));



        ArrayAdapter<String> catAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,catList){

            @Override
            public boolean isEnabled(int position) {

                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }



        };

        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        catSpinner.setAdapter(catAdapter);

        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=(String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> degAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,degList){
            @Override
            public boolean isEnabled(int position) {

                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }


        }
                ;

        degAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grads.setAdapter(degAdapter);

        ArrayAdapter<String> spinnerArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,regList){
            @Override
            public boolean isEnabled(int position) {

                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRegion.setAdapter(spinnerArrayAdapter);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                region= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        grads.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                qualification=(String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        //ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,)










        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!validateFirstName(fName.getText().toString()))
                    Toast.makeText(NewRegistrationActivity.this,"Please enter valid first name",Toast.LENGTH_SHORT).show();

                else if(!validateLastName(lName.getText().toString()))
                    Toast.makeText(NewRegistrationActivity.this,"Please enter valid last name",Toast.LENGTH_SHORT).show();



         else if(mob.getText().toString().length()!=10)
             Toast.makeText(NewRegistrationActivity.this,"Please enter valid mobile no.",Toast.LENGTH_SHORT).show();

                else if(!EmailValidator.getInstance().isValid(emailEt.getText().toString()))
             Toast.makeText(NewRegistrationActivity.this,"Please enter valid email id",Toast.LENGTH_SHORT).show();

                        else if(!gen.getText().toString().equals("Male")&&!gen.getText().toString().equals("Female"))
                    Toast.makeText(NewRegistrationActivity.this,"Please enter a valid gender",Toast.LENGTH_SHORT).show();











               // if(region.equals("Select Region")||qualification.equals("Qualification")||category.equals("Category"))
                    //Toast.makeText(NewRegistrationActivity.this,"Incorrect Details",Toast.LENGTH_SHORT).show();
                //register user here

                //plz also use validation checks

               else {
                    user = new User(fName.getText().toString(),
                            lName.getText().toString(),
                            stateEt.getText().toString(),
                            gen.getText().toString(),
                            category,
                            mob.getText().toString(),
                            emailEt.getText().toString(),
                            heightEt.getText().toString(),
                            bmiEt.getText().toString(),
                            region,
                            qualification
                    );

                    //submit user details

                    //Intent i=new Intent(NewRegistrationActivity.this,UserPage.class);

                    //i.putExtra("logID",user.generateUserID());

                  //  startActivity(i);
                  //  the above intent is to be put in registerUser()

                    String id=user.generateUserID();

                 registerUser(id);

                }




            }
        });








    }

    private void registerUser(String id)  {

        final String firstname=fName.getText().toString().trim();
        final String lastname=lName.getText().toString().trim();
       // final String password=passwordEt.getText().toString().trim();
        final String email=emailEt.getText().toString().trim();
        final String mobile=mob.getText().toString().trim();
        final String gender=gen.getText().toString().trim();
       // final String category=cat.getText().toString().trim();
        final String state=stateEt.getText().toString().trim();
        final String height=heightEt.getText().toString().trim();
        final String bmi=bmiEt.getText().toString().trim();
        final String userid=id;
        //date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
       final String date = "" + mdformat.format(calendar.getTime());

    //    Toast.makeText(NewRegistrationActivity.this,userid,Toast.LENGTH_SHORT).show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(NewRegistrationActivity.this,response,Toast.LENGTH_SHORT).show();

                if(response.equals("successfully registered")) {
//                   //open UserPage
//
                      Intent i=new Intent(NewRegistrationActivity.this,UserPage.class);

                    i.putExtra("fname",firstname);
                    i.putExtra("lname",lastname);
                    i.putExtra("state",state);
                    i.putExtra("gender",gender);
                    i.putExtra("category",category);
                    i.putExtra("mob",mobile);
                    i.putExtra("email",email);
                    i.putExtra("logID",userid);
                    i.putExtra("height",user.getHeight());
                    i.putExtra("bmi",user.getBmi());
                    i.putExtra("region",user.getRegion());
                    i.putExtra("graduation",qualification);


                     startActivity(i);




                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewRegistrationActivity.this,error.toString(),Toast.LENGTH_SHORT).show();



            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> params=new HashMap<>();
                params.put(KEY_FIRSTNAME,firstname);
                params.put(KEY_LASTNAME,lastname);
                params.put(KEY_STATE,state);
                params.put(KEY_GENDER,gender);
                params.put(KEY_CATEGORY,category);
               // params.put(KEY_PASSWORD,password);
                params.put(KEY_MOBILE,mobile);
                params.put(KEY_EMAIL,email);
                params.put(KEY_REGION,region);
                params.put(KEY_QUALIFICATION,qualification);
                params.put(KEY_HEIGHT,height);
                params.put(KEY_BMI,bmi);
                params.put(KEY_USERID,userid);
                params.put(KEY_DATE,date);

                return params;



            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

    public static boolean validateFirstName( String firstName )
    {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    } // end method validateFirstName

    // validate last name
    public static boolean validateLastName( String lastName )
    {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    } // end method validateLastNa


}
