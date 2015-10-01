package tvz.gymplanner.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;
import tvz.gymplanner.list.adapters.GridAdapter;
import tvz.gymplanner.mainPkg.MainActivity;
import tvz.gymplanner.training.simpleModel.SimpleTrainingModel;

public class SetTrainingNameDescriptionActivity extends ActionBarActivity {
    EditText naziv;
    EditText opis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_training_name_description);

        naziv= (EditText) findViewById(R.id.trainingFin_nazivInput);
        opis= (EditText) findViewById(R.id.trainingFin_opisInput);
        Button bttn= (Button) findViewById(R.id.trainingFin_bttn);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kreirajTrening(v, naziv.getText().toString(), opis.getText().toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_training_name_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void kreirajTrening(View v, String ime, String opis){
        TrainingsRepository repo = new TrainingsRepository(getApplicationContext());

        TrainingModel trainingModel = new TrainingModel();
        trainingModel.setName(ime);
        trainingModel.setDescription(opis);
        trainingModel.setDateCreated(new Date(2015, 5, 10));
        trainingModel = repo.createNew(trainingModel);

        ArrayList<TrainingExerciseModel> trainingExerciseModels = new ArrayList<TrainingExerciseModel>();
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (SimpleTrainingModel m : GridAdapter.getListaVjezbiSimple()){
            if(!ids.contains(m.getIdSerije()))
                ids.add(m.getIdSerije());
        }

        for (int i =0;i<ids.size();i++) {
            boolean vecPostoji = false;
            for (SimpleTrainingModel m : GridAdapter.getListaVjezbiSimple()) {
                if (m.getIdSerije() == ids.get(i) && !vecPostoji) {
                    TrainingExerciseModel tmp = repo.addExerciseTo(trainingModel, m.getVjezba());
                    trainingExerciseModels.add(tmp);
                    vecPostoji = true;
                }
            }
        }

        for (TrainingExerciseModel tem : trainingExerciseModels){
            for (SimpleTrainingModel m : GridAdapter.getListaVjezbiSimple()) {
                if (m.getIdSerije()==trainingExerciseModels.indexOf(tem)){
                    repo.addSetTo(tem,m.getBrojPonavljanja());
                }
            }
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SetTrainingNameDescriptionActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("message", "Trening uspješno kreiran!");
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
