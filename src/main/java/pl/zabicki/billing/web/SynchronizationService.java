package pl.zabicki.billing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zabicki.billing.converter.EventConverter;
import pl.zabicki.billing.data.model.CsvEvent;
import pl.zabicki.billing.data.reader.DataReader;
import pl.zabicki.billing.data.reader.EventReader;
import pl.zabicki.billing.db.postgres.model.Event;
import pl.zabicki.billing.db.postgres.repository.EventRepository;

import java.io.IOException;
import java.util.List;

@Service
public class SynchronizationService {

    @Autowired
    EventRepository repo;
    DataReader<CsvEvent> reader = new EventReader();

    public void synchronize() throws IOException {
        List<CsvEvent> records = reader.readData("data/test.csv");
        List<Event> events = EventConverter.convertEvents(records);

        repo.saveAll(events);
    }
}
