package tvz.gymplanner.data.models;

import java.util.ArrayList;

import tvz.gymplanner.data.entities.Exercise;

/**
 * Created by Mateo Velenik on 6.5.2015..
 */
public class ExerciseModel extends Exercise {

    public ArrayList<ExerciseSetModel> sets;

    public ExerciseModel(){
        this.sets = new ArrayList<ExerciseSetModel>();
    }

    public ArrayList<ExerciseSetModel> getSets() {
        return sets;
    }

    public void addSet(ExerciseSetModel set) {
        this.sets.add(set);
    }

    public ExerciseModel(String name, String description, String image) { this(); this.setName(name); this.setDescription(description); this.setImage(image); }
}
