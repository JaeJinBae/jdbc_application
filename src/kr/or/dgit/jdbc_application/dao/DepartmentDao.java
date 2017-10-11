package kr.or.dgit.jdbc_application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_application.dto.Department;
import kr.or.dgit.jdbc_application.jdbc.DBCon;

public class DepartmentDao implements SqlDao<Department> {
	private static final DepartmentDao instance = new DepartmentDao();

	public static DepartmentDao getInstance() {
		return instance;
	}

	@Override
	public void insertItem(Department item) throws SQLException {
		String sql = "INSERT INTO department VALUES(?, ?, ?)";
		Connection con = DBCon.getInstance().getConnection();
		int res = -1;
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {// try안에
																	// 넣어주면 auto
																	// close됩니다.괄호안에
																	// 안넣어주면
																	// catch뒤에
																	// finally해줘야함
			pstmt.setInt(1, item.getDeptNo());
			pstmt.setString(2, item.getDeptName());
			pstmt.setInt(3, item.getFloor());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteItem(Department item) throws SQLException {
		String sql = "delete from department where deptno=?";
		Connection con = DBCon.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, item.getDeptNo());
			pstmt.executeUpdate();
		} 
	}

	@Override
	public void updateItem(Department item) throws SQLException {
		String sql = "update department set deptname=?, floor=? where deptno=?";
		Connection con = DBCon.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, item.getDeptName());
			pstmt.setInt(2, item.getFloor());
			pstmt.setInt(3, item.getDeptNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Department selectItemByNo(Department item) throws SQLException {
		List<Department> lists = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department where deptno=?";
		Department dept = null;

		try (PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);) {
			pstmt.setInt(1, item.getDeptNo());
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					dept = getDepartment(rs);
				}
			}
		}
		return dept;
	}

	@Override
	public List<Department> selectItemByAll() throws SQLException {
		List<Department> lists = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";

		try (PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				lists.add(getDepartment(rs));
			}
		}
		return lists;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt(1);
		String deptName = rs.getString(2);
		int floor = rs.getInt(3);
		return new Department(deptNo, deptName, floor);
	}

}
