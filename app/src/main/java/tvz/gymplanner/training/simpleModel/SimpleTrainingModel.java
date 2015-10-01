package tvz.gymplanner.training.simpleModel;

import tvz.gymplanner.data.models.ExerciseModel;
import tvz.gymplanner.list.adapters.TrainingListAdapter;

/**
 * Created by Antun on 2.6.2015..
 */
public class SimpleTrainingModel {
    int idSerije;
    ExerciseModel vjezba;
    int brojPonavljanja;
 //   ExerciseSetModel brojPonavljanja = new ExerciseSetModel();

    public SimpleTrainingModel(int idSerije, int brojPonavljanja) {
        this.idSerije = idSerije;
      //  this.brojPonavljanja.setNumberOfRepeats(brojPonavljanja);
        this.brojPonavljanja=brojPonavljanja;

        vjezba= TrainingListAdapter.getSelected().get(idSerije);
      //  vjezba.addSet(this.brojPonavljanja);
    }

    public int getIdSerije() {
        return idSerije;
    }

    public void setIdSerije(int idSerije) {
        this.idSerije = idSerije;
    }

    public int getBrojPonavljanja() {
        return brojPonavljanja;
    }

    public void setBrojPonavljanja(int brojPonavljanja) {
        this.brojPonavljanja = brojPonavljanja;
    }

    public ExerciseModel getVjezba() {
        return vjezba;
    }
}
