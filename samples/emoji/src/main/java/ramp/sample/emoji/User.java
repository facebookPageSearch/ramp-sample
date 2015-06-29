/**
 * 
 */
package ramp.sample.emoji;

import java.util.Date;

/**
 * @author Rama Palaniappan
 * @since 29-Jun-2015
 */
public class User {
	private Integer id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String loginName;
	private String avatar;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	private static String NEW_LINE = System.getProperty("line.separator");
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id:").append(id).append("; EmailId:").append(emailId)
				.append("; Avatar:").append(avatar).append(NEW_LINE);
		return sb.toString();
	}

}
