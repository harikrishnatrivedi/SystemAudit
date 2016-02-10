/**
 * 
 */
package org.systemaudit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author harikrishna.trivedi
 *
 */

@Entity
@Table(name = "KEY_VALUE")
public class KeyValue {

	@Id
	@Column(name = "KVAL_ID", nullable = false)
	@Size(max = 20)
	private String kvalId;

	@Size(max = 500)
	@Column(unique = true, name = "KVAL_VALUE", nullable = true)
	private String kvalValue;

	/**
	 * @return the kvalId
	 */
	public String getKvalId() {
		return kvalId;
	}

	/**
	 * @param kvalId
	 *            the kvalId to set
	 */
	public void setKvalId(String kvalId) {
		this.kvalId = kvalId;
	}

	/**
	 * @return the kvalValue
	 */
	public String getKvalValue() {
		return kvalValue;
	}

	/**
	 * @param kvalValue
	 *            the kvalValue to set
	 */
	public void setKvalValue(String kvalValue) {
		this.kvalValue = kvalValue;
	}

	@Override
	public String toString() {
		return " KeyValue [kvalId=" + kvalId + ", kvalValue=" + kvalValue + "]";
	}
}
