package dev.ghlima.locatech.aggregatorservice.dto;

import org.springframework.graphql.data.method.annotation.SchemaMapping;

@SchemaMapping("VehicleAndReservationResponse")
public record VehicleAndReservationResponseDTO(
  VehicleDTO vehicle,
  ReservationDTO reservation
) {
}
