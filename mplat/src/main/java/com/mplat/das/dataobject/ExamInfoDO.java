/**
 * obullxl@gmail.com
 */
package com.mplat.das.dataobject;

// auto generated imports

/**
 * A data object class directly models database table <tt>mplat_exam_info</tt>.
 */
public class ExamInfoDO extends BaseDO {
    private static final long serialVersionUID = 741231858441822688L;

	/** column:id */
	private long id;
	/** column:title */
	private String title;
	/** column:rgt_no */
	private String rgtNo;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRgtNo() {
		return rgtNo;
	}

	public void setRgtNo(String rgtNo) {
		this.rgtNo = rgtNo;
	}

}
