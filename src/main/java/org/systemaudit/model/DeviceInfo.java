/**
 * 
 */
package org.systemaudit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "DEVICE_INFO")
public class DeviceInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMP_ID", nullable = false)
	private int compId;

	@Size(max = 30)
	@Column(unique = true, name = "COMP_NAME", nullable = false)
	private String compName;

	@Size(max = 150)
	@Column(name = "COMP_USER_NAME", nullable = true)
	private String compUserName;

	@Size(max = 150)
	@NotBlank
	@Column(name = "COMP_OS_NAME", nullable = false)
	private String compOsName;

	@Column(name = "COMP_PROCESSOR_TYPE", nullable = true)
	@Size(max = 150)
	private String compProcessorType;

	@OneToMany(mappedBy = "objDeviceInfo")
	@Column(name = "COMP_SCH_ID")
	private List<ScheduleMaster> lstObjScheduleMaster;

	@ManyToOne
	@JoinColumn(name = "COMP_GRP_ID", referencedColumnName = "GRP_ID")
	private DeviceGroup objDeviceGroup;

	@OneToMany(mappedBy = "objDeviceInfo",cascade = CascadeType.ALL)
	private List<FileDetails> lstObjFileDetails;

	@OneToMany(mappedBy = "objDeviceInfo",cascade = CascadeType.ALL)
	private List<DriveInfo> lstObjDriveInfo;

	/**
	 * @return the lstObjDriveInfo
	 */
	public List<DriveInfo> getLstObjDriveInfo() {
		return lstObjDriveInfo;
	}

	/**
	 * @param lstObjDriveInfo
	 *            the lstObjDriveInfo to set
	 */
	public void setLstObjDriveInfo(List<DriveInfo> lstObjDriveInfo) {
		this.lstObjDriveInfo = lstObjDriveInfo;
	}

	/**
	 * @return the compId
	 */
	public int getCompId() {
		return compId;
	}

	/**
	 * @param compId
	 *            the compId to set
	 */
	public void setCompId(int compId) {
		this.compId = compId;
	}

	/**
	 * @return the compName
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * @param compName
	 *            the compName to set
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	 * @return the compUserName
	 */
	public String getCompUserName() {
		return compUserName;
	}

	/**
	 * @param compUserName
	 *            the compUserName to set
	 */
	public void setCompUserName(String compUserName) {
		this.compUserName = compUserName;
	}

	/**
	 * @return the compOsName
	 */
	public String getCompOsName() {
		return compOsName;
	}

	/**
	 * @param compOsName
	 *            the compOsName to set
	 */
	public void setCompOsName(String compOsName) {
		this.compOsName = compOsName;
	}

	/**
	 * @return the compProcessorType
	 */
	public String getCompProcessorType() {
		return compProcessorType;
	}

	/**
	 * @param compProcessorType
	 *            the compProcessorType to set
	 */
	public void setCompProcessorType(String compProcessorType) {
		this.compProcessorType = compProcessorType;
	}

	/**
	 * @return the lstObjScheduleMaster
	 */
	public List<ScheduleMaster> getLstObjScheduleMaster() {
		return lstObjScheduleMaster;
	}

	/**
	 * @param lstObjScheduleMaster
	 *            the lstObjScheduleMaster to set
	 */
	public void setLstObjScheduleMaster(List<ScheduleMaster> lstObjScheduleMaster) {
		this.lstObjScheduleMaster = lstObjScheduleMaster;
	}

	/**
	 * @return the objDeviceGroup
	 */
	public DeviceGroup getObjDeviceGroup() {
		return objDeviceGroup;
	}

	/**
	 * @param objDeviceGroup
	 *            the objDeviceGroup to set
	 */
	public void setObjDeviceGroup(DeviceGroup objDeviceGroup) {
		this.objDeviceGroup = objDeviceGroup;
	}

	/**
	 * @return the lstObjFileDetails
	 */
	public List<FileDetails> getLstObjFileDetails() {
		return lstObjFileDetails;
	}

	/**
	 * @param lstObjFileDetails
	 *            the lstObjFileDetails to set
	 */
	public void setLstObjFileDetails(List<FileDetails> lstObjFileDetails) {
		this.lstObjFileDetails = lstObjFileDetails;
	}

	@Override
	public String toString() {
		return "objDeviceInfo [compId=" + compId + ", compName=" + compName + ", compUserName=" + compUserName
				+ ", compOsName=" + compOsName + ", compProcessorType=" + compProcessorType + ", objDeviceGroup="
				+ objDeviceGroup + /*", lstObjFileDetails=" + lstObjFileDetails + ", lstObjScheduleMaster="
				+ lstObjScheduleMaster + */"]";
	}
}
