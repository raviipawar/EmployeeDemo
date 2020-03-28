package io.employee.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.employee.model.Employee;
import io.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/all")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Employee> getById(@PathVariable ObjectId id) {
		return employeeRepository.findById(id);
	}

	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/edit")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
		if (existingEmployee.isPresent()) {
			employeeRepository.save(employee);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable ObjectId id) {
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if (existingEmployee.isPresent()) {
			employeeRepository.delete(existingEmployee.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/maxAge")
	public Employee getMaxAge() {
		Comparator<Employee> comparator = Comparator.comparing(Employee::getAge);
		List<Employee> employees = employeeRepository.findAll();
		Employee maxAgeEmployee = employees.stream().max(comparator).get();
		return maxAgeEmployee;

	}

	@GetMapping("/minAge")
	public Employee getMinAge() {
		Comparator<Employee> comparator = Comparator.comparing(Employee::getAge);
		List<Employee> employees = employeeRepository.findAll();
		Employee minAgeEmployee = employees.stream().max(comparator).get();
		return minAgeEmployee;
	}
}
