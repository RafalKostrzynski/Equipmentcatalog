package pl.kostrzynski.katalogsprzet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.service.EquipmentApiService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentApi {

    private EquipmentApiService equipmentApiService;

    @Autowired
    public EquipmentApi(EquipmentApiService equipmentApi) {
        this.equipmentApiService = equipmentApi;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment(){
        return new ResponseEntity<>(equipmentApiService.getAllEquipment(), HttpStatus.OK);
    }
    @GetMapping("/getAllAvailable")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment(){
        return new ResponseEntity<>(equipmentApiService.getAllAvailableEquipment(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> postEquipment(@RequestBody Equipment newEquipment){
        if(equipmentApiService.addEquipment(newEquipment)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/changeAvailability")
    public ResponseEntity<HttpStatus> changeAvailability(@RequestParam Long id, @RequestParam boolean available){
        if(equipmentApiService.changeAvailability(id, available)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}
