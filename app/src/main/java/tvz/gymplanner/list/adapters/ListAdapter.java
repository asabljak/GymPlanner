package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.ExerciseRepository;
import tvz.gymplanner.data.repositories.TrainingsRepository;

/**
 * Created by Antun on 23.5.2015..
 */
public class ListAdapter extends BaseAdapter {
    protected Context context;
    ExerciseRepository exerciseRepo;
    public static ArrayList<ExerciseModel> allExercises;
    protected static TextView text;
    protected static ArrayList<ExerciseModel> selectedExercises = new ArrayList<ExerciseModel>();
    protected int checked=0;
    protected  int imageId;
    protected ImageView image;

    public ListAdapter(Context con, int image){
        this.context=con;
        exerciseRepo = new ExerciseRepository(context);
        allExercises = exerciseRepo.getAll();
        this.imageId=image;
        selectedExercises.clear();
    }

    @Override
    public int getCount() {
        return allExercises.size();
    }

    @Override
    public Object getItem(int position) {
        return allExercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return allExercises.get(position).getId();
    }

    public static ArrayList<ExerciseModel> getSelectedExercises() {
        return selectedExercises;
    }

    public static String getText(int position){return allExercises.get(position).getName();}

    public int getChecked(){return checked;}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item, null);
        text=(TextView)v.findViewById(R.id.naziv_vjezbe);
        text.setText(allExercises.get(position).getName());
        image=(ImageView)v.findViewById(R.id.listItemIcon);
        image.setImageResource(imageId);

        final CheckBox cb = (CheckBox)v.findViewById(R.id.checkbox_vjezbe);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()) {
                    selectedExercises.add(allExercises.get(position));
                    checked++;
                }
                else {
                    selectedExercises.remove(allExercises.get(position));
                    checked--;
                }
            }
        });

        return v;
    }
}
