package dev.ghlima.locatech.aggregatorservice.service;

import dev.ghlima.locatech.aggregatorservice.dto.CompleteReservationResponseDTO;
import dev.ghlima.locatech.aggregatorservice.dto.CreateReservationResponseDTO;
import dev.ghlima.locatech.aggregatorservice.dto.ReservationDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import dev.ghlima.locatech.reservation.ReservationServiceGrpc.ReservationServiceBlockingStub;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdRequest;
import dev.ghlima.locatech.reservation.CreateReservationRequest;
import dev.ghlima.locatech.reservation.CompleteReservationRequest;
import dev.ghlima.locatech.reservation.GetReservationRequest;

@Service
public class ReservationService {
  
  @GrpcClient("reservation")
  private ReservationServiceBlockingStub stub;

  public ReservationDTO getReservationById(Long reservationId) {
    var request = GetReservationRequest.newBuilder().setReservationId(reservationId).build();
    var response = stub.getReservation(request);
    return new ReservationDTO(response.getReservation());
  }
  
  public ReservationDTO getReservationByVehicleId(Long vehicleId) {
    var request = GetReservationByVehicleIdRequest.newBuilder()
      .setVehicleId(vehicleId)
      .build();
    var response = stub.getReservationByVehicleId(request);
    return new ReservationDTO(response.getReservation());
  }
  
  public CreateReservationResponseDTO createReservation(ReservationDTO reservationDTO) {
    var request = CreateReservationRequest.newBuilder()
      .setVehicleId(reservationDTO.vehicleId())
      .setUserId(reservationDTO.userId())
      .setStartDate(reservationDTO.startDate())
      .setEndDate(reservationDTO.endDate())
      .build();
    
    var response = stub.createReservation(request);
    return new CreateReservationResponseDTO(response.getReservationId(), response.getMessage());
  }
  
  public CompleteReservationResponseDTO completeReservation(Long reservationId) {
    var request = CompleteReservationRequest.newBuilder()
      .setReservationId(reservationId)
      .build();
    var response = stub.completeReservation(request);
    return new CompleteReservationResponseDTO(response.getMessage(), response.getTotalDays());
  }
}
