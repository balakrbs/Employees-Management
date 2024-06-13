package com.employee.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.saveEmployee(employee);
        return "success.html";
    }

    @GetMapping("/displayAll")
    public String displayAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employeeList";
    }
    
    
//    @GetMapping("/forward")
//    public String forwardpage(Model model) {  
//    	 	model.addAttribute("employee", new Employee());
//           return "Searchemployee";
//       }
    
    
    @PostMapping("/display")
    public String displayEmployeeById(@ModelAttribute(value="employee") Employee employee,Model model) { 
    	//System.out.println(employee.getId());    	
        Optional<Employee> emp = employeeService.getEmployeeById(employee.getId());
        if (emp.isPresent()) {
        	model.addAttribute("view", "detail");
            model.addAttribute("employee", emp.get());
            return "employeeDetail";
        } else {
            return "error";
        }
    }
    
    
  
}

