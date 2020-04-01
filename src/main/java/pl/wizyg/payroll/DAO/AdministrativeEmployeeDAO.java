package pl.wizyg.payroll.DAO;

import pl.wizyg.payroll.entity.AdministrativeEmployee;

import java.util.List;

public interface AdministrativeEmployeeDAO {


    List<AdministrativeEmployee> getAdministrativeEmployees();

    void saveAdministrativeEmployee(AdministrativeEmployee employee);

    AdministrativeEmployee getAdministrativeEmployee(int id);

    void deleteAdministrativeEmployee(int id);
}
