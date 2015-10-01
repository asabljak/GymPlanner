package tvz.gymplanner.data.entities;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class SetsHistory extends EntityBase {
    private int exerciseHistoryId;
    private int numberOfRepeats;
    private int order;

    public int getExerciseHistoryId() {
        return exerciseHistoryId;
    }

    public void setExerciseHistoryId(int exerciseHistoryId) {
        this.exerciseHistoryId = exerciseHistoryId;
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
