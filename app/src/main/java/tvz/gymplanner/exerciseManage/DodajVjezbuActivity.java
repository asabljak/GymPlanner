package tvz.gymplanner.exerciseManage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.repositories.ExerciseRepository;

public class DodajVjezbuActivity extends ActionBarActivity {
    protected EditText naslov;
    protected EditText opis;
    protected ExerciseRepository erepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_vjezbu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_vjezbu, menu);
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

    public void dodajVjezbuMethod(View v){
        naslov = (EditText) findViewById(R.id.dodajVjezbu_naziv);
        opis = (EditText) findViewById(R.id.dodajVjezbu_opis);

        ExerciseModel vjezba = new ExerciseModel();
        vjezba.setName(naslov.getText().toString());
        vjezba.setDescription(opis.getText().toString());

        erepo = new ExerciseRepository(getApplicationContext());
        erepo.createNew(vjezba);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("message", "Vježba uspješno dodana!");
        editor.commit();

        onBackPressed();
    }
}
