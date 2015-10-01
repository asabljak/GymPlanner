package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.entities.Training;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;

/**
 * Created by Antun on 5.6.2015..
 */
public class TrainingDetailsAdapter extends BaseAdapter {
    Context context;
    TrainingModel training;
    ArrayList<ExerciseModel> vjezbe;

    public TrainingDetailsAdapter(Context c, TrainingModel m){
        this.context=c;
        this.training=m;
        this.vjezbe=training.getExercises();

    }

    @Override
    public int getCount() {
        return vjezbe.size();
    }

    @Override
    public Object getItem(int position) {
        return vjezbe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vjezbe.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.opis_treninga_list_item, null);
        TextView naslov=(TextView)v.findViewById(R.id.opisTreninga_imeTreninga);
        TextView serije=(TextView)v.findViewById(R.id.opisTreninga_brojSerija);



        naslov.setText( vjezbe.get(position).getName());
        serije.setText("broj serija: " + String.valueOf(vjezbe.get(position).getSets().size()));


        return v;
    }
}
