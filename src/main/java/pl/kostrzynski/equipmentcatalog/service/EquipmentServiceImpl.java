package pl.kostrzynski.equipmentcatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzynski.equipmentcatalog.model.Classification;
import pl.kostrzynski.equipmentcatalog.model.Equipment;
import pl.kostrzynski.equipmentcatalog.repo.EquipmentRepository;

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
    public List<Equipment> getAllAvailableEquipment(boolean availability) {
        return equipmentRepository.findAllByAvailability(availability);
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
        return equipmentRepository.findAllByEquipmentNameAndAvailability(name, true);
    }

    @Override
    public boolean addEquipment(Equipment equipment) {
        try {
            equipmentRepository.save(equipment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateEquipment(Equipment equipment) {
        if(equipment.isBroken())equipment.setAvailability(false);
        return equipmentRepository.findById(equipment.getID())
                .map(element -> {
                    element.setEquipmentName(equipment.getEquipmentName());
                    element.setBroken(equipment.isBroken());
                    element.setClassification(equipment.getClassification());
                    element.setSpecification(equipment.getSpecification());
                    equipmentRepository.save(element);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean changeAvailability(Long id, boolean availability) {
        return equipmentRepository.findById(id)
                .map(element -> {
                    element.setAvailability(availability);
                    equipmentRepository.save(element);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean changeAvailability(List<Equipment> equipmentList,boolean availability) {
        for(Equipment equipment:equipmentList){
            equipment.setAvailability(availability);
        }
        try {
            equipmentRepository.saveAll(equipmentList);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean changeBrokenStatus(Long id, boolean broken) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            if (broken) {
                equipment.setBroken(true);
                equipment.setAvailability(false);
            } else {
                equipment.setBroken(false);
                equipment.setAvailability(true);
            }
            equipmentRepository.save(equipment);
            return true;
        }
        return false;
    }

}
