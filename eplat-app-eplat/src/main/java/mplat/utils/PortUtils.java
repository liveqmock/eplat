/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;

import com.atom.core.lang.utils.LogUtils;

/**
 * 串口工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: PortUtils.java, V1.0.1 2013-2-13 上午11:31:48 $
 */
public final class PortUtils {
    /** 检查串口打开时间(毫秒) */
    public static final int OPEN_TIMEOUT = 200;

    /**
     * 查找所有可用的串口名称
     */
    @SuppressWarnings("unchecked")
    public static List<String> findSerialPortNames() {
        List<String> pnames = new ArrayList<String>();

        Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
        if (ports == null) {
            return pnames;
        }

        while (ports.hasMoreElements()) {
            CommPortIdentifier cport = ports.nextElement();
            if ((cport == null) || (cport.getPortType() != CommPortIdentifier.PORT_SERIAL)) {
                continue;
            }

            try {
                CommPort thePort = cport.open("CommPort", OPEN_TIMEOUT);
                thePort.close();
                pnames.add(cport.getName());
            } catch (PortInUseException e) {
                LogUtils.warn("[检查串口]-串口[" + cport.getName() + "]正在使用中！");
            } catch (Exception e) {
                LogUtils.warn("[检查串口]-检查串口[" + cport.getName() + "]发生异常！");
            }
        }

        // 可用串口名称
        return pnames;
    }
}
