package pl.wizyg.payroll;

import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;

public class Main {
    public static void main(String[] args) {
        Employee teacher = new Teacher();
        teacher.setFirstName("Tomasz");
        teacher.setLastName("Nowak");
    }
}
