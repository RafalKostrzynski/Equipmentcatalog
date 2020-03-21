package pl.kostrzynski.katalogsprzet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.repo.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentApiService {

private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentApiService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    private List<Equipment>getAvailableEquipment(){
       return equipmentRepository.findAllByAvailability(true);
    }

    public List<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }
    public List<Equipment> getAllAvailableEquipment(){
        return getAvailableEquipment();
    }

    public boolean addEquipment(Equipment equipment){
            equipmentRepository.save(equipment);
            return true;
    }

    public boolean changeAvailability(Long id,boolean availability){
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if(optionalEquipment.isPresent())
        {
            Equipment equipment=optionalEquipment.get();
            equipment.setAvailability(availability);
            equipmentRepository.save(equipment);
            return true;
        }
        return false;
    }

}
