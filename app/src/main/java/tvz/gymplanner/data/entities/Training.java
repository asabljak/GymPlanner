package tvz.gymplanner.data.entities;

import java.util.Date;

/**
 * Created by Mateo Velenik on 12.4.2015..
 */
public class Training extends EntityBase {
    private String name;
    private String description;
    private Date dateCreated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
