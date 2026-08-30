package dev.ghlima.locatech.vehiclecatalog.repository;

import dev.ghlima.locatech.vehiclecatalog.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
  List<Vehicle> findByAvailableTrue();
}
