package com.bank.model;

public class Employee {
	private String empId;
	private String empPassword;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
		
	public Employee(String empId, String empPassword) {
		super();
		this.empId = empId;
		this.empPassword = empPassword;
	}

	public String getEmployeeId() {
		return empId;
	}
	public void setEmployeeId(String employeeId) {
		this.empId = employeeId;
	}
	public String getEmployeePassword() {
		return empPassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.empPassword = employeePassword;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empPassword == null) ? 0 : empPassword.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empPassword == null) {
			if (other.empPassword != null)
				return false;
		} else if (!empPassword.equals(other.empPassword))
			return false;
		return true;
	}
	
	

}
