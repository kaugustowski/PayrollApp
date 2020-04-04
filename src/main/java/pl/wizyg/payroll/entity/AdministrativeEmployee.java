package pl.wizyg.payroll.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "administrative_employee")
public class AdministrativeEmployee extends Employee implements FullTimeEmployee {


    private double bonus;

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {

        salary = baseSalary + seniorityBonus + bonus;

        return salary;
    }
}
