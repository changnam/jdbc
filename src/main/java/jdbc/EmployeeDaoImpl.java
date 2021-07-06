package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDaoImpl implements EmployeeDao {

		private JdbcTemplate jdbcTemplate;
		
		
		public void setDataSource(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
	public List<Employee> getAllEmployees() {
		List<Employee> employees = jdbcTemplate.query("select * from employee", new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				return employee;
			}
			
		});
		return employees;
	}

	public void insertEmployee(Employee employee) {
		String sql="insert into employee (id,name,email) values(?,?,?)";
		int result = jdbcTemplate.update(sql,employee.getId(),employee.getName(),employee.getEmail());
	}

	public void updateEmployee(Employee employee) {
		String sql="update employee set name = ?, email = ? where id = ?";
		int result = jdbcTemplate.update(sql,employee.getName(),employee.getEmail(),employee.getId());

		
	}

	public void deleteEmployee(Employee employee) {
		String sql="delete from employee where id = ?";
		int result = jdbcTemplate.update(sql,employee.getId());

	}

	public Employee getEmployee(int id) {
		Employee employee = jdbcTemplate.queryForObject("select * from employee where id = ?",new Object[] {id}, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				return employee;
			}
			
		});
		return employee;
	}

}
