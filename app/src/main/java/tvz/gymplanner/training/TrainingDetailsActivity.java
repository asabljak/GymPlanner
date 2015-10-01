package tvz.gymplanner.training;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.Training;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;
import tvz.gymplanner.list.adapters.OdabirTreningaAdapter;
import tvz.gymplanner.list.adapters.TrainingDetailsAdapter;

public class TrainingDetailsActivity extends ActionBarActivity {
    TrainingDetailsAdapter adapter;
    String trainingName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        Intent i = getIntent();
        trainingName=i.getStringExtra("item");
        TrainingsRepository repo = new TrainingsRepository(this);
        TrainingModel training=repo.getByName(trainingName);

        ListView lv = (ListView)findViewById(R.id.trainingDetails_ListView);
        TextView naslov = (TextView) findViewById(R.id.trainingDetails_naslov);
        TextView opis = (TextView) findViewById(R.id.trainingDetails_opis);

        adapter=new TrainingDetailsAdapter(this,training);
        lv.setAdapter(adapter);

        naslov.setText( training.getName());
        opis.setText(training.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training_details, menu);
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
}
