/**
 * obullxl@gmail.com
 */
package com.mplat.controls.ui;

import com.mplat.mgt.dto.UserInfoDTO;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author obullxl@gmail.com
 */
public class UserTableCellRenderer extends DefaultTableCellRenderer {

    private int index;

    public UserTableCellRenderer(int index) {
        this.index = index;
    }

    public void setValue(Object value) {
        if(value != null && (value instanceof UserInfoDTO)) {
            UserInfoDTO user = (UserInfoDTO) value;
            // ID
            if(this.index == 0) {
                this.setHorizontalAlignment(SwingConstants.CENTER);
                super.setText(String.valueOf(user.getId()));
            }
            // UserName
            else if(this.index == 1) {
                this.setHorizontalAlignment(SwingConstants.LEFT);
                super.setText(user.getUsrName());
            }
        } else {
            super.setText(String.valueOf(value));
        }
    }
    
}
