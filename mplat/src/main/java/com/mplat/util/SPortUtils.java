/**
 * obullxl@gmail.com
 */
package com.mplat.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;

/**
 * @author obullxl@gmail.com
 */
public class SPortUtils {

    /**
     * 串口打开时间(毫秒)
     */
    public static final int OPEN_TIMEOUT = 200;

    /**
     * 查找所有可用的串口名称
     */
    public static List<String> findSerialPortNames() {
        List<String> pnames = new ArrayList<String>();

        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier cport = (CommPortIdentifier) ports.nextElement();
            if ((cport != null) && (cport.getPortType() == CommPortIdentifier.PORT_SERIAL)) {
                try {
                    CommPort thePort = cport.open("CommPort", OPEN_TIMEOUT);
                    thePort.close();
                    pnames.add(cport.getName());
                } catch (PortInUseException e) {
                    System.out.println("Port " + cport.getName() + " is in use.");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("Failed to open port " + cport.getName());
                    e.printStackTrace();
                }
            }
        }

        return pnames;
    }
}
