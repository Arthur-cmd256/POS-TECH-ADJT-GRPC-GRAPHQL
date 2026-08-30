package fiap.com.br.serivce;

import dev.ghlima.locatech.reservation.CompleteReservationRequest;
import dev.ghlima.locatech.reservation.CompleteReservationResponse;
import dev.ghlima.locatech.reservation.CreateReservationRequest;
import dev.ghlima.locatech.reservation.CreateReservationResponse;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdResponse;
import dev.ghlima.locatech.reservation.GetReservationRequest;
import dev.ghlima.locatech.reservation.GetReservationResponse;
import fiap.com.br.serivce.handler.ReservationResquestHandler;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import dev.ghlima.locatech.reservation.ReservationServiceGrpc.ReservationServiceImplBase;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdRequest;

@GrpcService
@Blocking
public class ReservationService extends ReservationServiceImplBase {
    private final ReservationResquestHandler reservationResquestHandler;
    
    public ReservationService(ReservationResquestHandler reservationResquestHandler) {
      this.reservationResquestHandler = reservationResquestHandler;
    }

  @Override
  public void createReservation(
    CreateReservationRequest request, 
    StreamObserver<CreateReservationResponse> responseObserver
  ) {
      var response = reservationResquestHandler.createReservation(request);
      responseObserver.onNext(response);
      responseObserver.onCompleted();
  }

  @Override
  public void getReservation(GetReservationRequest request, StreamObserver<GetReservationResponse> responseObserver) {
    var response = reservationResquestHandler.getReservation(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getReservationByVehicleId(GetReservationByVehicleIdRequest request, StreamObserver<GetReservationByVehicleIdResponse> responseObserver) {
    var response = reservationResquestHandler.getReservationByVehicleId(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void completeReservation(CompleteReservationRequest request, StreamObserver<CompleteReservationResponse> responseObserver) {
    var response = reservationResquestHandler.completeReservation(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
