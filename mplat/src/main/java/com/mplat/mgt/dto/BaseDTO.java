/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author obullxl@gmail.com
 */
public abstract class BaseDTO {

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
