package tvz.gymplanner.training;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import tvz.gymplanner.R;
import tvz.gymplanner.list.adapters.ListAdapter;

public class NapraviTreningActivity extends ActionBarActivity {
    ListView popis;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napravi_trening);
        popis= (ListView) findViewById(R.id.list);
        listAdapter = new ListAdapter(this,R.drawable.info);
        popis.setAdapter(listAdapter);

        popis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                String item = listAdapter.getText(position);
                i.putExtra("item", item);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_napravi_trening, menu);
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

    public void sloziTreningBttn(View v){
        if(listAdapter.getChecked()>0)
            startActivity(new Intent(getApplicationContext(), OdaberiVjezbeActivity.class));
        else
            Toast.makeText(this,"Označite vježbe koje želite raditi!",Toast.LENGTH_LONG).show();
    }

}
