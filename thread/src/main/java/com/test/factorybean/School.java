package com.test.factorybean;

/**
 * @author  作者 wangbiao
 * @date 创建时间：2016年11月14日 上午11:13:18 
 * @parameter 
 * @return 
 */
public class School {
    private String schoolName;
	private String address;
	private int studentNumber;
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	@Override
	public String toString() {
		return "School [schoolName=" + schoolName + ", address=" + address
				+ ", studentNumber=" + studentNumber + "]";
	}
}