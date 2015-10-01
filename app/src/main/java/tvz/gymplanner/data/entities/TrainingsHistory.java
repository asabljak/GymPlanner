package tvz.gymplanner.data.entities;

import java.util.Date;

/**
 * Created by Mateo Velenik on 5.5.2015..
 */
public class TrainingsHistory extends EntityBase {
    private int trainingId;
    private Date dateTrained;

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public Date getDateTrained() {
        return dateTrained;
    }

    public void setDateTrained(Date dateTrained) {
        this.dateTrained = dateTrained;
    }
}
