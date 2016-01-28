/**
 * 
 */
package org.systemaudit.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "SCHEDULE_MASTER")
public class ScheduleMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCH_ID", nullable = false)
	private int schId;

	@Column(name = "SCH_RUN_DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schRunDateTime;

	@Column(name = "SCH_CREATED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schCreatedDate;

	@Column(name = "SCH_CREATED_BY", nullable = true)
	@Size(max = 15)
	private String schCreatedBy;

	@Column(name = "SCH_STATUS", nullable = true)
	@Size(max = 1)
	private String schStatus;

	@ManyToOne
	@JoinColumn(name = "SCH_COMP_ID", referencedColumnName = "COMP_ID")
	private DeviceInfo objDeviceInfo;

	@ManyToOne
	@JoinColumn(name = "SCH_GRP_ID", referencedColumnName = "GRP_ID")
	private DeviceGroup objDeviceGroup;

	@OneToMany(mappedBy = "objScheduleMaster")
	private List<FileDetails> lstObjFileDetails;

	/**
	 * @return the schId
	 */
	public int getSchId() {
		return schId;
	}

	/**
	 * @param schId
	 *            the schId to set
	 */
	public void setSchId(int schId) {
		this.schId = schId;
	}

	/**
	 * @return the schRunDateTime
	 */
	public Date getSchRunDateTime() {
		return schRunDateTime;
	}

	/**
	 * @param schRunDateTime
	 *            the schRunDateTime to set
	 */
	public void setSchRunDateTime(Date schRunDateTime) {
		this.schRunDateTime = schRunDateTime;
	}

	/**
	 * @return the schCreatedDate
	 */
	public Date getSchCreatedDate() {
		return schCreatedDate;
	}

	/**
	 * @param schCreatedDate
	 *            the schCreatedDate to set
	 */
	public void setSchCreatedDate(Date schCreatedDate) {
		this.schCreatedDate = schCreatedDate;
	}

	/**
	 * @return the schCreatedBy
	 */
	public String getSchCreatedBy() {
		return schCreatedBy;
	}

	/**
	 * @param schCreatedBy
	 *            the schCreatedBy to set
	 */
	public void setSchCreatedBy(String schCreatedBy) {
		this.schCreatedBy = schCreatedBy;
	}

	/**
	 * @return the schStatus
	 */
	public String getSchStatus() {
		return schStatus;
	}

	/**
	 * @param schStatus
	 *            the schStatus to set
	 */
	public void setSchStatus(String schStatus) {
		this.schStatus = schStatus;
	}

	/**
	 * @return the objDeviceInfo
	 */
	public DeviceInfo getObjDeviceInfo() {
		return objDeviceInfo;
	}

	/**
	 * @param objDeviceInfo
	 *            the objDeviceInfo to set
	 */
	public void setObjDeviceInfo(DeviceInfo objDeviceInfo) {
		this.objDeviceInfo = objDeviceInfo;
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
		return "objDeviceInfo [schId=" + schId + ", schRunDateTime=" + schRunDateTime + ", schCreatedDate="
				+ schCreatedDate + ", schCreatedBy=" + schCreatedBy + ", objDeviceInfo=" + objDeviceInfo
				/*+ ", lstObjFileDetails=" + lstObjFileDetails*/ + ", objDeviceGroup=" + objDeviceGroup + "]";
	}
}
