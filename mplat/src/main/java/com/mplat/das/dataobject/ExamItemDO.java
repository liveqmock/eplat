/**
 * obullxl@gmail.com
 */
package com.mplat.das.dataobject;

// auto generated imports

/**
 * A data object class directly models database table <tt>mplat_exam_item</tt>.
 */
public class ExamItemDO extends BaseDO {
    private static final long serialVersionUID = 741231858441822688L;

	/** column:id */
	private long id;
	/** column:no */
	private String no;
	/** column:exm_id */
	private long exmId;
	/** column:text */
	private String text;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public long getExmId() {
		return exmId;
	}

	public void setExmId(long exmId) {
		this.exmId = exmId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
