import domain.repository.EmployeeRepository;

import java.time.LocalDate;

public class BirthdayService {
    private final EmployeeRepository repository;
    private final EmailSender notifier;

    public BirthdayService(EmployeeRepository repository, EmailSender notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public void sendBirthdayEmail(LocalDate today) {

    }
}
