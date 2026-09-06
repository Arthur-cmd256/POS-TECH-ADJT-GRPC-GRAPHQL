package dev.ghlima.locatech.aggregatorservice.dto;

import org.springframework.graphql.data.method.annotation.SchemaMapping;

@SchemaMapping ("CreateReservationResponse")
public record CreateReservationResponseDTO(
  Long reservationId,
  String message
) {
}
