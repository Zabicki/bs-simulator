package pl.zabicki.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class EventGenerator {

    Random random = new Random();

    public static void main(String[] args) throws IOException {
        new EventGenerator().generate(List.of(
                new Request(100_000, 1, 500),
                new Request(10_000, 3, 500),
                new Request(100, 1000, 10_000)
                ));
    }

    /**
     * Generate events for clients and accounts specified by requests.
     * Saves events in csv file
     * TODO csv files are too big, need to seperate the files, etc. max 50/100 MB per file?
     * Events will be inserted into tested database.
     * Also creates file with clients and accounts. Format for each row: clientId,accountId1,accountId2,...,accountIdn
     * @param requests generation requests specifying number of clients, accounts per clients and events per accounts
     *                 for 1 billing cycle
     */
    public void generate(List<Request> requests) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("test.csv"));
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("clientId",
                        "accountId",
                        "apInstanceId",
                        "callingNumber",
                        "calledNumber",
                        "callingPrefix",
                        "calledPrefix",
                        "eventBeginDate",
                        "eventEndDate",
                        "productId",
                        "rootProductId",
                        "intProperty1",
                        "intProperty2",
                        "intProperty3",
                        "intProperty4",
                        "intProperty5",
                        "stringProperty1",
                        "stringProperty2",
                        "stringProperty3",
                        "stringProperty4",
                        "stringProperty5",
                        "booleanProperty1",
                        "booleanProperty2",
                        "booleanProperty3",
                        "booleanProperty4",
                        "booleanProperty5",
                        "quantity",
                        "billingCycleDefId",
                        "billingCycleInstanceId",
                        "unit",
                        "billingProviderId")
                .build();

        try (final CSVPrinter printer = new CSVPrinter(writer, format)) {
            String billCycleDefId = UUID.randomUUID().toString();
            String billCycleInstanceId = UUID.randomUUID().toString();
            for (Request request : requests) {
                String bProviderId = UUID.randomUUID().toString();
                for (int i = 0; i < request.numOfClients(); i++) {
                    String clientId = UUID.randomUUID().toString();
                    for (int j = 0; j < request.numOfAccounts(); j++) {
                        String accountId = UUID.randomUUID().toString();
                        for (int k = 0; k < request.numOfEventsPerAccount(); k++) {
                            String apInstanceId = UUID.randomUUID().toString();
                            String productId = UUID.randomUUID().toString();
                            String rootProductId = UUID.randomUUID().toString();
                            Event event = Event.builder()
                                    .clientId(clientId)
                                    .accountId(accountId)
                                    .apInstanceId(apInstanceId)
                                    .billingCycleDefId(billCycleDefId)
                                    .billingCycleInstanceId(billCycleInstanceId)
                                    .billingProviderId(bProviderId)
                                    .calledNumber(randomNumber(100_000_000, 999_999_999))
                                    .callingNumber(randomNumber(100_000_000, 999_999_999))
                                    .calledPrefix(randomNumber(10, 99))
                                    .callingPrefix(randomNumber(10, 99))
                                    .eventBeginDate(randomDate())
                                    .eventEndDate(randomDate())
                                    .booleanProperty1(randomBool())
                                    .booleanProperty2(randomBool())
                                    .booleanProperty3(randomBool())
                                    .booleanProperty4(randomBool())
                                    .booleanProperty5(randomBool())
                                    .intProperty1(randomLong())
                                    .intProperty2(randomLong())
                                    .intProperty3(randomLong())
                                    .intProperty4(randomLong())
                                    .intProperty5(randomLong())
                                    .stringProperty1(randomString(5, 100))
                                    .stringProperty2(randomString(5, 100))
                                    .stringProperty3(randomString(5, 100))
                                    .stringProperty4(randomString(5, 100))
                                    .stringProperty5(randomString(5, 100))
                                    .unit(randomString(2, 16))
                                    .productId(productId)
                                    .rootProductId(rootProductId)
                                    .quantity(Long.parseLong(randomNumber(1, 1000)))
                                    .build();

                            printer.printRecord(event.getClientId(),
                                    event.getAccountId(),
                                    event.getApInstanceId(),
                                    event.getCallingNumber(),
                                    event.getCalledNumber(),
                                    event.getCallingPrefix(),
                                    event.getCalledPrefix(),
                                    event.getEventBeginDate(),
                                    event.getEventEndDate(),
                                    event.getProductId(),
                                    event.getRootProductId(),
                                    event.getIntProperty1(),
                                    event.getIntProperty2(),
                                    event.getIntProperty3(),
                                    event.getIntProperty4(),
                                    event.getIntProperty5(),
                                    event.getStringProperty1(),
                                    event.getStringProperty2(),
                                    event.getStringProperty3(),
                                    event.getStringProperty4(),
                                    event.getStringProperty5(),
                                    event.isBooleanProperty1(),
                                    event.isBooleanProperty2(),
                                    event.isBooleanProperty3(),
                                    event.isBooleanProperty4(),
                                    event.isBooleanProperty5(),
                                    event.getQuantity(),
                                    event.getBillingCycleDefId(),
                                    event.getBillingCycleInstanceId(),
                                    event.getUnit(),
                                    event.getBillingProviderId());
                        }
                    }
                }
            }
        }

    }

    private String randomNumber(int min, int max) {
        return String.valueOf(random.nextInt(min, max));
    }

    private String randomDate() {
        return Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt()).toString();
    }

    private boolean randomBool() {
        return random.nextBoolean();
    }

    private long randomLong() {
        return random.nextLong();
    }

    private String randomString(int min, int max) {
        int length = random.nextInt(min, max);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
