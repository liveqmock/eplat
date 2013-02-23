/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.mgt;

import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.dto.PumpInfoDTO;

/**
 * 注射泵/输液泵管理器测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpMgtTest.java, V1.0.1 2013-2-23 上午10:39:25 $
 */
public class PumpMgtTest {

    public static void main(String[] args) {
        List<PumpInfoDTO> pumps = MgtFactory.get().findEjectorMgt().findAll();
        for (PumpInfoDTO item : pumps) {
            System.out.println(item);
        }
    }

}
