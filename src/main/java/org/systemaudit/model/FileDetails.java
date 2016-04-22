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
import org.joda.time.DateTime;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "FILE_DETAILS")
public class FileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", nullable = false)
	private int fileId;

	@Size(max = 1500)
	@NotBlank
	@Column(name = "FILE_FULL_PATH", nullable = false)
	private String fileFullPath;

	@Size(max = 1250)
	@NotBlank
	@Column(name = "FILE_FOLDER_PATH", nullable = false)
	private String fileFolderPath;

	@Size(max = 255)
	@NotBlank
	@Column(name = "FILE_NAME", nullable = false)
	private String fileName;

	@Size(max = 10)
	@Column(name = "FILE_EXTENSION", nullable = true)
	private String fileExtension;

	@Size(max = 5)
	@Column(name = "FILE_DRIVE", nullable = false)
	private String fileDrive;

	@Column(name = "FILE_SIZE", nullable = true)
	private long fileSize;

	@Column(name = "FILE_CREATION_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileCreationDate;

	@Column(name = "FILE_LAST_ACCESS_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileLastAccessDate;

	@Column(name = "FILE_LAST_MODIFIED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileLastModifiedDate;

	@Column(name = "FILE_STATUS", nullable = false, columnDefinition = "character varying(15) default 'GOOD'")
	@Enumerated(EnumType.STRING)
	private EnumFileFolderOperationStatus fileStatus = EnumFileFolderOperationStatus.GOOD;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FILE_COMP_ID", referencedColumnName = "COMP_ID")
	private DeviceInfo objDeviceInfo;

	@ManyToOne
	@JoinColumn(name = "FILE_SCH_ID", referencedColumnName = "SCH_ID")
	private ScheduleMaster objScheduleMaster;

	/**
	 * @return the fileCreationDate
	 */
	public Date getFileCreationDate() {
		return fileCreationDate;
	}

	/**
	 * @param fileCreationDate the fileCreationDate to set
	 */
	public void setFileCreationDate(Date fileCreationDate) {
		this.fileCreationDate = fileCreationDate;
	}

	/**
	 * @return the fileLastAccessDate
	 */
	public Date getFileLastAccessDate() {
		return fileLastAccessDate;
	}

	/**
	 * @param fileLastAccessDate the fileLastAccessDate to set
	 */
	public void setFileLastAccessDate(Date fileLastAccessDate) {
		this.fileLastAccessDate = fileLastAccessDate;
	}

	/**
	 * @return the fileLastModifiedDate
	 */
	public Date getFileLastModifiedDate() {
		return fileLastModifiedDate;
	}

	/**
	 * @param fileLastModifiedDate the fileLastModifiedDate to set
	 */
	public void setFileLastModifiedDate(Date fileLastModifiedDate) {
		this.fileLastModifiedDate = fileLastModifiedDate;
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

	/**
	 * @return the fileStatus
	 */
	public EnumFileFolderOperationStatus getFileStatus() {
		return fileStatus;
	}

	/**
	 * @param fileStatus
	 *            the fileStatus to set
	 */
	public void setFileStatus(EnumFileFolderOperationStatus fileStatus) {
		this.fileStatus = fileStatus;
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileFullPath
	 */
	public String getFileFullPath() {
		return fileFullPath;
	}

	/**
	 * @param fileFullPath
	 *            the fileFullPath to set
	 */
	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	/**
	 * @return the fileFolderPath
	 */
	public String getFileFolderPath() {
		return fileFolderPath;
	}

	/**
	 * @param fileFolderPath
	 *            the fileFolderPath to set
	 */
	public void setFileFolderPath(String fileFolderPath) {
		this.fileFolderPath = fileFolderPath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @param fileExtension
	 *            the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * @return the fileDrive
	 */
	public String getFileDrive() {
		return fileDrive;
	}

	/**
	 * @param fileDrive
	 *            the fileDrive to set
	 */
	public void setFileDrive(String fileDrive) {
		this.fileDrive = fileDrive;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
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

	@Override
	public String toString() {
		return "objFileDetails [fileId=" + fileId + ", fileFullPath=" + fileFullPath + ", fileFolderPath="
				+ fileFolderPath + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileExtension="
				+ fileExtension + ", fileDrive=" + fileDrive + ", objDeviceInfo=" + objDeviceInfo + "]";
	}
}
