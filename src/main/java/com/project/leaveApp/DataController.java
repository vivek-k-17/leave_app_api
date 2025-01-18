package com.project.leaveApp;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// leave appp
@RestController
@CrossOrigin(value ="http://localhost:4200")
public class DataController {
	
	@Autowired
	 EmployeeRepository repo;
	@Autowired
	LeaveRepository leave;
	
	@GetMapping("/login/{emailId}/{password}") //login page
    public Employee loginEmployee(@PathVariable String emailId,@PathVariable String password) {

        Employee e1 = repo.findByEmailIdAndPassword(emailId, password);
        
        if (e1 != null) {
        	
            return e1;
        	
        } else {
            return null;
        }
    }
	
//	@PostMapping("/login")
//	public String login(@RequestBody Employee obj) {
//	    // Validate if emailId or password is null
//	    if (obj == null || obj.getEmailId() == null || obj.getPassword() == null) {
//	        return "Email or password cannot be null";
//	    }
//	    
//	    // Check if the role is Admin
//	    if ("Admin".equals(obj.getRole())) {
//	        return repo.findByEmailIdAndPassword(obj.getEmailId(), obj.getPassword()) != null 
//	            ? "Admin" 
//	            : "Admin Login Unsuccessful";
//	    }
//	    System.out.println(obj.getRole());
//	
//	    // Check if the role is HR
//	    if ("Hr".equals(obj.getRole())) {
//	        return repo.findByEmailIdAndPassword(obj.getEmailId(), obj.getPassword()) != null 
//	            ? "Hr login" 
//	            : "HR Login Unsuccessful";
//	    }
//	
//	    // Default case: Employee role
//	    return repo.findByEmailIdAndPassword(obj.getEmailId(), obj.getPassword()) != null 
//	        ? "Login Successful" 
//	        : "Login Unsuccessful";
//	}
	    
	
	@PostMapping("/addEmployee")  //Add employee
	public String addEmployee(@RequestBody Employee employee) {
	    // Save the employee to the database
		employee.setTotalLeaves(20);
		employee.setLeaveRemaining(20);
	
		String role = employee.getRole().toUpperCase();
		System.out.println(role);

		    switch (role) {
		        case "ADMIN":
		            Employee admin= repo.findByDeptNameAndRole(employee.getDeptName(), "Admin");
		            if(admin!=null)
		            {
		            	throw new RuntimeException(" admin found for the department: " + employee.getDeptName());
		            }

		            // For admins, set hrId and adminId to 0
		            employee.setHrId(0); // No HR for admin
		            employee.setAdminId(0); // No higher admin
		            break;

		        case "HR":
		        	Employee hr= repo.findByDeptNameAndRole(employee.getDeptName(), "Hr");
		            if(hr!=null)
		            {
		            	throw new RuntimeException(" hr found for the department: " + employee.getDeptName());
		            }
		            // For HRs, set hrId to 0 and adminId based on department
		            Employee adminForHR = repo.findByDeptNameAndRole(employee.getDeptName(), "Admin");
		            
		            if(adminForHR!= null)
		            {
		            	 employee.setHrId(0); // No HR for HR
				            employee.setAdminId(adminForHR.getEmpId()); // Set admin ID based on department
				            
		            }
		            else {
		            	throw new RuntimeException("No admin found for the department: " + employee.getDeptName());
		            }
		            break;

		        case "EMPLOYEE":
		            // For employees, set hrId and adminId based on department
		            Employee hrForEmployee = repo.findByDeptNameAndRole(employee.getDeptName(), "Hr");
		            if(hrForEmployee!=null)
		            {
		            	employee.setHrId(hrForEmployee.getEmpId()); // Set HR ID for employee
			            employee.setAdminId(hrForEmployee.getAdminId()); // Set Admin ID based on HR's admin ID
			            
		            }
		            else
		            {
		            	throw new RuntimeException("No hr found for the department: " + employee.getDeptName());
		            }
		            break;

		        default:
		            throw new RuntimeException("Invalid role: " + role);
		    }

	    repo.save(employee);
	
	    System.out.println(employee.empId);
//	    return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
	    return "Employee added successfully";
	    
	   
	}
	
