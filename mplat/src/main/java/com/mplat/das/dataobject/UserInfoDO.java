/**
 * obullxl@gmail.com
 */
package com.mplat.das.dataobject;

// auto generated imports

/**
 * A data object class directly models database table <tt>mplat_user_info</tt>.
 */
public class UserInfoDO extends BaseDO {
    private static final long serialVersionUID = 741231858441822688L;

	/** column:id */
	private long id;
	/** column:usr_name */
	private String usrName;
	/** column:usr_passwd */
	private String usrPasswd;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPasswd() {
		return usrPasswd;
	}

	public void setUsrPasswd(String usrPasswd) {
		this.usrPasswd = usrPasswd;
	}

}
