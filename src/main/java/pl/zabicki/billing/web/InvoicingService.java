package pl.zabicki.billing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zabicki.billing.data.model.CsvAccount;
import pl.zabicki.billing.data.reader.AccountReader;
import pl.zabicki.billing.data.reader.DataReader;
import pl.zabicki.billing.db.postgres.repository.EventRepository;

import java.io.IOException;
import java.util.List;

@Service
public class InvoicingService {

    @Autowired
    EventRepository repo;
    DataReader<CsvAccount> reader = new AccountReader();

    public void startInvoicing() throws IOException {
        List<CsvAccount> accounts = reader.readData("data/accounts.csv");
        long totalStart = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            for (CsvAccount acc : accounts) {
                repo.findByClientIdAndAccountId(acc.clientId(), acc.accountId());
            }
            long stop = System.currentTimeMillis();
            System.out.println("Run " + i + " invoicing time: " + (stop - start));
        }
        long totalStop = System.currentTimeMillis();

        System.out.println("Total invoicing time: " + (totalStop - totalStart));
    }
}
