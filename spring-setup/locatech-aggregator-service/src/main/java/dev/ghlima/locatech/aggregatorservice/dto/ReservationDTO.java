package dev.ghlima.locatech.aggregatorservice.dto;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import dev.ghlima.locatech.reservation.ReservationProto;
@SchemaMapping("Reservation")
public record ReservationDTO (
  Long reservationId,
  Long vehicleId,
  Long userId,
  String startDate,
  String endDate,
  String status
) {


  public ReservationDTO(ReservationProto reservation) {
    this(
      reservation.getReservationId(), 
      reservation.getVehicleId(), 
      reservation.getUserId(), 
      reservation.getStartDate(), 
      reservation.getEndDate(), 
      reservation.getStatus().toString()
    );
  }
}
