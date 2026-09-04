package dev.ghlima.locatech.aggregatorservice.dto;

import org.springframework.graphql.data.method.annotation.SchemaMapping;

@SchemaMapping("CompleteReservationResponse")
public record CompleteReservationResponseDTO(
  String message,
  long totalDays
) {
}
