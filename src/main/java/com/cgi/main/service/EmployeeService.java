package com.cgi.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.main.entity.Employee;
import com.cgi.main.exception.EmployeeNotFoundException;
import com.cgi.main.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository erepo;

	public void saveEmployee(Employee employee) {
		this.erepo.save(employee);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = this.erepo.findAll();

		return list;
	}

	public void deleteById(int id) {
		this.erepo.deleteById(id);
	}

	public void updateEmployee(Employee employee) {

		this.erepo.save(employee);
	}

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException{
		// Employee emp=this.erepo.getOne(id);
		 Optional<Employee> emp=this.erepo.findById(id);
		 Employee e=null;
		 System.out.println(emp);
		 if(!emp.isEmpty()) {
			 if(emp!=null)
			 e=emp.get();
		 }
		 else {
			 throw new EmployeeNotFoundException("ID IS NOT FOUND!");
		 }
		 return e;
	}
	

}
