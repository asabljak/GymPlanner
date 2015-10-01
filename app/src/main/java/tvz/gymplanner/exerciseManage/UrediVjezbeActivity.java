package tvz.gymplanner.exerciseManage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.repositories.ExerciseRepository;
import tvz.gymplanner.list.adapters.UrediVjezbeAdapter;

public class UrediVjezbeActivity extends ActionBarActivity {
    ListView dodajBrisiVjezbu;
    private UrediVjezbeAdapter adapter;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uredi_vjezbe);

        adapter=new UrediVjezbeAdapter(getApplicationContext());
        dodajBrisiVjezbu=(ListView)findViewById(R.id.listViewDodajBrisi);
        dodajBrisiVjezbu.setAdapter(adapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        message = prefs.getString("message", null);

        if (message!=null ){
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            prefs.edit().clear().commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uredi_vjezbe, menu);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public void dodajVjezbu(View v){
        startActivity(new Intent(getApplicationContext(), DodajVjezbuActivity.class));
    }

    public void obrisiVjezbe(View v){
        if (adapter.getChecked()>0) {
            new AlertDialog.Builder(this)
                    .setTitle("Brisanje vježbi")
                    .setMessage("Jeste li sigurni da želite obrisati "+adapter.getChecked()+" označenih vježbe?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ExerciseRepository erepo = new ExerciseRepository(getApplicationContext());
                            for (ExerciseModel m : adapter.getSelectedExercises()) {
                                erepo.delete(m);
                                Toast.makeText(getApplicationContext(),"Vježbe uspješno izbrisane",Toast.LENGTH_LONG).show();
                            }

                            finish();
                            startActivity(getIntent());
                        }
                    })
                    .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
        }
        else{
            Toast.makeText(this,"Označite vježbe koje želite obrisati",Toast.LENGTH_LONG).show();
        }
    }
}
