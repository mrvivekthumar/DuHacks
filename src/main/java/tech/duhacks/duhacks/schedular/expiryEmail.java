package tech.duhacks.duhacks.schedular;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import tech.duhacks.duhacks.model.HealthProduct;
import tech.duhacks.duhacks.service.MailSenderService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.PriorityQueue;

@Getter
@Setter
@AllArgsConstructor
class medicineData{
    private LocalDate expiryTime;
    private Long id;
};


@Component
public class expiryEmail implements Runnable{

    private final MailSenderService mailSender;
    private static final ZoneId kolkataZoneId = ZoneId.of("Asia/Kolkata");
    private static final PriorityQueue<medicineData> taskQueue = new PriorityQueue<>((user1, user2) -> user1.getExpiryTime().compareTo(user2.getExpiryTime()));

    public expiryEmail(MailSenderService mailSender) {
        this.mailSender = mailSender;
    }

    public void addMedicine(HealthProduct hp){

        ZonedDateTime kolkataZonedTime = ZonedDateTime.now(kolkataZoneId);
        LocalDate kolkataLocalTime = kolkataZonedTime.toLocalDate();

        var md = new medicineData(hp.getExpiryDate(),hp.getId());

        if(!kolkataLocalTime.isAfter(md.getExpiryTime())){
            return;
        }

        taskQueue.add(md);

        if (taskQueue.size() == 1) {
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (taskQueue.isEmpty()) {
                break;
            }

            medicineData expiryData = taskQueue.peek();
            ZonedDateTime kolkataZonedTime = ZonedDateTime.now(kolkataZoneId);

            long timeForSleep = java.time.Duration.between(kolkataZonedTime, expiryData.getExpiryTime().atStartOfDay(kolkataZoneId).minusDays(2)).toMillis();

            if (timeForSleep <= 0) {
                taskQueue.poll();
                mailSender.SendEmail(expiryData.getId());
            } else {
                try {
                    Thread.sleep(timeForSleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread interrupted while sleeping", e);
                }
            }
        }
    }
}
