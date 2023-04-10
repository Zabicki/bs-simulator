package pl.zabicki.billing.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/simulate/")
public class BSController {

    @GetMapping(value = "invoicing")
    public String startInvoicing() {
        // TODO start invoicing simulation
        return "Invoicing simulation started";
    }

    @GetMapping(value = "synchronization")
    public String startEventSynchronization() {
        // TODO start invoicing simulation
        return "Event synchronization simulation started";
    }
}
