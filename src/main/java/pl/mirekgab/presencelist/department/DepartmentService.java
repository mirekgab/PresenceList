
package pl.mirekgab.presencelist.department;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Object findById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
}
