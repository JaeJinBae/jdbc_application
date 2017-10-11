package kr.or.dgit.jdbc_application.dto;

public class Department {
	private int deptNo;
	private String deptName;
	private int floor;

	private Department() {
		// TODO Auto-generated constructor stub
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("Department [deptNo=%s, deptName=%s, floor=%s]", deptNo, deptName, floor);
	}

}
