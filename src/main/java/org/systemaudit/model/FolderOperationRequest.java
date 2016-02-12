/**
 * 
 */
package org.systemaudit.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "FOLDER_OPERATION_REQUEST")
public class FolderOperationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOLD_ID", nullable = false)
	private int foldId;

	@Size(max = 1500)
	@NotBlank
	@Column(name = "FOLD_FULL_PATH", nullable = false)
	private String foldFullPath;

	@Column(name = "FOLD_STATUS", nullable = false, columnDefinition = "character varying(15) default 'GOOD'")
	@Enumerated(EnumType.STRING)
	private EnumFileFolderOperationStatus foldStatus = EnumFileFolderOperationStatus.GOOD;

	@Column(name = "FOLD_OPERATION_REQUESTED_BY", nullable = false)
	@Size(max = 15)
	private String foldOperationRequestedBy;

	@Column(name = "FOLD_OPERATION_REQUESTED_DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date foldOperationRequestedDatetime;

	@ManyToOne
	@JoinColumn(name = "FOLD_COMP_ID", referencedColumnName = "COMP_ID")
	private DeviceInfo objDeviceInfo;

	@ManyToOne
	@JoinColumn(name = "FOLD_SCH_ID", referencedColumnName = "SCH_ID")
	private ScheduleMaster objScheduleMaster;

	/**
	 * @return the foldId
	 */
	public int getFoldId() {
		return foldId;
	}

	/**
	 * @param foldId
	 *            the foldId to set
	 */
	public void setFoldId(int foldId) {
		this.foldId = foldId;
	}

	/**
	 * @return the foldFullPath
	 */
	public String getFoldFullPath() {
		return foldFullPath;
	}

	/**
	 * @param foldFullPath
	 *            the foldFullPath to set
	 */
	public void setFoldFullPath(String foldFullPath) {
		this.foldFullPath = foldFullPath;
	}

	/**
	 * @return the foldStatus
	 */
	public EnumFileFolderOperationStatus getFoldStatus() {
		return foldStatus;
	}

	/**
	 * @param foldStatus
	 *            the foldStatus to set
	 */
	public void setFoldStatus(EnumFileFolderOperationStatus foldStatus) {
		this.foldStatus = foldStatus;
	}

	/**
	 * @return the foldOperationRequestedBy
	 */
	public String getFoldOperationRequestedBy() {
		return foldOperationRequestedBy;
	}

	/**
	 * @param foldOperationRequestedBy
	 *            the foldOperationRequestedBy to set
	 */
	public void setFoldOperationRequestedBy(String foldOperationRequestedBy) {
		this.foldOperationRequestedBy = foldOperationRequestedBy;
	}

	/**
	 * @return the foldOperationRequestedDatetime
	 */
	public Date getFoldOperationRequestedDatetime() {
		return foldOperationRequestedDatetime;
	}

	/**
	 * @param foldOperationRequestedDatetime
	 *            the foldOperationRequestedDatetime to set
	 */
	public void setFoldOperationRequestedDatetime(Date foldOperationRequestedDatetime) {
		this.foldOperationRequestedDatetime = foldOperationRequestedDatetime;
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
	 * @return the objScheduleMaster
	 */
	public ScheduleMaster getObjScheduleMaster() {
		return objScheduleMaster;
	}

	/**
	 * @param objScheduleMaster
	 *            the objScheduleMaster to set
	 */
	public void setObjScheduleMaster(ScheduleMaster objScheduleMaster) {
		this.objScheduleMaster = objScheduleMaster;
	}

	@Override
	public String toString() {
		return "FolderOperationRequest [foldId=" + foldId + ", foldFullPath=" + foldFullPath + ", foldStatus="
				+ foldStatus + ", foldOperationRequestedBy=" + foldOperationRequestedBy
				+ ", foldOperationRequestedDatetime=" + foldOperationRequestedDatetime + ", objScheduleMaster="
				+ objScheduleMaster + "]";
	}
}
