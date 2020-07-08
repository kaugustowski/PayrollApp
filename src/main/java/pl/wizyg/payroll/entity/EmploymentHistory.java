package pl.wizyg.payroll.entity;

import org.springframework.format.annotation.DateTimeFormat;
import pl.wizyg.payroll.validator.ValidEmploymentHistoryDates;

import javax.persistence.*;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "employment_history")
@ValidEmploymentHistoryDates(message = "Data zakończenia nie może poprzedzać daty rozpoczęcia")
public class EmploymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "institution_name")
    private String institutionName;


    @Column(name = "unpaid_leave_days")
    private int numberOfDaysOnUnpaidLeave;


    public EmploymentHistory() {
    }

    public int getNumberOfWorkDays() {
        return (int) DAYS.between(startDate, endDate) + 1 - getNumberOfDaysOnUnpaidLeave();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfDaysOnUnpaidLeave() {
        return numberOfDaysOnUnpaidLeave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfDaysOnUnpaidLeave(int numberOfDaysOnUnpaidLeave) {
        this.numberOfDaysOnUnpaidLeave = numberOfDaysOnUnpaidLeave;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
}
