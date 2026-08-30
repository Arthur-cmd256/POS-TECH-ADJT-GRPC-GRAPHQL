package fiap.com.br.exception;

public class ReservationNotFoundException  extends RuntimeException {

  private static final String message = "Reservation [id=%d] is not found";
  public ReservationNotFoundException(Long reservationId) {
    super(String.format(message, reservationId));
  }
}
