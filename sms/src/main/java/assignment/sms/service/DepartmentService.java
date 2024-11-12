package assignment.sms.service;

import assignment.sms.domain.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {
    private final Map<Long, Department> departments = Map.of(
            21L, new Department(21L, "Mathematics", 11L, List.of(11L), List.of(101L)),
            22L, new Department(22L, "Science", 12L, List.of(12L), List.of(102L))
    );

    public Optional<Department> getDepartmentById(Long id) {
        return Optional.ofNullable(departments.get(id));
    }

    public List<Department> getAllDepartments() {
        return List.copyOf(departments.values());
    }
}