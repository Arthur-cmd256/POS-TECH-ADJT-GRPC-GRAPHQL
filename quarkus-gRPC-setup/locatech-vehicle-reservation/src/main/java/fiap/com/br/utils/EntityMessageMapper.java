package fiap.com.br.utils;

import fiap.com.br.entity.Reservation;
import dev.ghlima.locatech.reservation.CreateReservationRequest;
import dev.ghlima.locatech.reservation.GetReservationResponse;
import dev.ghlima.locatech.reservation.ReservationProto;
import dev.ghlima.locatech.reservation.ReservationStatusProto;
import dev.ghlima.locatech.reservation.CompleteReservationResponse;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdResponse;

public class EntityMessageMapper {
  
  public static Reservation toEntity(CreateReservationRequest createReservationRequest) {
    return new Reservation(
      createReservationRequest.getVehicleId(), 
      createReservationRequest.getUserId(), 
      createReservationRequest.getStartDate(), 
      createReservationRequest.getEndDate(), 
      ReservationStatus.PENDING
    );
  }
  
  public static GetReservationResponse toGetReservationResponse(Reservation r) {
    return GetReservationResponse.newBuilder().setReservation(
      ReservationProto.newBuilder()
        .setReservationId(r.getReservationId())
        .setVehicleId(r.getVehicleId())
        .setUserId(r.getUserId())
        .setStartDate(r.getStartDate())
        .setEndDate(r.getEndDate())
        .setStatus(ReservationStatusProto.valueOf(r.getStatus().name()))
        .build()
    ).build();
  }
  
  public static CompleteReservationResponse toCompleteReservationResponse(Long totalDays) {
    return CompleteReservationResponse
      .newBuilder().setTotalDays(totalDays).setMessage("Reservation completed sucessfully").build();
  }
  
  public static GetReservationByVehicleIdResponse toGetReservationByVehicleId(Reservation r) {
    return GetReservationByVehicleIdResponse
      .newBuilder()
      .setReservation(
        ReservationProto.newBuilder()
          .setReservationId(r.getReservationId())
          .setVehicleId(r.getVehicleId())
          .setUserId(r.getUserId())
          .setStartDate(r.getStartDate())
          .setEndDate(r.getEndDate())
          .setStatus(ReservationStatusProto.valueOf(r.getStatus().name()))
          .build()
      )
      .build();
  }
}

