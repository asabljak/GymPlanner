package tvz.gymplanner.data.entities;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class ExercisesHistory extends EntityBase {
    private int trainingHistoryId;
    private int exerciseId;

    public int getTrainingHistoryId() {
        return trainingHistoryId;
    }

    public void setTrainingHistoryId(int trainingHistoryId) {
        this.trainingHistoryId = trainingHistoryId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
