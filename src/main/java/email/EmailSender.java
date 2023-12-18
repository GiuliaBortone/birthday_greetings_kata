package email;

import domain.entity.Employee;

public interface EmailSender {
    void sendBirthdayEmail(Employee employee);
}
