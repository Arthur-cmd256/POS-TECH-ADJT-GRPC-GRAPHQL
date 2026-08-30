package fiap.com.br.serivce.exception;

import fiap.com.br.exception.ReservationNotFoundException;
import io.grpc.*;
import io.quarkus.grpc.GlobalInterceptor;
import jakarta.inject.Singleton;

@Singleton
@GlobalInterceptor
public class ServiceExceptionHandler implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

    ServerCall.Listener<ReqT> listener = next.startCall(call, headers);
    
    return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
      @Override
      public void onHalfClose() {
        try {
          super.onHalfClose();
        } catch (ReservationNotFoundException e) {
            call.close(
              Status.NOT_FOUND.withDescription(e.getMessage()),
              new Metadata()
            );
        } catch (RuntimeException e) {
          call.close(
            Status.INTERNAL.withDescription(e.getMessage()),
            new Metadata()
          );
        }
      }
    };
  }
}
