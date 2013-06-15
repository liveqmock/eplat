/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ToString;

/**
 * 模拟人发音
 * 
 * @author obullxl@gmail.com
 * @version $Id: SpeakDTO.java, V1.0.1 2013-6-15 下午4:46:23 $
 */
public class SpeakDTO extends ToString {
    private static final long serialVersionUID = 2724909765444114813L;

    /** 代码 */
    private String            code;

    /** 描述 */
    private String            text;

    /** 扩展参数 */
    private final MapExt      ext              = new MapExt();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MapExt getExt() {
        return ext;
    }

}
