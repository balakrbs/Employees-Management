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
    private EmployeeService employeeService;			//importing the Service

    @GetMapping("/")											// refreshing the index
    public String index(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/save")																//Function for Adding data's to the database
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.saveEmployee(employee);
        return "success.html";
    }

    @GetMapping("/displayAll")															// Function Getting the list of employees from the database
    public String displayAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employeeList";
    }
    
    
//    @GetMapping("/forward")															//Function for redirect the next page -for testing purpose 
//    public String forwardpage(Model model) {  
//    	 	model.addAttribute("employee", new Employee());
//           return "Searchemployee";
//       }
    
    
    @PostMapping("/display")																				// Function for Getting the employee id to search the employee details
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

