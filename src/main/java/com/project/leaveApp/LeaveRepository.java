package com.project.leaveApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRepository extends JpaRepository<LeaveRecord,Integer> {
	
//	public List<LeaveRecord> findByEmployee(int empId);
	
	// Custom query to fetch leave records by employee ID
//    @Query(value="SELECT lr FROM LeaveRecord lr WHERE lr.employee.empId = :empId")
//    public List<LeaveRecord> findByEmpId(int empId);
    List<LeaveRecord> findByEmployee(Employee emp);
    public LeaveRecord findByLId(int lId);
    public List<LeaveRecord> findByStatus(String status);
    
  public List<LeaveRecord>  findByEmployeeAndLeaveTypeAndStatus(Employee employee,String leaveType,String status);
}
