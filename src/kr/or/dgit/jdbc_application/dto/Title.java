package kr.or.dgit.jdbc_application.dto;

public class Title {
	private int titleNo;
	private String titleName;

	private Title() {
		// TODO Auto-generated constructor stub
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return String.format("Title [titleNo=%s, titleName=%s]", titleNo, titleName);
	}

}
