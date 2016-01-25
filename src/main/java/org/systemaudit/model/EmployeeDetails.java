/**
 * 
 */
package org.systemaudit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID", nullable = false)
	private int empId;

	@Size(max = 10)
	@Column(name = "EMP_CODE", nullable = true)
	private String empCode;

	@Size(max = 50)
	@NotBlank
	@Column(name = "EMP_NAME", nullable = false)
	private String empLoginName;

	@Size(max = 15)
	@NotBlank
	@Column(name = "EMP_PASSWORD", nullable = false)
	private String empPassword;

	@Column(name = "EMP_LOCATION", nullable = true)
	@Size(max = 30)
	private String empLocation;

	@Column(name = "EMP_DESIGNATION", nullable = true)
	@Size(max = 30)
	private String empDesignation;

	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empLoginName
	 */
	public String getEmpLoginName() {
		return empLoginName;
	}

	/**
	 * @param empLoginName
	 *            the empLoginName to set
	 */
	public void setEmpLoginName(String empLoginName) {
		this.empLoginName = empLoginName;
	}

	/**
	 * @return the empPassword
	 */
	public String getEmpPassword() {
		return empPassword;
	}

	/**
	 * @param empPassword
	 *            the empPassword to set
	 */
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	/**
	 * @return the empLocation
	 */
	public String getEmpLocation() {
		return empLocation;
	}

	/**
	 * @param empLocation
	 *            the empLocation to set
	 */
	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}

	/**
	 * @return the empDesignation
	 */
	public String getEmpDesignation() {
		return empDesignation;
	}

	/**
	 * @param empDesignation
	 *            the empDesignation to set
	 */
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	@Override
	public String toString() {
		return "objEmployee [empId=" + empId + ", empDesignation=" + empDesignation + ", empLoginName=" + empLoginName
				+ ", empPassword=" + empPassword + ", empLocation=" + empLocation + "]";
	}
}
