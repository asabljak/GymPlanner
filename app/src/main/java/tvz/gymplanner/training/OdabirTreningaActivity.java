package tvz.gymplanner.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.list.adapters.OdabirTreningaAdapter;

public class OdabirTreningaActivity extends ActionBarActivity {
    OdabirTreningaAdapter adapter;
    ArrayList<TrainingModel> trainingModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_treninga);
        final ListView lv = (ListView)findViewById(R.id.odabirTreninga_ListView);
        adapter=new OdabirTreningaAdapter(this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TrainingDetailsActivity.class);
                String item = adapter.getText(position);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String message = prefs.getString("message", null);

        if (message!=null ){
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            prefs.edit().clear().commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odabir_treninga, menu);
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

    public void noviTrening(View v){
        startActivity(new Intent(getApplicationContext(),NapraviTreningActivity.class));
    }


}
