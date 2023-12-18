package domain.entity;

import java.time.LocalDate;

public record Employee (String lastName, String firstName, LocalDate dateOfBirth, String email){
}
