package com.demo.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.dao.EmployeeDao;
import com.demo.model.Employee;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public void insertEmployee(Employee cus) {
		
		String sql = "INSERT INTO employee " +
				"(empId, empName) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				cus.getEmpid(), cus.getName()
		});

	}

	@Override
	public void insertEmployees(List<Employee> employees) {
		
		String sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getEmpid());
				ps.setString(2, employee.getName());
			}
			
			public int getBatchSize() {
				return employees.size();
			}
		});
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Employee> result = new ArrayList<Employee>();
		for(Map<String, Object> row:rows){
			Employee emp = new Employee();
			emp.setEmpid((String)row.get("empId"));
			emp.setName((String)row.get("empName"));
			result.add(emp);
		}
		
		return result;
	}
	

	
	@Override
	public Employee getEmployeeById(String empId) {
		String sql = "SELECT * FROM employee WHERE empId = ?";
		return (Employee)getJdbcTemplate().queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>(){
			@Override
			public Employee mapRow(final ResultSet rs, final int rwNumber) throws SQLException {
				final Employee emp = new Employee();
				emp.setEmpid(rs.getString("empId"));
				emp.setName(rs.getString("empName"));
				return emp;
			}
		});

}
}
