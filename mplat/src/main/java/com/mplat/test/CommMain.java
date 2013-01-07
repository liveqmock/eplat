/**
 * obullxl@gmail.com
 */
package com.mplat.test;

import com.mplat.util.SPortUtils;
import com.mplat.util.UIUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

/**
 * @author obullxl@gmail.com
 */
public class CommMain {

    public static void main(String[] args) {
        try {
            List<String> pnames = SPortUtils.findSerialPortNames();
            
            if(pnames.isEmpty()) {
                UIUtils.initSystemUI();
                UIUtils.alert(null, "错误提示", "没有找到可用的串口，请检查串口是否连接正常~");
                return;
            }
            
            for(String pname : pnames) {
                System.out.println("串口可以使用：" + pname);
            }

            CommPortIdentifier comport = CommPortIdentifier.getPortIdentifier(pnames.get(0));
            final SerialPort sport = (SerialPort) comport.open("MPLAT", SPortUtils.OPEN_TIMEOUT);
            System.out.println("串口 " + sport.getName() + " 连接成功");

            sport.setSerialPortParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);
            OutputStream os = sport.getOutputStream();
            sport.notifyOnDataAvailable(true);
            sport.notifyOnBreakInterrupt(true);
            sport.enableReceiveTimeout(500);
            sport.addEventListener(new SerialPortEventListener() {
                public void serialEvent(SerialPortEvent e) {
                    InputStream is = null;
                    StringBuffer msgBuffer = new StringBuffer();
                    try {
                        is = sport.getInputStream();
                    } catch (Exception h) {
                        h.printStackTrace();
                    }

                    int newData = 0;
                    switch (e.getEventType()) {
                        case SerialPortEvent.DATA_AVAILABLE:
                            while (newData != -1) {
                                try {
                                    newData = is.read();
                                    if (newData == -1) {
                                        break;
                                    }
                                    if ('\r' == (char) newData) {
                                    } else {
                                        msgBuffer.append((char) newData);
                                    }
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                            }

                            try {
                                System.out.println("Resultis:" + Double.valueOf(msgBuffer.toString()));
                            } catch (Exception b) {
                                b.printStackTrace();
                            } finally {
                                try {
                                    is.close();
                                    sport.close();
                                } catch (Exception c) {
                                    c.printStackTrace();
                                }
                            }
                            break;
                        case SerialPortEvent.BI:
                            System.out.println("break recieve");
                            break;
                        default:
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
