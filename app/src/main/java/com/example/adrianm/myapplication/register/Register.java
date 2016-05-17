package com.example.adrianm.myapplication.register;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adrianm.myapplication.MainActivity;
import com.example.adrianm.myapplication.R;
import com.example.adrianm.myapplication.config.ApiRequest;
import com.example.adrianm.myapplication.config.Settings;

import org.json.JSONObject;

import java.net.URL;

public class Register extends AppCompatActivity {

    EditText mUsername;
    EditText mEmail;
    EditText mPassword;

    UserCreateTask mTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUsername = (EditText) findViewById(R.id.register_name);
        mEmail = (EditText) findViewById(R.id.register_email);
        mPassword = (EditText) findViewById(R.id.register_password);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Button signup = (Button) findViewById(R.id.btn_signup);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("status","Entro metodo crear");
                attempRegister();
            }
        });


    }

    public void attempRegister() {
        if (mTask != null){
            return;
        }

        String username = mUsername.getText().toString();
        String email = mEmail.getText().toString();
        String pwd = mPassword.getText().toString();

        mTask = new UserCreateTask(email,username,pwd,this);
        mTask.execute((Void)null);

    }

    public class UserCreateTask extends AsyncTask<Void,Void,Boolean>{

        private String email, username, password;
        private Context context;

        public UserCreateTask(String email, String username, String password, Context context) {
            this.email = email;
            this.username = username;
            this.password = password;
            this.context = context;
        }
        @Override
        protected Boolean doInBackground(Void... params) {

            return apiData();
        }

        @Override
        protected void onPostExecute(Boolean success) {

            if(success) {
                Intent i = new Intent(this.context, MainActivity.class);
                this.context.startActivity(i);
            }

        }

        public boolean apiData() {
            try {
                ApiRequest api = new ApiRequest("/api/users/create/");
                JSONObject response = api.post("username="+this.username+"&email="+this.email+"&password="+this.password);
                Log.d("Respuesta",response.getString("username"));
                Thread.sleep(2000);

            }
            catch (Exception e ){
                Log.d("Error:",e.getMessage());
            }
            return true;
        }


    }

}


