package dev.ghlima.locatech.aggregatorservice.controller;

import dev.ghlima.locatech.aggregatorservice.dto.AddVehicleRespondeDTO;
import dev.ghlima.locatech.aggregatorservice.dto.VehicleAndReservationResponseDTO;
import dev.ghlima.locatech.aggregatorservice.dto.VehicleDTO;
import dev.ghlima.locatech.aggregatorservice.service.VehicleCatalogService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehicleController {

  private final VehicleCatalogService vehicleCatalogService;

  public VehicleController(VehicleCatalogService vehicleCatalogService) {
    this.vehicleCatalogService = vehicleCatalogService;
  }

  @QueryMapping
  public List<VehicleDTO> listAvailableVehicles() {
    return vehicleCatalogService.listAvailableVehicles();
  }

  @QueryMapping
  public VehicleDTO getVehicleById(@Argument Long id) {
    return vehicleCatalogService.getVehicleById(id);
  }

  @QueryMapping
  public VehicleAndReservationResponseDTO getVehicleAndReservationById(@Argument Long vehicleId) {
    return vehicleCatalogService.getVehicleAndReservationById(vehicleId);
  }

  @MutationMapping
  public AddVehicleRespondeDTO addVehicle(@Argument("vehicle") VehicleDTO vehicle) {
    return vehicleCatalogService.addVehicle(vehicle);
  }
}
