package tvz.gymplanner.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import tvz.gymplanner.R;
import tvz.gymplanner.data.entities.Exercise;
import tvz.gymplanner.data.repositories.ExerciseRepository;

public class DescriptionActivity extends ActionBarActivity {
    private TextView naslov;
    private TextView opis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent=getIntent();
        naslov=(TextView)findViewById(R.id.textViewName);
        opis=(TextView)findViewById(R.id.textViewDescription);

        ExerciseRepository er = new ExerciseRepository(getApplication());
        String e=intent.getStringExtra("item");
        Exercise vjezba=er.getByName(e);
        naslov.setText(vjezba.getName());
        opis.setText(vjezba.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
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
