package fiap.com.br.serivce.handler;

import fiap.com.br.entity.Reservation;
import fiap.com.br.exception.ReservationNotFoundException;
import fiap.com.br.repository.ReservationRepository;
import dev.ghlima.locatech.reservation.CreateReservationResponse;
import dev.ghlima.locatech.reservation.CreateReservationRequest;
import fiap.com.br.utils.EntityMessageMapper;
import fiap.com.br.utils.ReservationStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import dev.ghlima.locatech.reservation.GetReservationResponse;
import dev.ghlima.locatech.reservation.GetReservationRequest;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdResponse;
import dev.ghlima.locatech.reservation.GetReservationByVehicleIdRequest;
import dev.ghlima.locatech.reservation.CompleteReservationResponse;
import dev.ghlima.locatech.reservation.CompleteReservationRequest;

import javax.print.attribute.standard.MediaSizeName;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class ReservationResquestHandler {
  
  private final ReservationRepository reservationRepository;
  
  public ReservationResquestHandler(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }
  
  @Transactional
  public CreateReservationResponse createReservation(CreateReservationRequest createReservationRequest) {
    var reservation = EntityMessageMapper.toEntity(createReservationRequest);
    reservationRepository.persistAndFlush(reservation);
    return CreateReservationResponse
      .newBuilder()
      .setMessage("Resevation created successfully")
      .setReservationId(reservation.getReservationId())
      .build();
  }
  
  @Transactional
  public GetReservationResponse getReservation(GetReservationRequest getReservationRequest) {
    Reservation reservation =  reservationRepository.findByIdOptional(getReservationRequest.getReservationId())
      .orElseThrow( () -> new ReservationNotFoundException(getReservationRequest.getReservationId()));
    return EntityMessageMapper.toGetReservationResponse(reservation);
  }
  
  @Transactional
  public GetReservationByVehicleIdResponse getReservationByVehicleId(
    GetReservationByVehicleIdRequest getReservationByVehicleIdRequest
  ) {
    Reservation reservation = reservationRepository.findByVehiculeId(getReservationByVehicleIdRequest.getVehicleId())
      .orElseThrow(() -> new ReservationNotFoundException(getReservationByVehicleIdRequest.getVehicleId()));
    return EntityMessageMapper.toGetReservationByVehicleId(reservation);
  }
  
  @Transactional
  public CompleteReservationResponse completeReservation(CompleteReservationRequest completeReservationRequest) {
    Reservation reservation = reservationRepository.findByIdOptional(completeReservationRequest.getReservationId())
      .orElseThrow( () -> new ReservationNotFoundException(completeReservationRequest.getReservationId()));
    
    reservation.setStatus(ReservationStatus.CONFIRMED);
    reservationRepository.persist(reservation);

    LocalDate startDate = LocalDate.parse(reservation.getStartDate());
    LocalDate endDate = LocalDate.parse(reservation.getEndDate());
    long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
    
    return EntityMessageMapper.toCompleteReservationResponse(totalDays);
  }
}
