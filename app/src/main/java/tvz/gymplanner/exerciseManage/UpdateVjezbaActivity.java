package tvz.gymplanner.exerciseManage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.repositories.ExerciseRepository;
import tvz.gymplanner.list.adapters.UrediVjezbeAdapter;

/**
 * Created by Antun on 30.5.2015..
 */
public class UpdateVjezbaActivity extends ActionBarActivity {
    protected EditText naslov;
    protected EditText opis;
    protected ExerciseRepository erepo;
    private Button updateBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_vjezbu);
        naslov= (EditText) findViewById(R.id.dodajVjezbu_naziv);
        opis = (EditText) findViewById(R.id.dodajVjezbu_opis);
        updateBttn= (Button) findViewById(R.id.dodajVjezbuBttn_dodajVjezbu);
        updateBttn.setText("Izmijeni");

        Intent intent=getIntent();
        erepo = new ExerciseRepository(getApplication());
        final Exercise vjezba=erepo.getByName(intent.getStringExtra("item"));
        naslov.setText(vjezba.getName());
        opis.setText(vjezba.getDescription());

        updateBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ExerciseModel afterUpdate=new ExerciseModel();
                afterUpdate.setName(naslov.getText().toString());
                afterUpdate.setDescription(opis.getText().toString());
                afterUpdate.setId(vjezba.getId());

                erepo.updateExisting(afterUpdate);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UpdateVjezbaActivity.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("message", "Izmjena uspješno izvršena!");
                editor.commit();

                onBackPressed();
            }
        });
    }
}
