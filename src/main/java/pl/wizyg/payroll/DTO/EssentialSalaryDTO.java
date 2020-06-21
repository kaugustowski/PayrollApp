package pl.wizyg.payroll.DTO;

import pl.wizyg.payroll.entity.Salary;

public class EssentialSalaryDTO {
    private String baseSalary;
    private String grossSalary;
    private String netSalary;
    private String sicknessContribution;
    private String pensionContributionEmployeeString;
    private String pensionContributionPayerString;
    private String accidentInsuranceContribution;
    private String disabilityContributionEmployeeString;
    private String disabilityContributionPayerString;

    public EssentialSalaryDTO(String baseSalary, String grossSalary, String netSalary, String sicknessContribution, String pensionContributionEmployeeString, String pensionContributionPayerString, String accidentInsuranceContribution, String disabilityContributionEmployeeString, String disabilityContributionPayerString) {
        this.baseSalary = baseSalary;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.sicknessContribution = sicknessContribution;
        this.pensionContributionEmployeeString = pensionContributionEmployeeString;
        this.pensionContributionPayerString = pensionContributionPayerString;
        this.accidentInsuranceContribution = accidentInsuranceContribution;
        this.disabilityContributionEmployeeString = disabilityContributionEmployeeString;
        this.disabilityContributionPayerString = disabilityContributionPayerString;
    }

    public EssentialSalaryDTO(Salary salary) {
        this.baseSalary = salary.getBaseSalaryString();
        this.grossSalary = salary.getGrossSalaryString();
        this.sicknessContribution = salary.getSicknessContributionString();
        this.pensionContributionEmployeeString = salary.getPensionContributionEmployeeString();
        this.pensionContributionPayerString = salary.getPensionContributionPayerString();
        this.accidentInsuranceContribution = salary.getAccidentInsuranceContributionString();
        this.disabilityContributionEmployeeString = salary.getDisabilityContributionEmployeeString();
        this.disabilityContributionPayerString = salary.getDisabilityContributionPayerString();
        this.netSalary = salary.getNetSalaryString();
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(String grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(String netSalary) {
        this.netSalary = netSalary;
    }

    public String getSicknessContribution() {
        return sicknessContribution;
    }

    public void setSicknessContribution(String sicknessContribution) {
        this.sicknessContribution = sicknessContribution;
    }

    public String getPensionContributionEmployeeString() {
        return pensionContributionEmployeeString;
    }

    public void setPensionContributionEmployeeString(String pensionContributionEmployeeString) {
        this.pensionContributionEmployeeString = pensionContributionEmployeeString;
    }

    public String getPensionContributionPayerString() {
        return pensionContributionPayerString;
    }

    public void setPensionContributionPayerString(String pensionContributionPayerString) {
        this.pensionContributionPayerString = pensionContributionPayerString;
    }

    public String getAccidentInsuranceContribution() {
        return accidentInsuranceContribution;
    }

    public void setAccidentInsuranceContribution(String accidentInsuranceContribution) {
        this.accidentInsuranceContribution = accidentInsuranceContribution;
    }

    public String getDisabilityContributionEmployeeString() {
        return disabilityContributionEmployeeString;
    }

    public void setDisabilityContributionEmployeeString(String disabilityContributionEmployeeString) {
        this.disabilityContributionEmployeeString = disabilityContributionEmployeeString;
    }

    public String getDisabilityContributionPayerString() {
        return disabilityContributionPayerString;
    }

    public void setDisabilityContributionPayerString(String disabilityContributionPayerString) {
        this.disabilityContributionPayerString = disabilityContributionPayerString;
    }

    @Override
    public String toString() {
        return "EssentialSalaryDTO{" +
                "baseSalary='" + baseSalary + '\'' +
                ", grossSalary='" + grossSalary + '\'' +
                ", netSalary='" + netSalary + '\'' +
                ", sicknessContribution='" + sicknessContribution + '\'' +
                ", pensionContributionEmployeeString='" + pensionContributionEmployeeString + '\'' +
                ", pensionContributionPayerString='" + pensionContributionPayerString + '\'' +
                ", accidentInsuranceContribution='" + accidentInsuranceContribution + '\'' +
                ", disabilityContributionEmployeeString='" + disabilityContributionEmployeeString + '\'' +
                ", disabilityContributionPayerString='" + disabilityContributionPayerString + '\'' +
                '}';
    }
}
