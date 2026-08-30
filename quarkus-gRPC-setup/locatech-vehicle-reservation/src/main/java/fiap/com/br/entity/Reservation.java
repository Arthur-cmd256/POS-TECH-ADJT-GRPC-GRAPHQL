package fiap.com.br.entity;

import fiap.com.br.utils.ReservationStatus;
import jakarta.persistence.*;

@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long reservationId;
  private Long vehicleId;
  private Long userId;
  private String startDate;
  private String endDate;

  @Enumerated(EnumType.STRING)
  private ReservationStatus status;
  
  public Reservation() {}

  public Reservation( Long vehicleId, Long userId, String startDate, String endDate, ReservationStatus status) {
    this.vehicleId = vehicleId;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public long getReservationId() {
    return reservationId;
  }

  public void setReservationId(long id) {
    this.reservationId = id;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
