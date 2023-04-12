package pl.zabicki.billing.db.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zabicki.billing.db.postgres.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByClientIdAndAccountId(String clientId, String accountId);
}
