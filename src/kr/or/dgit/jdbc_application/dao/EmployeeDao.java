package kr.or.dgit.jdbc_application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kr.or.dgit.jdbc_application.dto.Employee;
import kr.or.dgit.jdbc_application.jdbc.DBCon;

public class EmployeeDao implements SqlDao<Employee> {

	@Override
	public void insertItem(Employee item) throws SQLException {
		String sql="insert into employee values(?, ?, ?, ?, ?, ?)";
		Connection con=DBCon.getInstance().getConnection();
		int res=-1;
		try(PreparedStatement pstmt=con.prepareStatement(sql);){
			pstmt.setInt(1, item.getEmpNo());
			pstmt.setString(2, item.getEmpName());
			
			
		}
	}

	@Override
	public void deleteItem(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItem(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee selectItemByNo(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectItemByAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
