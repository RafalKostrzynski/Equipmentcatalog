package pl.kostrzynski.katalogsprzet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.repo.Repo;

import java.util.List;

@Service
public class EquipmentApiImpl {

private Repo repo;

    @Autowired
    public EquipmentApiImpl(Repo repo) {
        this.repo = repo;
    }

    private List<Equipment>getAvailableEquipment(){
       return repo.findAllByAvailability(true);
    }

    public List<Equipment> getAllEquipment(){
        return repo.findAll();
    }
    public List<Equipment> getAllAvailableEquipment(){
        return getAvailableEquipment();
    }

    public boolean addEquipment(Equipment equipment){
            repo.save(equipment);
            return true;
    }

    public boolean changeAvailability(Long id,boolean availability){
        if(repo.findById(id).isPresent())
        {
            Equipment equipment=repo.findById(id).get();
            equipment.setAvailability(availability);
            repo.save(equipment);
            return true;
        }
        return false;
    }

}
