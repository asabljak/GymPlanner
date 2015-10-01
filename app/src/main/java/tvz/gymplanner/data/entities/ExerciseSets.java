package tvz.gymplanner.data.entities;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class ExerciseSets extends EntityBase {
    private int trainingsExercisesId;
    private int numberOfRepeats;
    private int order;

    public int getTrainingsExercisesId() {
        return trainingsExercisesId;
    }

    public void setTrainingsExercisesId(int trainingsExercisesId) {
        this.trainingsExercisesId = trainingsExercisesId;
    }

    public int getNumberOfRepeats() {
        return numberOfRepeats;
    }

    public void setNumberOfRepeats(int numberOfRepeats) {
        this.numberOfRepeats = numberOfRepeats;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
