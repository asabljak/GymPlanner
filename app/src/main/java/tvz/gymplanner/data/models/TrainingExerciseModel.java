package tvz.gymplanner.data.models;

import java.util.ArrayList;

import tvz.gymplanner.data.entities.TrainingsExercises;

/**
 * Created by Mateo Velenik on 6.5.2015..
 */
public class TrainingExerciseModel extends TrainingsExercises {

    private ArrayList<ExerciseSetModel> sets;

    public TrainingExerciseModel(){
        sets = new ArrayList<ExerciseSetModel>();
    }

    public ArrayList<ExerciseSetModel> getSets() {
        return sets;
    }

    public void addSet(ExerciseSetModel set) {
        this.sets.add(set);
    }
}
