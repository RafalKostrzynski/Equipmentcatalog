package pl.kostrzynski.katalogsprzet.service;

import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;

import java.util.List;

public interface EquipmentService {

    List<Equipment> getAllEquipment();
    List<Equipment> getAllAvailableEquipment();
    List<Equipment> getAllAvailableEquipmentByClassification(Classification classification);
    List<Equipment> getAllAvailableEquipmentBySpecification(String specification);
    List<Equipment> getEquipmentByName(String Name);
    Equipment getEquipmentById(long id);
    boolean addEquipment(Equipment equipment);
    boolean changeAvailability(Long id,boolean availability);

}
