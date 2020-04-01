package pl.wizyg.payroll.service;


import pl.wizyg.payroll.entity.AdministrativeEmployee;

import java.util.List;


public interface AdministrativeEmployeeService {

    List<AdministrativeEmployee> getAdministrativeEmployees();

    void saveAdministrativeEmployee(AdministrativeEmployee administrativeEmployee);

    AdministrativeEmployee getAdministrativeEmployee(int id);

    void deleteAdministrativeEmployee(int id);
}
