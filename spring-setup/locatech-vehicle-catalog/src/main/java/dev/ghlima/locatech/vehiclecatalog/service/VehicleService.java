package dev.ghlima.locatech.vehiclecatalog.service;

import dev.ghlima.locatech.catalog.AddVehicleRequest;
import dev.ghlima.locatech.catalog.AddVehicleResponse;
import dev.ghlima.locatech.catalog.CatalogServiceGrpc.CatalogServiceImplBase;
import dev.ghlima.locatech.catalog.GetVehicleByIdRequest;
import dev.ghlima.locatech.catalog.GetVehicleByIdResponse;
import dev.ghlima.locatech.catalog.ListAvailableVehiclesRequest;
import dev.ghlima.locatech.catalog.ListAvailableVehiclesResponse;
import dev.ghlima.locatech.vehiclecatalog.service.handler.VehicleCatalogResquestHandler;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class VehicleService extends CatalogServiceImplBase {
  
  private final VehicleCatalogResquestHandler vehicleCatalogResquestHandler;
  
  public VehicleService(VehicleCatalogResquestHandler vehicleCatalogResquestHandler) {
    this.vehicleCatalogResquestHandler = vehicleCatalogResquestHandler;
  }

  @Override
  public void addVehicle(AddVehicleRequest request, StreamObserver<AddVehicleResponse> responseObserver) {
    var response = vehicleCatalogResquestHandler.addVehicle(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void listAvailableVehicles(ListAvailableVehiclesRequest request, StreamObserver<ListAvailableVehiclesResponse> responseObserver) {
    var response = vehicleCatalogResquestHandler.getAllAvailableVehicles();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getVehicleById(GetVehicleByIdRequest request, StreamObserver<GetVehicleByIdResponse> responseObserver) {
    var response = vehicleCatalogResquestHandler.getVehicleById(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
