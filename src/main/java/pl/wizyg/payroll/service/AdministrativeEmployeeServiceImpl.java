package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.DAO.AdministrativeEmployeeDAO;
import pl.wizyg.payroll.entity.AdministrativeEmployee;

import java.util.List;

@Service
public class AdministrativeEmployeeServiceImpl implements AdministrativeEmployeeService {

    private final AdministrativeEmployeeDAO administrativeEmployeeDAO;


    public AdministrativeEmployeeServiceImpl(@Autowired AdministrativeEmployeeDAO administrativeEmployeeDAO) {
        this.administrativeEmployeeDAO = administrativeEmployeeDAO;
    }

    @Override
    public List<AdministrativeEmployee> getAdministrativeEmployees() {

        return administrativeEmployeeDAO.getAdministrativeEmployees();
    }

    @Override
    public void saveAdministrativeEmployee(AdministrativeEmployee administrativeEmployee) {

        administrativeEmployeeDAO.saveAdministrativeEmployee(administrativeEmployee);

    }

    @Override
    public AdministrativeEmployee getAdministrativeEmployee(int id) {

        return administrativeEmployeeDAO.getAdministrativeEmployee(id);
    }

    @Override
    public void deleteAdministrativeEmployee(int id) {
        administrativeEmployeeDAO.deleteAdministrativeEmployee(id);
    }
}
