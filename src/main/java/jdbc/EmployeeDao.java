package jdbc;

import java.util.List;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public void insertEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public Employee getEmployee(int id);
}
