package com.example.eodwan.ourproject;


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

public class ListDataAdpter extends ArrayAdapter {
    List list= new ArrayList();
    public ListDataAdpter(Context context,int resource){
        super(context,resource);
    }
    static class LayoutHandler{
        TextView calory;
    }
    public void add(Object object){
        super.add(object);
        list.add(object);

    }
    public int getCount(){
        return list.size();
    }
    public Object getTtem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.calory=(TextView)row.findViewById(R.id.calory_number);
            row.setTag(layoutHandler);
        }

        else{
            layoutHandler=(LayoutHandler)row.getTag();
        }
        DataProvider dataProvider=(DataProvider)this.getTtem(position);
        layoutHandler.calory.setText(String.valueOf(dataProvider.getNumber()));
        return row;
    }
}
