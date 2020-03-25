package pl.wizyg.payroll.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrative_employee")
public class AdministrativeEmployee extends Employee implements FullTimeEmployee {


    private double bonus;

    @Override
    public double calculateSalary() {

        salary = baseSalary + seniorityBonus + bonus;

        return salary;
    }
}
