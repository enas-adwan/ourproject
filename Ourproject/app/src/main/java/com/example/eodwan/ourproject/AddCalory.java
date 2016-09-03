package com.example.eodwan.ourproject;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.app.Activity;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
/**
 * Created by hp on 03/09/2016.
 */
public class AddCalory extends AppCompatActivity  {
        EditText calory_num;
        Context context=this;
        recipeDbHelper userDbHelper;
        SQLiteDatabase sqLiteDatabase;


        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.calory);
                calory_num=(EditText)findViewById(R.id.edit1);

        }

        public void addContant(View view)
        {
                float num=Float.valueOf(calory_num.getText().toString());
                userDbHelper=new  recipeDbHelper(context);
                sqLiteDatabase=userDbHelper.getWritableDatabase();
                userDbHelper.addinnformation(num,sqLiteDatabase);
                Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
                userDbHelper.close();
        }


        public void showData(View view)
        {
                Intent i = new Intent(AddCalory.this,DataListActivity.class);
                startActivity(i);
        }
}
