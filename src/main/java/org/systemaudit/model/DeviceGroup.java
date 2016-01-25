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

	@Column(name = "GRP_NAME", nullable = false)
	@Size(max = 8)
	private String grpName;

	@Column(name = "GRP_DESCRIPTION", nullable = true)
	@Size(max = 150)
	private String grpDescription;

	@OneToMany(mappedBy = "objDeviceGroup")
	private List<DeviceInfo> lstObjDeviceInfo;

	@OneToMany(mappedBy = "objScheduleMaster")
	private List<ScheduleMaster> lstObjScheduleMaster;

	@Override
	public String toString() {
		return "objDeviceInfo [grpId=" + grpId + ", grpName=" + grpName + ", grpDescription=" + grpDescription
				+ ", lstObjDeviceInfo=" + lstObjDeviceInfo + ", lstObjScheduleMaster=" + lstObjScheduleMaster + "]";
	}
}
