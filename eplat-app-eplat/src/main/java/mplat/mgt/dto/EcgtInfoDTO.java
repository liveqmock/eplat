/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ids.LongID;

/**
 * 心律试题信息对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtInfoDTO.java, V1.0.1 2013-2-16 下午9:31:13 $
 */
public class EcgtInfoDTO extends LongID {
    private static final long serialVersionUID = 2190895621825643145L;

    private MapExt            ext;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public MapExt getExt() {
        if (this.ext == null) {
            this.ext = new MapExt();
        }

        return ext;
    }

    public void setExt(MapExt ext) {
        this.ext = ext;
    }

}
