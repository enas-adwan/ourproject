package com.example.eodwan.ourproject;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ListView;
        import android.widget.TextView;


public class DataListActivity extends ActionBarActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
   recipeDbHelper userDbHelper;
    Cursor cursor;

    ListDataAdpter listDataAdpter;
    TextView calorySum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);
        listView=(ListView)findViewById(R.id.list_view);
        calorySum=(TextView)findViewById(R.id.calory_sum);
        listDataAdpter=new ListDataAdpter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdpter);
        userDbHelper=new recipeDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        cursor=userDbHelper.getinformation(sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            do {
              Float sum=0.0f;
                Float calory;
               calory=cursor.getFloat(0);
               sum=userDbHelper.getTotal(sqLiteDatabase);
               calorySum.setText(String.valueOf(sum));
                DataProvider dataProvider=new DataProvider(calory);
                listDataAdpter.add(dataProvider);

            }while (cursor.moveToNext());
        }
    }


}