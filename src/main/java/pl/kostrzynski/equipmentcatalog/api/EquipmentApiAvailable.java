package pl.kostrzynski.equipmentcatalog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.equipmentcatalog.model.Classification;
import pl.kostrzynski.equipmentcatalog.model.Equipment;
import pl.kostrzynski.equipmentcatalog.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("justfit/equipment")
@Api(value = "AvailableEquipmentController", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentApiAvailable {

    private EquipmentService equipmentService;

    @Autowired
    public EquipmentApiAvailable(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/getAllAvailable")
    @ApiOperation("Gets a list with all available Equipments from the database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment() {
        List<Equipment> equipment = equipmentService.getAllAvailableEquipment(true);
        if (!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailableEquipmentByName")
    @ApiOperation("Gets a list with all available Equipments by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentByName(@RequestParam String equipmentName) {
        List<Equipment> equipment = equipmentService.getAvailableEquipmentByName(equipmentName);
        if (!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailableEquipmentBySpecification")
    @ApiOperation("Gets a list with all available Equipments by specification")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentBySpecification(@RequestParam String specification) {
        List<Equipment> equipment = equipmentService.getAllAvailableEquipmentBySpecification(specification);
        if (!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllAvailableEquipmentByClassification")
    @ApiOperation("Gets a list with all available Equipments by classification")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllAvailableEquipmentByClassification(@RequestParam String stClassification) {
        Classification classification;
        switch (stClassification.toLowerCase()) {
            case "pilki":
                classification = Classification.PILKI;
                break;
            case "treningsilowy":
                classification = Classification.TRENINGSILOWY;
                break;
            case "lawkidocwiczen":
                classification = Classification.LAWKIDOCWICZEN;
                break;
            case "kardio":
                classification = Classification.KARDIO;
                break;
            case "maty":
                classification = Classification.MATY;
                break;
            default:
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentService.getAllAvailableEquipmentByClassification(classification), HttpStatus.OK);
    }
}
