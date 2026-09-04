package dev.ghlima.locatech.aggregatorservice.dto;

import dev.ghlima.locatech.catalog.VehicleProto;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@SchemaMapping("Vehicle")
public record VehicleDTO(
  Long id,
  String make,
  String model,
  String category,
  Boolean isAvailable
) {

  public VehicleDTO(VehicleProto vehicle) {
    this(
      vehicle.getId(),
      vehicle.getMake(),
      vehicle.getModel(),
      vehicle.getCategory(),
      vehicle.getIsAvailable()
    );
  }
}
