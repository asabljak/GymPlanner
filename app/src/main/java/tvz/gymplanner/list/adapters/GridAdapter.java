package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

import tvz.gymplanner.training.simpleModel.SimpleTrainingModel;

/**
 * Created by Antun on 26.5.2015..
 */
public class GridAdapter extends BaseAdapter {
    Context context;
    int count;
    private int vjezbaId;
    private static ArrayList<SimpleTrainingModel> listaVjezbiSimple = new ArrayList<SimpleTrainingModel>();

    public GridAdapter(Context con, int n, int position){
        this.count=n;
        this.context=con;
        this.vjezbaId=position;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static ArrayList<SimpleTrainingModel> getListaVjezbiSimple() {
        return listaVjezbiSimple;
    }

    public static void clearListaVjezbiSimple() {
        listaVjezbiSimple.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EditText et;
        if(convertView!=null)
            et=(EditText)convertView;
        else{
            et=new EditText(context);
            et.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            et.setHint(String.valueOf(position + 1));
            et.setId(position);
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et.setText("");
                }
            });

            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(isNumeric(et.getText().toString())){
                        if (Integer.parseInt(s.toString())>=10)
                            listaVjezbiSimple.remove(listaVjezbiSimple.size()-1);


                        listaVjezbiSimple.add(new SimpleTrainingModel(vjezbaId, Integer.parseInt(et.getText().toString())));
                    }
                }
            });
        }
        return et;
    }

    private static boolean isNumeric(String num){
        try {
            int n = Integer.parseInt(num);
        } catch(NumberFormatException nfe) {
       //     Log.v("Eksepšn", String.valueOf(nfe));
            return false;
        }

        return true;
    }

}
