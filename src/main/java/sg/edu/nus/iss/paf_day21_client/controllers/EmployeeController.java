package sg.edu.nus.iss.paf_day21_client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.paf_day21_client.models.Employee;
import sg.edu.nus.iss.paf_day21_client.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService empSvc;

    @GetMapping
    public String listEmployee(Model model){

        List<Employee> employees = empSvc.findAll();
        model.addAttribute("employees", employees);
        return "employeelist";
    }

    @GetMapping("/addnew")
    public String createEmployee(Model model){

        Employee employeeForm = new Employee();

        model.addAttribute("employeeForm", employeeForm);
        return "newemployee";

    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employeeForm") Employee empForm, BindingResult result){

        // if(result.hasErrors()){
        //     return "newemployee";
        // }

        empSvc.save(empForm); // API service methodmust @RequestBody to process employee model

        return "redirect:/employees";
    }

    // hyperlink default method is /GET
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        empSvc.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Integer id, Model model){

        Employee retrievedEmployee = empSvc.findById(id);

        model.addAttribute("employeeForm", retrievedEmployee);

        return "updateemployee";
    }


    @PostMapping("/saveUpdate")
    public String saveUpdateEMployee(@ModelAttribute("employeeForm") Employee employee, BindingResult result ){

        if(result.hasErrors()){
            return "updateemployee";
        }

        empSvc.update(employee);

        return "redirect:/employees";

    }

}
