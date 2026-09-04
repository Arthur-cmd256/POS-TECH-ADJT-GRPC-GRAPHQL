package dev.ghlima.locatech.aggregatorservice.dto;

import org.springframework.graphql.data.method.annotation.SchemaMapping;

@SchemaMapping("AddVehicleResponde")
public class AddVehicleRespondeDTO {
  
  private Long vehicleId;
  private String message;

  public AddVehicleRespondeDTO(Long vehicleId, String message) {
    this.vehicleId = vehicleId;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }
}
