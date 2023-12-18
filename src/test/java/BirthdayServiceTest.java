import domain.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BirthdayServiceTest {
    @Test
    void noBirthdayToday() {
        EmployeeRepository repository = null;
        EmailSender notifier = new SpyEmailSender();
        BirthdayService birthdayService = new BirthdayService(repository, notifier);
        LocalDate today = LocalDate.of(1982, 10, 9);
        birthdayService.sendBirthdayEmail(today);

        assertThat(((SpyEmailSender)notifier).getSentGreetingsCount()).isEqualTo(0);
    }
}

class SpyEmailSender implements EmailSender {
    public int getSentGreetingsCount() {
        return 0;
    }
}
