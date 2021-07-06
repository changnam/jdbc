package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcEmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{
	
	public List<Employee> getAllEmployees() {
		String sql = "select * from employee";
		List<Employee> employees = getJdbcTemplate().query(sql, new EmployeeRowMapper());
		return employees;
	}

	public void insertEmployee(Employee employee) {
		String sql = "insert into employee (id,name,email) values (?,?,?)";
		int result = getJdbcTemplate().update(sql,employee.getId(),employee.getName(),employee.getEmail());
		
	}

	public void updateEmployee(Employee employee) {
		String sql = "update employee name = ? , email = ? where id = ?";
		int result = getJdbcTemplate().update(sql,employee.getName(),employee.getEmail(),employee.getId());
		
	}

	public void deleteEmployee(Employee employee) {
		String sql = "delete from  employee  where id = ?";
		int result = getJdbcTemplate().update(sql,employee.getId());
	
		
	}

	public Employee getEmployee(int id) {
		String sql = "select * from employee where id = ?";
		Employee employee = getJdbcTemplate().queryForObject(sql, new Object[] {id},new EmployeeRowMapper());
		return employee;
	}
	
}

final class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setEmail(rs.getString("email"));
		return employee;
	}
	
}
