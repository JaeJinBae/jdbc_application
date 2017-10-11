package kr.or.dgit.jdbc_application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_application.dto.Department;
import kr.or.dgit.jdbc_application.dto.Employee;
import kr.or.dgit.jdbc_application.dto.Title;
import kr.or.dgit.jdbc_application.jdbc.DBCon;

public class EmployeeDao implements SqlDao<Employee> {
	private static final EmployeeDao instance = new EmployeeDao();

	public static EmployeeDao getInstance() {
		return instance;
	}

	@Override
	public void insertItem(Employee item) throws SQLException {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		Connection con = DBCon.getInstance().getConnection();
		int res = -1;
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, item.getEmpNo());
			pstmt.setString(2, item.getEmpName());
			pstmt.setInt(3, item.getTitle().getTitleNo());
			pstmt.setInt(4, item.getManager().getEmpNo());
			pstmt.setInt(5, item.getSalary());
			pstmt.setInt(6, item.getDno().getDeptNo());
			pstmt.executeUpdate();

		}
	}

	@Override
	public void deleteItem(Employee item) throws SQLException {
		String sql = "delete from employee where empno=?";
		Connection con = DBCon.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, item.getEmpNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void updateItem(Employee item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee selectItemByNo(Employee item) throws SQLException {
		String sql = "select * from employee where empno=?";
		Employee emp = null;
		try (PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, item.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					emp = getEmployee(rs);
				}
			}
		}

		return emp;
	}

	@Override
	public List<Employee> selectItemByAll() throws SQLException {
		List<Employee> lists = new ArrayList<>();
		String sql = "select * from employee";

		try (PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				lists.add(getEmployee(rs));
				
			}
		}
		return lists;

	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt(1);
		String empName = rs.getString(2);
		Title title = TitleDao.getInstance().selectItemByNo(new Title(rs.getInt(3)));
		Employee manager=EmployeeDao.getInstance().selectItemByNo(new Employee(rs.getInt(4)));
		int salary = rs.getInt(5);		
		Department dno = DepartmentDao.getInstance().selectItemByNo(new Department(rs.getInt(6)));
		
		return new Employee(empNo, empName, title, new Employee(), salary, dno);
	}
}