package dev.ghlima.locatech.aggregatorservice.service;

import dev.ghlima.locatech.aggregatorservice.dto.AddVehicleRespondeDTO;
import dev.ghlima.locatech.aggregatorservice.dto.ReservationDTO;
import dev.ghlima.locatech.aggregatorservice.dto.VehicleAndReservationResponseDTO;
import dev.ghlima.locatech.aggregatorservice.dto.VehicleDTO;
import dev.ghlima.locatech.catalog.AddVehicleRequest;
import dev.ghlima.locatech.catalog.CatalogServiceGrpc.CatalogServiceBlockingStub;
import dev.ghlima.locatech.catalog.GetVehicleByIdRequest;
import dev.ghlima.locatech.catalog.ListAvailableVehiclesRequest;
import dev.ghlima.locatech.catalog.VehicleProto;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdRequest;
import dev.ghlima.locatech.reservation.ReservationServiceGrpc.ReservationServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCatalogService {

  @GrpcClient("vehicle-catalog")
  private CatalogServiceBlockingStub catalogStub;

  @GrpcClient("reservation")
  private ReservationServiceBlockingStub reservationStub;

  public List<VehicleDTO> listAvailableVehicles() {
    var response = catalogStub.listAvailableVehicles(ListAvailableVehiclesRequest.newBuilder().build());
    return response.getVehiclesList().stream().map(VehicleDTO::new).toList();
  }

  public VehicleDTO getVehicleById(Long id) {
    var request = GetVehicleByIdRequest.newBuilder().setId(id).build();
    var response = catalogStub.getVehicleById(request);
    return new VehicleDTO(response.getVehicle());
  }

  public AddVehicleRespondeDTO addVehicle(VehicleDTO vehicleDTO) {
    var vehicle = VehicleProto.newBuilder()
      .setMake(vehicleDTO.make())
      .setModel(vehicleDTO.model())
      .setCategory(vehicleDTO.category())
      .setIsAvailable(vehicleDTO.isAvailable())
      .build();

    var request = AddVehicleRequest.newBuilder().setVehicle(vehicle).build();
    var response = catalogStub.addVehicle(request);
    return new AddVehicleRespondeDTO(response.getVehicleId(), response.getMessage());
  }

  public VehicleAndReservationResponseDTO getVehicleAndReservationById(Long vehicleId) {
    var vehicle = getVehicleById(vehicleId);
    var reservationRequest = GetReservationByVehicleIdRequest.newBuilder()
      .setVehicleId(vehicleId)
      .build();
    var reservationResponse = reservationStub.getReservationByVehicleId(reservationRequest);
    var reservation = reservationResponse.hasReservation()
      ? new ReservationDTO(reservationResponse.getReservation())
      : null;
    return new VehicleAndReservationResponseDTO(vehicle, reservation);
  }
}
