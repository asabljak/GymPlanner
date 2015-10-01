package tvz.gymplanner.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import tvz.gymplanner.R;
import tvz.gymplanner.list.adapters.GridAdapter;
import tvz.gymplanner.list.adapters.TrainingListAdapter;

public class OdaberiVjezbeActivity extends ActionBarActivity {
    TrainingListAdapter tladapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_vjezbe);
        ListView lv = (ListView)findViewById(R.id.trainingListView);
        tladapter=new TrainingListAdapter(this);
        lv.setAdapter(tladapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odaberi_vjezbe, menu);
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

    public void zapocniTrening(View v){
        startActivity(new Intent(getApplicationContext(),SetTrainingNameDescriptionActivity.class));
    }

    @Override
    public void onBackPressed() {
        GridAdapter.clearListaVjezbiSimple();
        super.onBackPressed();
    }

    public void klik(View v){
    }
}
