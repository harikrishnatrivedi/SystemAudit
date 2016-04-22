/**
 * 
 */
package org.systemaudit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Column(name = "SCH_ACTUAL_RUN_DATETIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schActualRunDateTime;

	@Column(name = "SCH_SCHEDULED_DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schScheduledDateTime;

	@Column(name = "SCH_CREATED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schCreatedDate;

	@Column(name = "SCH_CREATED_BY", nullable = true)
	@Size(max = 15)
	private String schCreatedBy;

	
	@Column(name = "SCH_STATUS", nullable = false, columnDefinition = "character varying(15) default 'PENDING'")
	@Enumerated(EnumType.STRING)
	private EnumScheduleStatus schStatus = EnumScheduleStatus.PENDING;

	@ManyToOne
	@JoinColumn(name = "SCH_COMP_ID", referencedColumnName = "COMP_ID")
	private DeviceInfo objDeviceInfo;

	@ManyToOne
	@JoinColumn(name = "SCH_GRP_ID", referencedColumnName = "GRP_ID")
	private DeviceGroup objDeviceGroup;

	@OneToMany(mappedBy = "objScheduleMaster")
	private List<FileDetails> lstObjFileDetails;

	@OneToMany(mappedBy = "objScheduleMaster")
	private List<FolderOperationRequest> lstObjFolderOperationRequest;

	
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
	 * @return the schActualRunDateTime
	 */
	public Date getSchActualRunDateTime() {
		return schActualRunDateTime;
	}

	/**
	 * @param schActualRunDateTime
	 *            the schActualRunDateTime to set
	 */
	public void setSchActualRunDateTime(Date schActualRunDateTime) {
		this.schActualRunDateTime = schActualRunDateTime;
	}

	/**
	 * @return the schScheduledDateTime
	 */
	public Date getSchScheduledDateTime() {
		return schScheduledDateTime;
	}

	/**
	 * @param schScheduledDateTime
	 *            the schScheduledDateTime to set
	 */
	public void setSchScheduledDateTime(Date schScheduledDateTime) {
		this.schScheduledDateTime = schScheduledDateTime;
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
	 * @return the schScheduleStatus
	 */
	public EnumScheduleStatus getSchStatus() {
		return schStatus;
	}

	/**
	 * @param schScheduleStatus
	 *            the schScheduleStatus to set
	 */
	public void setSchStatus(EnumScheduleStatus schStatus) {
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
		return "objDeviceInfo [schId=" + schId + ", schActualRunDateTime=" + schActualRunDateTime
				+ ", schScheduledDateTime=" + schScheduledDateTime + ", schCreatedDate=" + schCreatedDate
				+ ", schCreatedBy=" + schCreatedBy + ", objDeviceInfo=" + objDeviceInfo + ", schStatus="+schStatus
				/* + ", lstObjFileDetails=" + lstObjFileDetails */ + ", objDeviceGroup=" + objDeviceGroup + "]";
	}
}
