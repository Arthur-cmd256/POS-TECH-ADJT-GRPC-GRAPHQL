package fiap.com.br.repository;

import fiap.com.br.entity.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {
  public Optional<Reservation> findByVehiculeId(long vehicleId) {
    return find("vehicleId", vehicleId).firstResultOptional();
  }
}
