package pl.wizyg.payroll.DTO;

public class SalaryListDTO {
    private final int month;
    private final int year;

    public SalaryListDTO(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
