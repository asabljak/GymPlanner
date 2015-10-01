package tvz.gymplanner.list.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.entities.Training;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;
import tvz.gymplanner.training.OdabirTreningaActivity;
import tvz.gymplanner.training.TrainingAcitivity;
import tvz.gymplanner.training.TrainingDetailsActivity;

/**
 * Created by Antun on 5.6.2015..
 */
public class OdabirTreningaAdapter extends BaseAdapter{
    private Context context;
    private TextView text;
    private ImageView info;
    private ImageView delete;
    TrainingsRepository repo;
    ArrayList<TrainingModel> trainingModels;

    public OdabirTreningaAdapter(Context c){
        this.context=c;
        repo = new TrainingsRepository(context);
        trainingModels=repo.getAll();
    }

    @Override
    public int getCount() {
        return trainingModels.size();
    }

    @Override
    public Object getItem(int position) {
        return trainingModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return trainingModels.get(position).getId();
    }

    public String getText(int position){return trainingModels.get(position).getName();}

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.odabir_treninga_list_item, null);
        text=(TextView)v.findViewById(R.id.odabirTreninga_nazivVjezbe);
        info=(ImageView)v.findViewById(R.id.odabirTreninga_details);
        delete= (ImageView) v.findViewById(R.id.odabirTreninga_delete);



        text.setText(trainingModels.get(position).getName());
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingAcitivity.class);
                intent.putExtra("training", trainingModels.get(position).getId());
                context.startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).
                        setTitle("Brisanje treninga").
                        setMessage("Jeste li sigurni da želite obrisati " + trainingModels.get(position).getName() + "?").
                        setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TrainingsRepository repo = new TrainingsRepository(context);
                                repo.delete(trainingModels.get(position));

                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("message", "Trening uspješno obrisan!");
                                editor.commit();

                                Intent i = new Intent(context,OdabirTreningaActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                            }
                            }).
                            setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();

            }
        });


        return v;
    }
}
