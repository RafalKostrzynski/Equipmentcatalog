package pl.kostrzynski.katalogsprzet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzynski.katalogsprzet.model.Classification;
import pl.kostrzynski.katalogsprzet.model.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAvailability(Boolean availability);
    List<Equipment> findAllByClassification(Classification classification);
    List<Equipment> findAllBySpecification(String specification);
    List<Equipment> findAllByEquipmentName(String name);
}
