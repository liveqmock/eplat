/**
 * obullxl@gmail.com
 */
package com.mplat.das.dataobject;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;

/**
 * Base class for all data objects.
 */
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 741231858441822688L;

    /**
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("unchecked") 
    public String toString() {
        try {
            Map          props     = BeanUtils.describe(this);
            Iterator     iProps    = props.keySet().iterator();
            StringBuffer retBuffer = new StringBuffer();

            while (iProps.hasNext()) {
                String key = (String) iProps.next();

                // skip false property "class"
                if ("class".equals(key)) {
                    continue;
                }

                retBuffer.append(key).append("=[").append(props.get(key)).append("]");

                if (iProps.hasNext()) {
                    retBuffer.append(", ");
                }
            }

            return retBuffer.toString();
        } catch (Exception e) {
            return super.toString();
        }
    }
}
