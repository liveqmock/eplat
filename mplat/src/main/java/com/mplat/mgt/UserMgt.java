/**
 * obullxl@gmail.com
 */
package com.mplat.mgt;

import com.mplat.mgt.dto.UserInfoDTO;
import java.util.List;

/**
 * @author obullxl@gmail.com
 */
public interface UserMgt {

    long create(UserInfoDTO user);
    
    List<UserInfoDTO> findAll();
    
    UserInfoDTO findByName(String usrName);
    
    boolean update(UserInfoDTO user);
    
    boolean remove(String usrName);
    
}
