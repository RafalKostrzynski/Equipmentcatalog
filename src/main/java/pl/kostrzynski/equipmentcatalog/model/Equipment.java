package pl.kostrzynski.equipmentcatalog.model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    private String equipmentName;
    private Classification classification;
    private String specification;
    @NotNull
    private boolean broken;
    @NotNull
    private boolean availability;

    public Equipment() {
    }

    public Equipment(String equipmentName, Classification classification, String specification, boolean broken, boolean availability) {
        this.equipmentName = equipmentName;
        this.classification = classification;
        this.specification = specification;
        this.broken = broken;
        this.availability = availability;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
