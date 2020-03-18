package pl.kostrzynski.katalogsprzet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kostrzynski.katalogsprzet.model.Equipment;

import java.util.List;

public interface Repo extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByAvailability(Boolean availability);

}
