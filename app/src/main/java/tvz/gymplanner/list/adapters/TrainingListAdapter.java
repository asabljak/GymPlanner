package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;
import tvz.gymplanner.list.adapters.GridAdapter;
import tvz.gymplanner.list.adapters.ListAdapter;

/**
 * Created by Antun on 25.5.2015..
 */
public class TrainingListAdapter extends BaseAdapter {
    private Context context;
    TrainingModel trainingModel;
    TrainingExerciseModel[] trainingExerciseModels;
    static ArrayList<ExerciseModel> selected;
    GridAdapter gridAdapter;
    int content;

    public TrainingListAdapter(Context con){
        this.context=con;
        selected = ListAdapter.getSelectedExercises();
    }
    @Override
    public int getCount() {
        return selected.size();
    }

    @Override
    public Object getItem(int position) {
        return selected.get(position);
    }

    @Override
    public long getItemId(int position) {
        return selected.get(position).getId();
    }

    public static ArrayList<ExerciseModel> getSelected() {
        return selected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        
        final View v = inflater.inflate(R.layout.training_list_item, null);
        ((TextView) v.findViewById(R.id.training_list_item_text)).setText(selected.get(position).getName());

        final NumberPicker serije=(NumberPicker)v.findViewById(R.id.numberPickerSerije);
        serije.setMinValue(0);
        serije.setMaxValue(100);
        serije.setWrapSelectorWheel(false);
        serije.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                gridAdapter = new GridAdapter(context,newVal,position);
                GridView gv= (GridView) v.findViewById(R.id.gridView);
                for (int i = 0; i < newVal; i++) {
                    gv.setAdapter(gridAdapter);
                }
            }
        });

        return v;
    }

}
