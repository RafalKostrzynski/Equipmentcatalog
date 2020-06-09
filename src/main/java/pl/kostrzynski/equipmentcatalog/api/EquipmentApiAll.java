package pl.kostrzynski.equipmentcatalog.api;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kostrzynski.equipmentcatalog.model.Equipment;
import pl.kostrzynski.equipmentcatalog.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("justfit/equipment")
@Api(value = "AllEquipmentController", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentApiAll {

    private EquipmentService equipmentService;

    @Autowired
    public EquipmentApiAll(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/getAll")
    @ApiOperation("Gets a list with all Equipments in the database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = equipmentService.getAllEquipment();
        if (!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getEquipmentById")
    @ApiOperation("Gets an Equipment by its ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<Equipment> getEquipmentById(@RequestParam long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment != null) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ApiOperation("Post a new equipment")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED", response = Equipment.class), @ApiResponse(code = 406, message = "NOT_ACCEPTABLE")})
    public ResponseEntity<HttpStatus> postEquipment(@RequestBody Equipment newEquipment) {
        if (equipmentService.addEquipment(newEquipment)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/update")
    @ApiOperation("Update an existing equipment")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED", response = Equipment.class), @ApiResponse(code = 406, message = "NOT_ACCEPTABLE")})
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipment) {
        if (equipmentService.updateEquipment(equipment)) {
            return new ResponseEntity<>(equipmentService.getEquipmentById(equipment.getID()), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(equipmentService.getEquipmentById(equipment.getID()), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/getAllUnAvailable")
    @ApiOperation("Gets a list with all unavailable Equipments from the database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Equipment.class), @ApiResponse(code = 404, message = "NOT_FOUND")})
    public ResponseEntity<List<Equipment>> getAllUnAvailableEquipment() {
        List<Equipment> equipment = equipmentService.getAllAvailableEquipment(false);
        if (!equipment.isEmpty()) return new ResponseEntity<>(equipment, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/changeAvailability")
    @ApiOperation("Change Availability of an Equipment")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"), @ApiResponse(code = 406, message = "NOT_ACCEPTABLE")})
    public ResponseEntity<HttpStatus> changeAvailability(@RequestParam Long id, @RequestParam boolean available) {
        if (equipmentService.changeAvailability(id, available)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/changeAvailabilityList")
    @ApiOperation("Update availability of an equipmentList")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"), @ApiResponse(code = 406, message = "NOT_ACCEPTABLE")})
    public ResponseEntity<HttpStatus> changeAvailabilityList(@RequestBody List<Equipment>equipmentList,@RequestParam boolean availability){
        if (equipmentService.changeAvailability(equipmentList,availability)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/changeBrokenStatus")
    @ApiOperation("Change the Broken status of an Equipment")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"), @ApiResponse(code = 406, message = "NOT_ACCEPTABLE")})
    public ResponseEntity<HttpStatus> changeBrokenStatus(@RequestParam Long id, @RequestParam boolean broken) {
        if (equipmentService.changeBrokenStatus(id, broken)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}
