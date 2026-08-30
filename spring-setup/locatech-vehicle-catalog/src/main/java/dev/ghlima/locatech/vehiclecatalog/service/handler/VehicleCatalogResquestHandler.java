package dev.ghlima.locatech.vehiclecatalog.service.handler;

import dev.ghlima.locatech.vehiclecatalog.entity.Vehicle;
import dev.ghlima.locatech.vehiclecatalog.exceptions.VehicleNotFoundException;
import dev.ghlima.locatech.vehiclecatalog.repository.VehicleRepository;
import dev.ghlima.locatech.vehiclecatalog.utils.EntityMessageMapper;
import org.springframework.stereotype.Service;

import dev.ghlima.locatech.catalog.GetVehicleByIdResponse;
import dev.ghlima.locatech.catalog.GetVehicleByIdRequest;
import dev.ghlima.locatech.catalog.ListAvailableVehiclesResponse;
import dev.ghlima.locatech.catalog.AddVehicleResponse;
import dev.ghlima.locatech.catalog.AddVehicleRequest;

import java.util.List;

@Service
public class VehicleCatalogResquestHandler {

  private final VehicleRepository vehicleRepository;
  
  public VehicleCatalogResquestHandler(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }
  
  public GetVehicleByIdResponse getVehicleById(GetVehicleByIdRequest getVehicleByIdRequest) {
    Vehicle vehicle = vehicleRepository.findById(getVehicleByIdRequest.getId()).orElseThrow(
      () -> new VehicleNotFoundException(getVehicleByIdRequest.getId())
    );
    return EntityMessageMapper.toVehicleProtoMessage(vehicle);
  }
  
  public ListAvailableVehiclesResponse getAllAvailableVehicles() {
    List<Vehicle> vehicles = vehicleRepository.findByAvailableTrue();
    return EntityMessageMapper.toListAvailableVehiclesResponseMessage(vehicles);
  }
  
  public AddVehicleResponse addVehicle(AddVehicleRequest addVehicleRequest) {
    Vehicle vehicle = EntityMessageMapper.toEntity(addVehicleRequest);
    Vehicle vehicleSave = vehicleRepository.save(vehicle);
    return EntityMessageMapper.toAddVehicleResponse(vehicleSave);
  }
}
