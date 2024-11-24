package assignment.sms.service;

import assignment.sms.entity.Department;
import assignment.sms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartmentsByIds(List<String> departmentIds) {
        return departmentRepository.findAllById(departmentIds);
    }
}
