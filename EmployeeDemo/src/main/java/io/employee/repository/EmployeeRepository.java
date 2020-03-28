package io.employee.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.employee.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

}
