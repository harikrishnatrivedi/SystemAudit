/**
 * 
 */
package org.systemaudit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "DEVICE_GROUP")
public class DeviceGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRP_ID", nullable = false)
	private int grpId;

	@Column(unique=true, name = "GRP_NAME", nullable = false)
	@NotBlank
	@Size(max = 50)
	private String grpName;

	@Column(name = "GRP_DESCRIPTION", nullable = true)
	@Size(max = 150)
	private String grpDescription;

	@OneToMany(mappedBy = "objDeviceGroup")
	private List<DeviceInfo> lstObjDeviceInfo;

	@OneToMany(mappedBy = "objDeviceGroup")
	private List<ScheduleMaster> lstObjScheduleMaster;

	/**
	 * @return the grpId
	 */
	public int getGrpId() {
		return grpId;
	}

	/**
	 * @param grpId
	 *            the grpId to set
	 */
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	/**
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * @param grpName
	 *            the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * @return the grpDescription
	 */
	public String getGrpDescription() {
		return grpDescription;
	}

	/**
	 * @param grpDescription
	 *            the grpDescription to set
	 */
	public void setGrpDescription(String grpDescription) {
		this.grpDescription = grpDescription;
	}

	/**
	 * @return the lstObjDeviceInfo
	 */
	public List<DeviceInfo> getLstObjDeviceInfo() {
		return lstObjDeviceInfo;
	}

	/**
	 * @param lstObjDeviceInfo
	 *            the lstObjDeviceInfo to set
	 */
	public void setLstObjDeviceInfo(List<DeviceInfo> lstObjDeviceInfo) {
		this.lstObjDeviceInfo = lstObjDeviceInfo;
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

	@Override
	public String toString() {
		return "objDeviceInfo [grpId=" + grpId + ", grpName=" + grpName + ", grpDescription=" + grpDescription
				+ ", lstObjDeviceInfo=" + lstObjDeviceInfo + ", lstObjScheduleMaster=" + lstObjScheduleMaster + "]";
	}
}
