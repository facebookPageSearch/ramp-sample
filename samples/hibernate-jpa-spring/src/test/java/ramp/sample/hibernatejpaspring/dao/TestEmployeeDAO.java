/**
 * 
 */
package ramp.sample.hibernatejpaspring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ramp.sample.hibernatejpaspring.entity.Employee;

/**
 * @author Rama Palaniappan
 * @since Mar 30, 2014
 */
public class TestEmployeeDAO extends BaseDAOTest {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Test
	public void testFindAll() {
		List<Employee> employees = employeeDAO.findAll();
		Assert.assertTrue(employees.size() > 0);
	}
	
	@DataProvider
	public Object[][] dpFindById() {
		return new Object[][] {
				{10001},
				{10001},
				{10001},
				{10002},
				{10003},
		};
	}
	
	@Test(dataProvider="dpFindById")
	public void testFindById(Integer employeeId) {
		Employee actual = employeeDAO.find(employeeId);
		Assert.assertEquals(actual.getEmpNo(), employeeId);
	}
}
