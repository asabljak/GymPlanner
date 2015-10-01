package tvz.gymplanner.data.entities;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class TrainingsExercises extends EntityBase {
    public int trainingId;
    public int exerciseId;

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
