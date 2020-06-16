package pl.wizyg.payroll.DTO;

public class SalaryListDTO {
    private int month;
    private int year;

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
