package tvz.gymplanner.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.TrainingsRepository;
import tvz.gymplanner.list.adapters.TrainingAdapter;

public class TrainingAcitivity extends ActionBarActivity {

    private Context context;
    private  TextView naziv_treninga;
    private TextView trenutna_serija;
    private ListView popis_vjezbi_i_ponavaljanja;
    private Button nova_serija;

    private TrainingAdapter adapter;
    private TrainingModel training;
    int rbr=0;
    int max;
    int serija=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_training_acitivity);
        naziv_treninga = (TextView) findViewById(R.id.naziv_treninga);
        trenutna_serija = (TextView) findViewById(R.id.trenutna_serija);
        popis_vjezbi_i_ponavaljanja = (ListView) findViewById(R.id.lista_vjezbi_i_ponavljanja);
        nova_serija = (Button) findViewById(R.id.butt_nova_serija);

        rbr=0;
        Intent i = getIntent();
        TrainingsRepository repo = new TrainingsRepository(this);
        training=repo.getById(i.getIntExtra("training", 0));
        max=training.getExercises().size();
        adapter=new TrainingAdapter(training,rbr,this);
        popis_vjezbi_i_ponavaljanja.setAdapter(adapter);





        trenutna_serija.setText(serija + "");
        naziv_treninga.setText(training.getName());
    }

   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String value=trenutna_serija.getText().toString();
        value.concat(value);
        outState.putString("broj",value);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String rbr=savedInstanceState.getString("broj");
        trenutna_serija.setText(rbr+"");
        naziv_treninga.setText(training.getName());
    }*/

    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training_acitivity, menu);
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

    public void novaSerija(View view) {

        rbr++;
        serija++;
        if(serija<=max){
        trenutna_serija.setText(serija+"");

        adapter=new TrainingAdapter(training,rbr,this);
        popis_vjezbi_i_ponavaljanja.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        }

        else{
           startActivity(new Intent(getApplicationContext(),TrainingOverActivity.class));
            finish();
        }

    }
}
