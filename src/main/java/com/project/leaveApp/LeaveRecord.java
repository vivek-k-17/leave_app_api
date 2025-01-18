package com.project.leaveApp;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="LeaveRecord")
public class LeaveRecord {
//	@Id
//	@Column(name="leaveId")
//	String leaveId;
//	@ManyToOne
//    @JoinColumn(name = "empId", referencedColumnName = "empId", insertable = false, updatable = false)
//    private Employee employee;
//	@ManyToOne
//    @JoinColumn(name = "leaveTypeId", referencedColumnName = "leaveTypeId", insertable = false, updatable = false)
//    private LeaveType leaveType;
//	@Column(name="startingDate")
//	LocalDate startingDate;
//	@Column(name="endingDate")
//	LocalDate endingDate;
//	@Column(name="reason")
//	String reason;
//	@Column(name="status")
//	String status;
//	@Column(name="appliedDate")
//	LocalDate appliedDate;
//	@Column(name="approvedBy")
//	String approvedBy;
//	@Column(name="leaveTaken")
//	int leaveTaken;
//	@Column(name="leaveRemaining")
//	int leaveRemaining;
//	
//	 public LeaveRecord() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public LeaveRecord(String leaveId, Employee employee, LeaveType leaveType, LocalDate startingDate,
//			LocalDate endingDate, String reason, String status, LocalDate appliedDate, String approvedBy,
//			int leaveTaken, int leaveRemaining) {
//		super();
//		this.leaveId = leaveId;
//		this.employee = employee;
//		this.leaveType = leaveType;
//		this.startingDate = startingDate;
//		this.endingDate = endingDate;
//		this.reason = reason;
//		this.status = status;
//		this.appliedDate = appliedDate;
//		this.approvedBy = approvedBy;
//		this.leaveTaken = leaveTaken;
//		this.leaveRemaining = leaveRemaining;
//	}
//
//	public String getLeaveId() {
//		return leaveId;
//	}
//
//	public void setLeaveId(String leaveId) {
//		this.leaveId = leaveId;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	public LeaveType getLeaveType() {
//		return leaveType;
//	}
//
//	public void setLeaveType(LeaveType leaveType) {
//		this.leaveType = leaveType;
//	}
//
//	public LocalDate getStartingDate() {
//		return startingDate;
//	}
//
//	public void setStartingDate(LocalDate startingDate) {
//		this.startingDate = startingDate;
//	}
//
//	public LocalDate getEndingDate() {
//		return endingDate;
//	}
//
//	public void setEndingDate(LocalDate endingDate) {
//		this.endingDate = endingDate;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public LocalDate getAppliedDate() {
//		return appliedDate;
//	}
//
//	public void setAppliedDate(LocalDate appliedDate) {
//		this.appliedDate = appliedDate;
//	}
//
//	public String getApprovedBy() {
//		return approvedBy;
//	}
//
//	public void setApprovedBy(String approvedBy) {
//		this.approvedBy = approvedBy;
//	}
//
//	public int getLeaveTaken() {
//		return leaveTaken;
//	}
//
//	public void setLeaveTaken(int leaveTaken) {
//		this.leaveTaken = leaveTaken;
//	}
//
//	public int getLeaveRemaining() {
//		return leaveRemaining;
//	}
//
//	public void setLeaveRemaining(int leaveRemaining) {
//		this.leaveRemaining = leaveRemaining;
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="lId")
	int lId;
	@ManyToOne
	@JoinColumn(name="empId")
	Employee employee;
	@Column(name="startDate")
	LocalDate startDate;
	@Column(name="endDate")
	LocalDate endDate;
	@Column(name="leaveType")
	String leaveType;
	@Column(name="reason")
	String reason;
	@Column(name="status")
	String status;
//	@Column(name="empId")
//	int empId;
	
	//leave type
	@Column(name="maximumDays")
	int maximumDays;
	
	//leave record
	@Column(name="appliedDate")
	LocalDate appliedDate;
	@Column(name="approvedBy")
	String approvedBy;
	@Column(name="leavesAcquired")
	int leavesAcquired;
	@Column(name="leavesLeft")
	int leavesLeft;
	
	public LeaveRecord() {
		// TODO Auto-generated constructor stub
	}

	public LeaveRecord(int lId, Employee employee, LocalDate startDate, LocalDate endDate, String leaveType,
			String reason, String status, int maximumDays, LocalDate appliedDate, String approvedBy, int leavesAcquired,
			int leavesLeft) {
		super();
		this.lId = lId;
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.reason = reason;
		this.status = status;
		this.maximumDays = maximumDays;
		this.appliedDate = appliedDate;
		this.approvedBy = approvedBy;
		this.leavesAcquired = leavesAcquired;
		this.leavesLeft = leavesLeft;
	}

	public int getlId() {
		return lId;
	}

	public void setlId(int lId) {
		this.lId = lId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMaximumDays() {
		return maximumDays;
	}

	public void setMaximumDays(int maximumDays) {
		this.maximumDays = maximumDays;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getLeavesAcquired() {
		return leavesAcquired;
	}

	public void setLeavesAcquired(int leavesAcquired) {
		this.leavesAcquired = leavesAcquired;
	}

	public int getLeavesLeft() {
		return leavesLeft;
	}

	public void setLeavesLeft(int leavesLeft) {
		this.leavesLeft = leavesLeft;
	}
	
	
	

}
