package pl.mirekgab.presencelist.employee;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    
    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee findById(long l) {
        return repository.findById(l).orElseThrow();
    }

}
