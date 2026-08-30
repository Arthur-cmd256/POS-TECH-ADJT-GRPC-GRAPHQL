package dev.ghlima.locatech.vehiclecatalog.service.exception;

import dev.ghlima.locatech.vehiclecatalog.exceptions.VehicleNotFoundException;
import dev.ghlima.locatech.vehiclecatalog.utils.EntityMessageMapper;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

  @GrpcExceptionHandler(VehicleNotFoundException.class)
  public Status handleVehicleNotFoundException(VehicleNotFoundException e) {
    return Status.INVALID_ARGUMENT.withDescription(e.getMessage());
  }
}
