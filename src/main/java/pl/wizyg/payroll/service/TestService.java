package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.entity.Test;
import pl.wizyg.payroll.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public void saveTest(Test test){
        testRepository.saveAndFlush(test);

    }
}
