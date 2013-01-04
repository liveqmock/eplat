/**
 * obullxl@gmail.com
 */
package com.mplat.controls.ui;

import com.mplat.mgt.dto.BaseDTO;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author obullxl@gmail.com
 */
public class MplatTableCellRenderExt  extends DefaultTableCellRenderer {

    private int index;

    public MplatTableCellRenderExt(int index) {
        this.index = index;
    }

    public void setValue(Object value) {
        if(value != null && (value instanceof BaseDTO)) {
            BaseDTO user = (BaseDTO) value;
            // ID
            if(this.index == 0) {
                this.setHorizontalAlignment(SwingConstants.CENTER);
                super.setText(String.valueOf(user.getId()));
            }
        } else {
            super.setText(String.valueOf(value));
        }
    }

}
