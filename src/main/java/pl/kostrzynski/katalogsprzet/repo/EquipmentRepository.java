package pl.kostrzynski.katalogsprzet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAvailability(Boolean availability);
    List<Equipment> findAllByClassificationAndAvailability(Classification classification,Boolean availability);
    List<Equipment> findAllBySpecificationAndAvailability(String specification, Boolean availability);
    List<Equipment> findAllByEquipmentNameAndAvailability(String name, Boolean availability);
}
