package pl.kostrzynski.katalogsprzet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;
import pl.kostrzynski.katalogsprzet.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentApi {

    //TODO rozdzielenie klasy API na zwracającą dostepne i wszystkie
    //TODO napisać endpoint który zwraca informacje o wszystkich Mappingach

    private EquipmentService equipmentService;

    @Autowired
    public EquipmentApi(EquipmentService equipmentApi) {
        this.equipmentService = equipmentApi;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment(){
        List<Equipment>equipment=equipmentService.getAllEquipment();
        if(!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailable")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment(){
        List<Equipment>equipment=equipmentService.getAllAvailableEquipment();
        if(!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getEquipmentById")
    public ResponseEntity<Equipment> getEquipmentById(@RequestParam long id){
        Equipment equipment=(equipmentService.getEquipmentById(id));
        if (equipment!=null) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailableEquipmentByName")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentByName(@RequestParam String equipmentName){
        List<Equipment>equipment=equipmentService.getAvailableEquipmentByName(equipmentName);
        if(!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailableEquipmentBySpecification")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentBySpecification(@RequestParam String specification){
        List<Equipment>equipment=equipmentService.getAllAvailableEquipmentBySpecification(specification);
        if(!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

    @GetMapping("/getAllAvailableEquipmentByClassification")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentByClassification(@RequestParam String stClassification){
        Classification classification;
        switch (stClassification.toLowerCase()){
            case "pilki":
                classification=Classification.PILKI;
                break;
            case "treningsilowy":
                classification=Classification.TRENINGSILOWY;
                break;
            case "lawkidocwiczen":
                classification=Classification.LAWKIDOCWICZEN;
                break;
            case "kardio":
                classification=Classification.KARDIO;
                break;
            case "maty":
                classification=Classification.MATY;
                break;
            default:
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentService.getAllAvailableEquipmentByClassification(classification),HttpStatus.OK);
    }

}
