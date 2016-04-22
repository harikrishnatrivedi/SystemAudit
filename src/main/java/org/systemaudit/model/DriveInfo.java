/**
 * 
 */
package org.systemaudit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "DRIVE_INFO")
public class DriveInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRV_ID", nullable = false)
	private int drvId;

	@Size(max = 5)
	@NotBlank
	@Column(name = "DRV_LETTER", nullable = false)
	private String drvLetter;

	@Size(max = 50)
	@Column(name = "DRV_TYPE", nullable = false)
	private String drvType;

	@Column(name = "DRV_TOTAL_SPACE", nullable = false)
	private long drvTotalSpace;

	@Column(name = "DRV_FREE_SPACE", nullable = true)
	private long drvFreeSpace;

	@Column(name = "DRV_USABLE_SPACE", nullable = true)
	private long drvUsableSpace;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DRV_COMP_ID", referencedColumnName = "COMP_ID")
	private DeviceInfo objDeviceInfo;

	/**
	 * @return the drvId
	 */
	public int getDrvId() {
		return drvId;
	}

	/**
	 * @param drvId
	 *            the drvId to set
	 */
	public void setDrvId(int drvId) {
		this.drvId = drvId;
	}

	/**
	 * @return the drvLetter
	 */
	public String getDrvLetter() {
		return drvLetter;
	}

	/**
	 * @param drvLetter
	 *            the drvLetter to set
	 */
	public void setDrvLetter(String drvLetter) {
		this.drvLetter = drvLetter;
	}

	/**
	 * @return the drvType
	 */
	public String getDrvType() {
		return drvType;
	}

	/**
	 * @param drvType
	 *            the drvType to set
	 */
	public void setDrvType(String drvType) {
		this.drvType = drvType;
	}

	
	/**
	 * @return the drvTotalSpace
	 */
	public long getDrvTotalSpace() {
		return drvTotalSpace;
	}

	/**
	 * @param drvTotalSpace the drvTotalSpace to set
	 */
	public void setDrvTotalSpace(long drvTotalSpace) {
		this.drvTotalSpace = drvTotalSpace;
	}

	/**
	 * @return the drvFreeSpace
	 */
	public long getDrvFreeSpace() {
		return drvFreeSpace;
	}

	/**
	 * @param drvFreeSpace the drvFreeSpace to set
	 */
	public void setDrvFreeSpace(long drvFreeSpace) {
		this.drvFreeSpace = drvFreeSpace;
	}

	/**
	 * @return the drvUsableSpace
	 */
	public long getDrvUsableSpace() {
		return drvUsableSpace;
	}

	/**
	 * @param drvUsableSpace the drvUsableSpace to set
	 */
	public void setDrvUsableSpace(long drvUsableSpace) {
		this.drvUsableSpace = drvUsableSpace;
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
		return "objFileDetails [drvId=" + drvId + ", drvLetter=" + drvLetter + ", drvTotalSpace=" + drvTotalSpace
				+ ", drvFreeSpace=" + drvFreeSpace + ", drvUsableSpace=" + drvUsableSpace + ", objDeviceInfo="
				+ objDeviceInfo + "]";
	}
}
