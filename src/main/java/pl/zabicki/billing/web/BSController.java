package pl.zabicki.billing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zabicki.billing.db.postgres.repository.EventRepository;

@RestController
@RequestMapping(value = "/simulate/")
public class BSController {

    @Autowired
    EventRepository repo;

    @GetMapping(value = "invoicing")
    public String startInvoicing() {
        // TODO start invoicing simulation
        return "Invoicing simulation started";
    }

    @GetMapping(value = "synchronization")
    public String startEventSynchronization() {
        /**
         * TODO przeczytac dane z pliku i zapisywac do bazy paczkami? Czy calosc? Przy duzej ilosci danych raczej paczkami po n eventow
         * Potem to przerobic zeby czytal w czasie po n eventow na sekunde
         */
        //repo.saveAll();
        return "Event synchronization simulation started";
    }
}
