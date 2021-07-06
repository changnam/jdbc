package jdbc;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		EmployeeDao eDao = (EmployeeDao) context.getBean("beanPropertyEmployeeDao",EmployeeDao.class);
		
		List<Employee> employees = eDao.getAllEmployees();
		for(Employee emp : employees) {
			System.out.println(emp.getName());
		}
		
		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("Hyundai");
		employee.setEmail("hyundai@hyundai.com");
		
		eDao.insertEmployee(employee);
		
		employee = eDao.getEmployee(2);
		employee.setName("Kia");
		employee.setEmail("kia@kia.com");
		
		eDao.updateEmployee(employee);
		
		employee = eDao.getEmployee(1);
		eDao.deleteEmployee(employee);
		
		System.out.println("======= after operation =============");
		employees = eDao.getAllEmployees();
		for(Employee emp : employees) {
			System.out.println(emp.getName()+" , "+emp.getEmail());
		}
		
	}
}
