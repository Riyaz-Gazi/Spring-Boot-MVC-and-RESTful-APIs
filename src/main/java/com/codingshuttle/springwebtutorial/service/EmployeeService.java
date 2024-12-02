package com.codingshuttle.springwebtutorial.service;

import com.codingshuttle.springwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springwebtutorial.entity.Employee;
import com.codingshuttle.springwebtutorial.exception.ResourceNotFoundException;
import com.codingshuttle.springwebtutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        return employee.map(employee1 -> mapper.map(employee1,EmployeeDTO.class));
        return employeeRepository.findById(id).map(employee -> mapper.map(employee, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistsByEmployeeId(employeeId);
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employee.setId(employeeId);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapper.map(savedEmployee, EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) throw new ResourceNotFoundException("Employee Not Found with id : " + id);
    }

    public boolean deleteEmployeeById(Long id) {
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO  updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        Employee employee = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Employee.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employee, value);
        });
        return mapper.map(employeeRepository.save(employee), EmployeeDTO.class);
    }
}
