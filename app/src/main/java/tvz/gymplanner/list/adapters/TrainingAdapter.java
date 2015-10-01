package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.ExerciseSets;
import tvz.gymplanner.data.entities.Training;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.ExerciseSetModel;
import tvz.gymplanner.data.models.TrainingModel;

/**
 * Created by Filip on 8.6.2015..
 */
public class TrainingAdapter extends BaseAdapter {
    TrainingModel training;
    int rbr;
    int rbrPon;
    protected Context context;

    public TrainingAdapter(TrainingModel training, int rbr, Context context){
        this.training=training;
        this.rbr=rbr;
        this.rbrPon=0;
        this.context=context;
    }

    @Override
    public int getCount() {
        // �TA BI OVAJ TREBAO VRA�AT
        // return training.getExercises().get(rbr).getSets().size();
        return training.getExercises().get(rbr).getSets().size();
    }

    @Override
    public Object getItem(int i) {
        return training.getExercises().get(rbr).getSets().get(i);
    }

    @Override
    public long getItemId(int i) {
        return training.getExercises().get(rbr).getSets().get(rbrPon).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item_training_in_progress, null);
        TextView naziv=(TextView) v.findViewById(R.id.ime_vjezbe);
        TextView ponavljanje=(TextView) v.findViewById(R.id.broj_ponavljanja_u_seriji);

        naziv.setText(training.getExercises().get(rbr).getName());

        String vjezbe="";

        for(ExerciseSetModel set : training.getExercises().get(rbr).getSets()){
            vjezbe+=set.getNumberOfRepeats()+", ";
        }

        //ponavljanje.setText(vjezbe);

        if(rbrPon == training.getExercises().get(rbr).getSets().size()){
            rbrPon=0;
        }

        ponavljanje.setText(training.getExercises().get(rbr).getSets().get(rbrPon).getNumberOfRepeats()+"");
        rbrPon++;

        return v;
    }
}
