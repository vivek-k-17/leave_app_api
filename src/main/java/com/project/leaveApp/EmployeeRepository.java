package com.project.leaveApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Employee findByEmailIdAndPassword(String emailId, String password);
//	public Employee findByEmailId(String emailId);
	
//	@Query(value="select e.firstName,e.lastName,d.departmentName,sum(l.leaveTaken+l.leaveRemaining) as "+"totalLeaves"+"sum(l.leavesTaken) as"+"TotalLeaveTaken"+"from Employee e join Department d on e.departmentId=d.departmentId join LeaveRecord l on e.empId=l.empId")
	
	public Employee findById(int empId);
	public Employee findByDeptNameAndRole(String deptName,String role);
	

//	public void saveAll(LeaveRecord lr);

}
