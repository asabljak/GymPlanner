package tvz.gymplanner.mainPkg;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tvz.gymplanner.R;
import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.data.models.TrainingExerciseModel;
import tvz.gymplanner.data.models.TrainingModel;
import tvz.gymplanner.data.repositories.ExerciseRepository;
import tvz.gymplanner.data.repositories.TrainingsRepository;
//import tvz.gymplanner.data.seed.SeedData;
import tvz.gymplanner.data.seed.SeedData;
import tvz.gymplanner.exerciseManage.UrediVjezbeActivity;
import tvz.gymplanner.pedometer.PedometarActivity;
import tvz.gymplanner.training.OdabirTreningaActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String message = prefs.getString("message", null);

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            prefs.edit().clear().commit();
        }

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setMessageText();
                    }
                });

            }
        }, 0, 5000);

        ExerciseRepository repo = new ExerciseRepository(getApplicationContext());
        if(repo.getAll().size() == 0){
            SeedData.Seed(getApplicationContext());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void repositoriesDemo(View _) {

        // evo kratki tutorial kako se koriste repozitoriji koje sam napisao

        // kada se želi komunicirati s bazom podataka kreira se novi repozitorij i kroz njega
        // se vrši spremanje promjena i dohvat podataka
        // ExerciseRepository radi sa vježbama i s njim se može dohvatiti npr sve vježbe i pokazati ih korisniku
        // pa on može kreirati nove ili brisati stare
        // isto tako možemo ih sve dohvatiti i prikazati u nekom spinneru
        ExerciseRepository exerciseRepo = new ExerciseRepository(getApplicationContext());
        ArrayList<ExerciseModel> allExercises = exerciseRepo.getAll();

        // repozitoriji uvijek vraćaju objekte koji su kao modeli. te klase su definirane u data.models paketu i
        // njih se direktno koristi za prikaz korisniku
        // klase koje se nalaze u data.entities su podaci koji su spremljeni direktno u bazi i njih se ne
        // koristi direktno - njih interno koristim u repozitorijima

        TrainingsRepository repo = new TrainingsRepository(getApplicationContext());

        // ovo je primjer kreiranja treninga
        // prvo se kreira novi model i njemu se postavljaju vrijednosti kakve je korisnik unio
        TrainingModel trainingModel = new TrainingModel();
        trainingModel.setName("Neki trening");
        trainingModel.setDescription("Opis kreiranog treninga");
        trainingModel.setDateCreated(new Date(2015, 5, 10));

        // kada je kreiran trening, sa repozitorijem se pozove createNew i trening je ubačen u bazu. createNew vraća
        // taj isti model samo što sada ima na sebi postavljen i Id iz baze
        trainingModel = repo.createNew(trainingModel);

        // kada korisnik dodaje na trening vježbe, poziva se metoda na repozitoriju addExerciseTo
        // ona prima TrainingModel (kreiran prije) i ExerciseModel (koji smo prije ubacili u neki spinner npr.)
        // ovdje je primjer kako je dodano tri vježbe na trening
        // funkcija vraća TrainingExerciseModel, a on nam treba kako bi toj vježbi na treningu dodali ponavljanja
        TrainingExerciseModel exercise1 = repo.addExerciseTo(trainingModel, allExercises.get(0));
        TrainingExerciseModel exercise2 = repo.addExerciseTo(trainingModel, allExercises.get(1));
        TrainingExerciseModel exercise3 = repo.addExerciseTo(trainingModel, allExercises.get(2));

        // sada koristimo repozitorij kako bi dodanoj vježbi dodali seriju - dajemo prije dobiveni TrainingExerciseModel i broj ponavljanja za seriju
        repo.addSetTo(exercise1, 12);
        repo.addSetTo(exercise1, 10);
        repo.addSetTo(exercise1, 8);
        repo.addSetTo(exercise1, 6);
        repo.addSetTo(exercise1, 100);
        // kako je zadnja serija malo overkill, korisnik klikne neki minus button i pozove se deleteLastSetFrom metoda
        // koja prima TrainingExerciseModel i njemu briše zadnje ponavljanje
        // sve što se radi s repozitorijem je odmah pohranjeno u bazi, uopće se za to ne treba brinuti
        repo.deleteLastSetFrom(exercise1);

        repo.addSetTo(exercise2, 15);
        repo.addSetTo(exercise2, 10);

        repo.addSetTo(exercise3, 20);
        repo.addSetTo(exercise3, 20);
        repo.addSetTo(exercise3, 15);
        repo.addSetTo(exercise3, 10);

        // i to je to! trening je kreiran i sada kada na repozitoriju pozovemo getAll dobijemo
        // listu svih treninga, i svaki na sebi ima sve svoje vježbe i svaka vježba sve svoje serije
        ArrayList<TrainingModel> allTrainings = repo.getAll();

        // slično se koristi i ExerciseRepository samo je on dosta jednostavniji
        // kasnije ću napisati i repozitorij za unošenje povijesti treninga - kad korisnik počne trenirati, kada odradi seriju,
        // sve će se te promjene unositi kroz neki TrainingHistoryRepository
        // eto, nadam se da vam se ovakav pristup bazi sviđa

        // isto tako, ne zaboravite ponekad obrisati bazu (Options/Apps/GymPlanner/Delete data ili kako već)
    }

    public void izradaTreninga(View view) {
        startActivity(new Intent(getApplicationContext(), OdabirTreningaActivity.class));
    }

    public void prikaziStatistiku(View view) {
        Toast.makeText(this, "404 - Statistike su u pripremi.", Toast.LENGTH_LONG).show();
    }

    public void pokreniPedometar(View view) {
        startActivity(new Intent(getApplicationContext(), PedometarActivity.class));
    }

    public void urediVjezbe(View view) {
        startActivity(new Intent(getApplicationContext(), UrediVjezbeActivity.class));
    }

    public void pokaziPovijest(View view) {
        Toast.makeText(this, "404 - Povijrst treninga je u pripremi.", Toast.LENGTH_LONG).show();
    }

    private void setMessageText() {

        TextView msg = (TextView) findViewById(R.id.lblMessageLabel);

        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Bit ces najjaci brate");
        messages.add("Moras svaki dan dizat");
        messages.add("Kupi proteine");
        messages.add("Samo jako!!!");
        messages.add("Masa je mama!!!");

        Random randomiser = new Random();

        msg.setText(messages.get(randomiser.nextInt(messages.size())));

    }
}
