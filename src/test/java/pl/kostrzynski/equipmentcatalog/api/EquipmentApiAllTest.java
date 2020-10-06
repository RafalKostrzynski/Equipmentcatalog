package pl.kostrzynski.equipmentcatalog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.kostrzynski.equipmentcatalog.model.Classification;
import pl.kostrzynski.equipmentcatalog.model.Equipment;
import pl.kostrzynski.equipmentcatalog.service.EquipmentService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EquipmentApiAllTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EquipmentService equipmentService;

    private List<Equipment> equipmentList;
    private Equipment equipment;


    @BeforeEach
    public void createNewList() {
        equipment = new Equipment();
        equipment.setID(5L);
        equipment.setEquipmentName("test");
        equipment.setAvailability(true);
        equipment.setClassification(Classification.KARDIO);
        equipment.setSpecification("basketball");
        equipment.setBroken(false);
        equipmentList = Collections.singletonList(equipment);
    }

    @Test
    void checkXMLMediaType() throws Exception {

        given(equipmentService.getAllEquipment()).willReturn(equipmentList);

        //check if XML not accepted

        mockMvc.perform(get("/justfit/equipment/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    void getAllEquipment() throws Exception {

        given(equipmentService.getAllEquipment()).willReturn(equipmentList);

        // Check if status is ok and size of List is 1

        mockMvc.perform(get("/justfit/equipment/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    void getEquipmentById() throws Exception {

        equipment.setID(17L);
        given(equipmentService.getEquipmentById(17L)).willReturn(equipment);
        given(equipmentService.getEquipmentById(1L)).willReturn(null);

        //normal usage

        mockMvc.perform(get("/justfit/equipment/getEquipmentById?id=17")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", hasToString("17")));

        //check if id isnt 1

        mockMvc.perform(get("/justfit/equipment/getEquipmentById?id=17")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", not(1)));

        //check if returns status NOT_FOUND

        mockMvc.perform(get("/justfit/equipment/getEquipmentById?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void postEquipment() throws Exception {

        given(equipmentService.addEquipment(equipment)).willReturn(true);
        given(equipmentService.addEquipment(new Equipment())).willReturn(false);

        //check the isNotAcceptable response

        mockMvc.perform(post("/justfit/equipment")
                .content(asJsonString(new Equipment()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());

        //check the isAccepted response

        mockMvc.perform(post("/justfit/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(equipment)))
                .andExpect(status().isAccepted());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}