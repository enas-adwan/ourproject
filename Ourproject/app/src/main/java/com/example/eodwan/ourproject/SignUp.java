package com.example.eodwan.ourproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private EditText Num;
    private EditText Password;
    private Button BUtton;
    private EditText ConfirmPassword;

    String valid_flag=null;
    private static final String REGISTER_URL = "http://192.168.1.7/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Num = (EditText) findViewById(R.id.num);
        Num.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                String num = Num.getText().toString().trim();
                Num.setError(null);
                valid_flag=null;
                if (!isValidEmail(num)) {

                    Num.setError("Wrong ID Number");
                    valid_flag="err";
                }
                else{
                    valid_flag=null;
                }
            }
        });

        Password = (EditText) findViewById(R.id.password);
        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                String password = Password.getText().toString().trim();
                valid_flag=null;
                if (!isValidPassword(password)) {
                    Password.setError("Invalid password");
                    valid_flag="err";
                }else{
                    valid_flag=null;
                }
            }
        });
        ConfirmPassword = (EditText) findViewById(R.id.confirmpassword);
        ConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                String confirmpassword = ConfirmPassword.getText().toString().trim();
                valid_flag=null;
                if (!isValidConfirmPassword(confirmpassword)) {
                    ConfirmPassword.setError("Invalid password");
                    valid_flag="err";
                }else{
                    valid_flag=null;
                }
            }
        });

        BUtton = (Button) findViewById(R.id.button);
        BUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser(valid_flag);

             /*   new SignUpActivity(this,status,role,0).execute(username,password);*/


            }
        });
    }

    private void registerUser(String valid_flag) {

        String num = Num.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String confirmpassword = ConfirmPassword.getText().toString().trim();
        if (!isValidEmail(num)) {
            Num.setError("Wrong ID Number");
            valid_flag="err";
        }
        else {
            Num.setError(null);
            valid_flag=null;
        }



        if (!isValidPassword(password)) {
            Password.setError("Invalid password");
            valid_flag="err";
        }
        else {
            Password.setError(null);
            valid_flag=null;
        }

        if (!isValidConfirmPassword(confirmpassword)) {
            ConfirmPassword.setError("Invalid password");
            valid_flag="err";
        }else{
            valid_flag=null;
        }



        if(valid_flag==null){


            String secret="3CH6knCsenas2va8GrHk4mf3JqmUctCM";
            register(num, password,secret,valid_flag);}
        else{

            Toast.makeText(getApplicationContext(),"please enter the right requirment",Toast.LENGTH_LONG).show();
        }


    }


    private boolean isValidEmail(String num) {
        String NUMBER_PATTERN = "^[0-9]{8}";

        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
    private boolean isValidConfirmPassword(String pass) {
        String password = Password.getText().toString().trim();
        Pattern pattern = Pattern.compile(password);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
   
    }
    private void register(String name, String password, String secret, final String valid_flag) {
       /* String urlSuffix = "?name=" + name + "&password=" + password;*/





        class RegisterUser extends AsyncTask<String, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Toast.makeText(getApplicationContext(),s+valid_flag,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {
              /*  String s = params[0];
              Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(REGISTER_URL+s));
                startActivity(i);*/
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);

                data.put("password",params[1]);
                data.put("secret",params[2]);


                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;

               /* BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return "no";
                }*/
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,password,secret);

       /* ru.execute(urlSuffix);*/
    }}
