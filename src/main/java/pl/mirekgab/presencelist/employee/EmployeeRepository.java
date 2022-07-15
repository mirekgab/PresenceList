/*

*/
package pl.mirekgab.presencelist.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Override
    @Query(nativeQuery = false, value = "select e from Employee e join fetch e.department d")
    public List<Employee> findAll();

    @Query(nativeQuery=false, value="select e from Employee e where department_id=:departmentId")
    public List<Employee> findByDepartmentId(Long departmentId);

    
}
