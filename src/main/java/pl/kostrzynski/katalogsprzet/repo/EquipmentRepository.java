package pl.kostrzynski.katalogsprzet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAvailability(Boolean availability);
    List<Equipment> findAllByClassification(Classification classification);
    List<Equipment> findAllBySpecification(String specification);
    List<Equipment> findAllByName(String name);
}
