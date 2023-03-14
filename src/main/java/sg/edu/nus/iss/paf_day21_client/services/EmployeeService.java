package sg.edu.nus.iss.paf_day21_client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21_client.models.Employee;
import sg.edu.nus.iss.paf_day21_client.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> findAll(){

        return empRepo.findAll();
    } 

    public Employee findById(Integer id){

        return empRepo.findById(id);
    } 

    public Boolean save(Employee employee){

        return empRepo.save(employee);
    } 

    public int update(Employee employee){

        return empRepo.update(employee);
    } 

    public int delete(Integer id){

        return empRepo.delete(id);
    } 

}
