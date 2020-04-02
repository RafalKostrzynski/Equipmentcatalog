package pl.kostrzynski.katalogsprzet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentApi {

    private EquipmentService equipmentService;

    @Autowired
    public EquipmentApi(EquipmentService equipmentApi) {
        this.equipmentService = equipmentApi;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment(){
        return new ResponseEntity<>(equipmentService.getAllEquipment(), HttpStatus.OK);
    }
    @GetMapping("/getAllAvailable")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment(){
        return new ResponseEntity<>(equipmentService.getAllAvailableEquipment(), HttpStatus.OK);
    }
    @GetMapping("/getEquipmentById")
    public ResponseEntity<Equipment> getEquipmentById(@RequestParam long id){
        return new ResponseEntity<>(equipmentService.getEquipmentById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllAvailableEquipmentByName")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentByName(@RequestParam String equipmentName){
        return new ResponseEntity<>(equipmentService.getEquipmentByName(equipmentName), HttpStatus.OK);
    }
    @GetMapping("/getAllAvailableEquipmentBySpecification")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentBySpecification(@RequestParam String specification){
        return new ResponseEntity<>(equipmentService.getAllAvailableEquipmentBySpecification(specification), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> postEquipment(@RequestBody Equipment newEquipment){
        if(equipmentService.addEquipment(newEquipment)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/changeAvailability")
    public ResponseEntity<HttpStatus> changeAvailability(@RequestParam Long id, @RequestParam boolean available){
        if(equipmentService.changeAvailability(id, available)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}