	@PostMapping("/leave")  //Add leave 
    public String requestLeave(@RequestBody LeaveRecord leaveReq) {
		// Calculate remaining leaves
        int leaveDays = Period.between(leaveReq.getStartDate(), leaveReq.getEndDate()).getDays() + 1;
        
            String l_Type = leaveReq.getLeaveType().toUpperCase();

            // Define total leaves for each type dynamically
            int totalLeaves;
            System.out.println(l_Type);
            switch (l_Type) {
                case "WEDDING LEAVE":
                    totalLeaves = 4; // Example: 20 days for annual leave
                    break;
                case "SICK LEAVE":
                    totalLeaves = 4; // Example: 15 days for sick leave
                    break;
                case "DEATH LEAVE":
                    totalLeaves = 4; // Example: 10 days for casual leave
                    break;
                case "PATTERNITY LEAVE":
                    totalLeaves = 4; // Example: 10 days for casual leave
                    break;
                case "MATTERNITY LEAVE":
                    totalLeaves = 4; // Example: 10 days for casual leave
                    break;
                case "MENSTRUAL LEAVE":
                    totalLeaves = 12; // Example: 10 days for casual leave
                    break;
                case "PARENTING LEAVE":
                    totalLeaves = 4; // Example: 10 days for casual leave
                    break;
                case "EARNED LEAVES":
                    totalLeaves = 12; // Example: 10 days for casual leave
                    break;
                case "BUFFER LEAVES":
                    totalLeaves = 6; // Example: 10 days for casual leave
                    break;
                    
                default:
                    throw new RuntimeException("Invalid leave type");
            }

            // Fetch all leave requests of the employee for this leave type
            List<LeaveRecord> existingLeaves = leave.findByEmployeeAndLeaveTypeAndStatus(
                    leaveReq.employee, l_Type, "APPROVED"
            );

            int takenLeaves = 0;

            // Calculate taken leaves for this leave type
            for (LeaveRecord existingLeave : existingLeaves) {
                takenLeaves += existingLeave.getLeavesAcquired();
            }

            int remainingLeaves = totalLeaves - takenLeaves;

            // Check if there are enough remaining leaves
            if (remainingLeaves < leaveDays) {
                return "Insufficient leave balance for " + l_Type;
            }
            //maintain annual leave
            if (leaveReq.employee.getLeaveRemaining() < leaveDays) {
                return "Insufficient leave balance";
             }

            leaveReq.employee.setLeaveTaken(leaveReq.employee.getLeaveTaken() + leaveDays);
            leaveReq.employee.setLeaveRemaining(leaveReq.employee.getTotalLeaves() - leaveReq.employee.getLeaveTaken());
             
            repo.save(leaveReq.employee);
     		

            // Update the leave request object
            leaveReq.setMaximumDays(totalLeaves);
            leaveReq.setLeavesAcquired(takenLeaves + leaveDays);
            leaveReq.setLeavesLeft(totalLeaves - leaveReq.getLeavesAcquired());
            leaveReq.setStatus("PENDING");
            leaveReq.setApprovedBy("Can't Approve");
            leave.save(leaveReq);
            System.out.println(leaveReq.employee);
            return "leave added succesfully";

        }
        
    
    @GetMapping("/leavesTable/{empId}")  //show leaves table on employee dashboard
    public List<LeaveRecord> getLeave(@PathVariable int empId) {
    	System.out.println(empId);
    	Employee e1=repo.findById(empId);
        return   leave.findByEmployee(e1);
    }
	
    @GetMapping("/employeeTable")  //show employee table on hr dashboard
    public List<LeaveRecord> getAllLeaves()
    {
    	return leave.findAll();
    }
    
    @GetMapping("/totalTable")  //show total reaves table on project manager dashboard
    public List<LeaveRecord> getTeamLeaves()
    {
    	return leave.findAll();
    }
	
	@PostMapping("/approve/{lId}/{role}") //approve leaves by project manager
	public LeaveRecord approveLeave(@PathVariable int lId,@PathVariable String role)
	{
		System.out.println(lId);
		LeaveRecord lr=leave.findByLId(lId);
		System.out.println(lr);
		lr.setStatus("Approve");
		lr.setApprovedBy(role);
		return leave.save(lr);
	}
	
	@PostMapping("/reject/{lId}/{role}")  //reject leaves by project manager
	public LeaveRecord rejectLeave(@PathVariable int lId,@PathVariable String role)
	{
		LeaveRecord lr=leave.findByLId(lId);
		lr.setStatus("Reject");
		lr.setApprovedBy(role);
		return leave.save(lr);
	}
	
	@GetMapping("/approveTable")   //show approve leaves table on project manager dashboard
	public List<LeaveRecord> showApprove()
	{
		List<LeaveRecord> ls=leave.findByStatus("Approve");
		
		return ls;
	}
	
	@GetMapping("/rejectTable")   //show reject leaves table on project manager dashboard
	public List<LeaveRecord> showReject()
	{
		List<LeaveRecord> ls=leave.findByStatus("Reject");
		
		return ls;
	}
	
	 @GetMapping("/pendingTable")   //show pending leaves table on project manager dashboard
	    public List<LeaveRecord> pendingLeaves()
	    {
		 List<LeaveRecord> ls=leave.findByStatus("PENDING");
		 return ls;
	    }
	
		
	}


