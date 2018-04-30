package com.example.harshit.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserPage extends AppCompatActivity {

    TextView fname,lname,state,gender,category,mobile,email,id,graduation,height,bmi,region,elligiblity;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        fname=(TextView)findViewById(R.id.firstname);
        lname=(TextView)findViewById(R.id.lastname);
        state=(TextView)findViewById(R.id.userState);
        gender=(TextView)findViewById(R.id.userGender);
        category=(TextView)findViewById(R.id.userCategory);
        mobile=(TextView)findViewById(R.id.userMobile);
        email=(TextView)findViewById(R.id.userEmail);
        id=(TextView)findViewById(R.id.userID);
       graduation=(TextView)findViewById(R.id.userGrad);
        height=(TextView)findViewById(R.id.userHeight);
        bmi=(TextView)findViewById(R.id.userBMI);
        region=(TextView)findViewById(R.id.userRegion);
        elligiblity=(TextView)findViewById(R.id.userEligiblity);

        logOut=(Button)findViewById(R.id.logOut);




        Intent intent=getIntent();
        fname.setText("First Name: "+intent.getStringExtra("fname"));
        lname.setText("Last Name: "+intent.getStringExtra("lname"));
        state.setText("State: "+intent.getStringExtra("state"));
        gender.setText("Gender: "+intent.getStringExtra("gender"));
        category.setText("Category: "+intent.getStringExtra("category"));
        mobile.setText("Mobile: "+intent.getStringExtra("mob"));
        email.setText("Email: "+intent.getStringExtra("email"));
        id.setText("User ID: "+intent.getStringExtra("logID"));
        graduation.setText("Graduation: "+intent.getStringExtra("graduation"));
        height.setText("Height: "+intent.getStringExtra("height")+"Cm");
        bmi.setText("BMI: "+intent.getStringExtra("bmi"));
        region.setText("Region: "+intent.getStringExtra("region"));

        int check=Integer.valueOf(intent.getStringExtra("logID"))%10;
        if(check==0)
            elligiblity.setText("Elligiblity: No");
        else
        elligiblity.setText("Elligiblity: Yes");


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(UserPage.this,MainActivity.class);

                startActivity(i1);

                finish();
            }
        });




    }
}
