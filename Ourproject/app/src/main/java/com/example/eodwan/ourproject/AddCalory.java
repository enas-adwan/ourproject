package com.example.eodwan.ourproject;

        import android.content.Intent;
        import android.database.Cursor;
        import android.support.v7.app.AppCompatActivity;
        import android.app.Activity;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import android.app.Activity;
        import android.os.Bundle;

        import android.view.View.OnClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.Toast;

/**
 * Created by hp on 03/09/2016.
 */
public class AddCalory extends AppCompatActivity  {
        private Spinner spinner1;
        EditText calory_num;
        Context context=this;
        ListView listView;
        SQLiteDatabase sqLiteDatabase;
        recipeDbHelper userDbHelper;
        recipeDbHelper userDbHelper2;
        Cursor cursor;
        ListDataAdpter listDataAdpter;
        TextView calorySum;
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.calory);
                calory_num=(EditText)findViewById(R.id.edit1);
                spinner1 = (Spinner) findViewById(R.id.spinner1);

        }

        public void addContant(View view)
        {
                float num=Float.valueOf(calory_num.getText().toString());
                userDbHelper=new  recipeDbHelper(context);
                sqLiteDatabase=userDbHelper.getWritableDatabase();
               String quintity= String.valueOf(spinner1.getSelectedItem());
                switch(quintity){

                        case "Spoon":
                                num=num/6.666666666666667f;
                                break;
                    case "Cup":
                        num=num*1.1f;
                        break;
                        case "1/2 Cup":
                               num=num*.6f;
                                break;
                        case "3/4 Cup": /** AlerDialog when click on Exit */
                                num=num*.85f;
                                break;

                }
                userDbHelper.addinnformation(num,sqLiteDatabase);
                Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
                userDbHelper.close();
                listView=(ListView)findViewById(R.id.list_view);
                calorySum=(TextView)findViewById(R.id.calory_sum);
                listDataAdpter=new ListDataAdpter(getApplicationContext(),R.layout.row_layout);
                listView.setAdapter(listDataAdpter);
                userDbHelper2=new recipeDbHelper(getApplicationContext());
                sqLiteDatabase=userDbHelper2.getReadableDatabase();
                cursor=userDbHelper2.getinformation(sqLiteDatabase);

                if(cursor.moveToFirst())
                {
                        do {
                                Float sum=0.0f;
                                Float calory;
                                calory=cursor.getFloat(0);
                                sum=userDbHelper2.getTotal(sqLiteDatabase);
                                calorySum.setText(String.valueOf(sum));
                                DataProvider dataProvider=new DataProvider(calory);
                                listDataAdpter.add(dataProvider);

                        }while (cursor.moveToNext());
                }

        }



}
