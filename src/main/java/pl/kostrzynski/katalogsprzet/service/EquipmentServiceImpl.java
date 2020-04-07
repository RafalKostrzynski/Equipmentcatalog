package pl.kostrzynski.katalogsprzet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.repo.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> getAllAvailableEquipment() {
        return equipmentRepository.findAllByAvailability(true);
    }

    @Override
    public List<Equipment> getAllAvailableEquipmentByClassification(Classification classification) {
        return equipmentRepository.findAllByClassificationAndAvailability(classification, true);
    }

    @Override
    public List<Equipment> getAllAvailableEquipmentBySpecification(String specification) {
        return equipmentRepository.findAllBySpecificationAndAvailability(specification, true);
    }

    @Override
    public Equipment getEquipmentById(long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Equipment> getAvailableEquipmentByName(String name) {
        return equipmentRepository.findAllByEquipmentNameAndAvailability(name,true);
    }

    @Override
    public boolean addEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
        return true;
    }

    @Override
    public boolean changeAvailability(Long id, boolean availability) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            equipment.setAvailability(availability);
            equipmentRepository.save(equipment);
            return true;
        }
        return false;
    }

}
