package pl.kostrzynski.katalogsprzet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.service.EquipmentApiImpl;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentApi {

    private EquipmentApiImpl equipmentApiImpl;

    @Autowired
    public EquipmentApi(EquipmentApiImpl equipmentApi) {
        this.equipmentApiImpl = equipmentApi;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment(){
        return new ResponseEntity<>(equipmentApiImpl.getAllEquipment(), HttpStatus.OK);
    }
    @GetMapping("/getAllAvailable")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment(){
        return new ResponseEntity<>(equipmentApiImpl.getAllAvailableEquipment(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> postEquipment(@RequestBody Equipment newEquipment){
        if(equipmentApiImpl.addEquipment(newEquipment)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/changeAvailability")
    public ResponseEntity<HttpStatus> changeAvailability(@RequestParam Long id, @RequestParam boolean available){
        if(equipmentApiImpl.changeAvailability(id, available)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}
