package com.project.leaveApp;

import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="empId")
	int empId;
	@Column(name="firstName")
	String firstName;
	@Column(name="lastName")
	String lastName;
	@Column(name="gender")
	String gender;
	@Column(name="contactNo")
	String contactNo;
	@Column(name="emailId")
	String emailId;
	@Column(name="joiningDate")
	LocalDate joiningDate;
	@Column(name="hrId")
	int hrId;
	@Column(name="password")
	String password;
	@Column(name="adminId")
	int adminId;
	
	//department
	@Column(name="deptName")
	String deptName;
	
//	//leave type
//	@Column(name="leaveName")----------
//	String leaveName;
//	@Column(name="maximumDays")--------
//	int maximumDays;
//	
//	//leave record
//	@Column(name="startingDate")------------
//	LocalDate startingDate;
//	@Column(name="endingDate")--------------
//	LocalDate endingDate;
//	@Column(name="reason")------------
//	String reason;
//	@Column(name="status")-----------
//	String status;
//	@Column(name="appliedDate")--------
//	LocalDate appliedDate;
//	@Column(name="approvedBy")--------
//	String approvedBy;
	@Column(name="leaveTaken")
	int leaveTaken;
	@Column(name="leaveRemaining")
	int leaveRemaining;
	@Column(name = "totalLeaves")
	int totalLeaves;
	@Column(name="role")
	String role;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}


	public Employee(int empId, String firstName, String lastName, String gender, String contactNo, String emailId,
			LocalDate joiningDate, int hrId, String password, int adminId, String deptName, int leaveTaken,
			int leaveRemaining, int totalLeaves, String role) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.joiningDate = joiningDate;
		this.hrId = hrId;
		this.password = password;
		this.adminId = adminId;
		this.deptName = deptName;
		this.leaveTaken = leaveTaken;
		this.leaveRemaining = leaveRemaining;
		this.totalLeaves = totalLeaves;
		this.role = role;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public LocalDate getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	public int getHrId() {
		return hrId;
	}


	public void setHrId(int hrId) {
		this.hrId = hrId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public int getLeaveTaken() {
		return leaveTaken;
	}


	public void setLeaveTaken(int leaveTaken) {
		this.leaveTaken = leaveTaken;
	}


	public int getLeaveRemaining() {
		return leaveRemaining;
	}


	public void setLeaveRemaining(int leaveRemaining) {
		this.leaveRemaining = leaveRemaining;
	}


	public int getTotalLeaves() {
		return totalLeaves;
	}


	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	
	
	
}
