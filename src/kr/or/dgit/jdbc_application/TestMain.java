package kr.or.dgit.jdbc_application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.dgit.jdbc_application.dao.DepartmentDao;
import kr.or.dgit.jdbc_application.dao.EmployeeDao;
import kr.or.dgit.jdbc_application.dao.TitleDao;
import kr.or.dgit.jdbc_application.dto.Department;
import kr.or.dgit.jdbc_application.dto.Employee;
import kr.or.dgit.jdbc_application.dto.Title;
import kr.or.dgit.jdbc_application.jdbc.DBCon;
import kr.or.dgit.jdbc_application.jdbc.JdbcUtil;

public class TestMain {

	public static void main(String[] args) {
		// testDBCon();

//		testDepartmentDao();

//		testTitleDao();
		
		Employee emp=new Employee(5555, "이순신", new Title(3), new Employee(4377), 1500000, new Department(5));
//		employeeInsert(emp);
//		employeeDelete(emp);
//		employeeListAll();
		employeeSelect(emp);
		
		

	}
	private static void employeeSelect(Employee emp) {
		try {
			Employee searchEmployee=EmployeeDao.getInstance().selectItemByNo(emp);
			System.out.println(searchEmployee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void employeeListAll() {
		try {
			List<Employee> lists=EmployeeDao.getInstance().selectItemByAll();
			for(Employee emp:lists){
				System.out.println(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void employeeDelete(Employee emp) {
		try {
			EmployeeDao.getInstance().deleteItem(emp);
			JOptionPane.showMessageDialog(null, "사원정보를 삭제하였습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "존재하지 않는 사원입니다.");
		}
		
	}
	private static void employeeInsert(Employee emp) {
		try {
			EmployeeDao.getInstance().insertItem(emp);
			JOptionPane.showMessageDialog(null, "사원정보가 추가되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "이미 존재하는 사원입니다.");
		}
	}
	private static void testTitleDao() {
		Title tt=new Title(6, "차장");
		
		titleInsert(tt);
		titleListAll();
		
		tt.setTitleName("부사장");
		titleUpdqte(tt);
		titleSearchByNo(tt);
		
		titleDelete(tt);
		titleListAll();
	}
private static void titleUpdqte(Title tt) {
		try {
			TitleDao.getInstance().updateItem(tt);
			JOptionPane.showMessageDialog(null, "직급이 수정되었습니다..");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "직급이 없습니다.");
		}
	}

	private static void titleSearchByNo(Title tt) {
		try {
			Title searchTitle=TitleDao.getInstance().selectItemByNo(tt);
			System.out.println(tt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void titleListAll() {
		try {
			List<Title> lists=TitleDao.getInstance().selectItemByAll();
			for(Title tt:lists){
				System.out.println(tt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void titleDelete(Title tt) {
		try {
			TitleDao.getInstance().deleteItem(tt);
			JOptionPane.showMessageDialog(null, "직급이 삭제되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "직급이 없습니다.");
		}
	}

	private static void titleInsert(Title tt) {
		try {
			TitleDao.getInstance().insertItem(new Title(6, "차장"));
			JOptionPane.showMessageDialog(null, "직급이 추가되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "직급이 중복됩니다.");
		}
	}

	private static void testDepartmentDao() {
		Department dept = new Department(4, "마케팅", 10);

		testInsert(dept);
		testListAll();

		dept.setDeptName("마케팅2");
		testUpdate(dept);
		testSearchByDeptNo(dept);
		
		testDelete(dept);
		testListAll();
	}

	private static void testUpdate(Department dept) {
		try {
			DepartmentDao.getInstance().updateItem(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static void testSearchByDeptNo(Department dept) {
		try {
			Department searchDept=DepartmentDao.getInstance().selectItemByNo(dept);
			System.out.println(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testListAll() {
		try {
			List<Department> lists=DepartmentDao.getInstance().selectItemByAll();
			for(Department dept:lists){
				System.out.println(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testDelete(Department dept) {
		try {
			DepartmentDao.getInstance().deleteItem(dept);
			JOptionPane.showMessageDialog(null, "부서가 삭제되었습니다..");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}
	}

	private static void testInsert(Department dept) {
		try {
			DepartmentDao.getInstance().insertItem(dept);
			JOptionPane.showMessageDialog(null, "부서가 추가되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "부서번호가 중복됩니다.");
		}
	}

	private static void testDBCon() {
		DBCon dbCon = DBCon.getInstance();

		Connection connection = dbCon.getConnection();
		System.out.println(connection);

		JdbcUtil.close(connection);
	}

}
