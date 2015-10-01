package tvz.gymplanner.data.models;

import java.util.ArrayList;

import tvz.gymplanner.data.entities.Training;

/**
 * Created by Mateo Velenik on 6.5.2015..
 */
public class TrainingModel extends Training {

    private ArrayList<ExerciseModel> exercises;

    public TrainingModel(){
        this.exercises = new ArrayList<ExerciseModel>();
    }

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public void addExercise(ExerciseModel exercise) {
        this.exercises.add(exercise);
    }
}
