/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt.msgs;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import org.apache.commons.io.IOUtils;

import com.atom.core.lang.utils.DigitUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * 数据/消息控制器
 * <p/>
 * 负责：打开/重新连接/关闭串口，从串口中读取数据，往串口中写数据。
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataMSG.java, V1.0.1 2013-2-23 下午8:25:47 $
 */
public final class DataMSG implements SerialPortEventListener {
    /** 串口只能被一个程序使用 */
    private final static DataMSG MSG = new DataMSG();

    /**
     * 获取控制器
     */
    public static final DataMSG get() {
        return MSG;
    }

    /** 读写锁 */
    private final Lock        lock     = new ReentrantLock();

    /** 有效 */
    private boolean           valid    = Boolean.FALSE;

    /** 串口名称 */
    private String            portName;

    /** 数据串口 */
    private SerialPort        dataPort;

    /** 输入/输出流 */
    private InputStream       input;
    private OutputStream      output;

    /** 数据缓存 */
    private final List<int[]> data     = new ArrayList<int[]>();

    /** 数据监听器 */
    private DataListener      listener = NopDataListener.INSTANCE;

    /**
     * 构造器，初始化
     */
    private DataMSG() {
    }

    /**
     * 设置串口名称
     */
    public final DataMSG setPortName(String portName) {
        this.portName = portName;
        return this;
    }

    /**
     * 设置数据监听器
     */
    public final DataMSG setDataListener(DataListener listener) {
        if (this.listener != null) {
            LogUtils.warn("[数据]-设置数据监听器，原有监听器不为NULL.");
        }

        if (listener == null) {
            throw new RuntimeException("[数据]-设置新数据监听器为NULL.");
        }

        this.listener = listener;
        return this;
    }

    /**
     * 打开串口连接
     */
    public final boolean openPort() {
        this.lock.lock();
        try {
            if (this.isValid()) {
                throw new RuntimeException("[串口]-串口已经打开，请先关闭！");
            }

            // 打开串口
            CommPortIdentifier cport = CommPortIdentifier.getPortIdentifier(this.portName);
            this.dataPort = (SerialPort) cport.open("EPLAT", 500);

            // 参数设置
            this.dataPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            this.dataPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            // 监听数据
            this.dataPort.addEventListener(this);

            this.dataPort.notifyOnDataAvailable(true);
            this.dataPort.notifyOnOutputEmpty(true);

            this.input = this.dataPort.getInputStream();
            this.output = this.dataPort.getOutputStream();

            this.valid = true;

            LogUtils.info("[串口]-串口[" + this.portName + "]打开成功！");
        } catch (Exception e) {
            LogUtils.error("[串口]-串口[" + this.portName + "]打开异常！");

            this.valid = false;
        } finally {
            this.lock.unlock();
        }

        // 是否有效
        return this.valid;
    }

    /**
     * 数据事件
     * 
     * @see javax.comm.SerialPortEventListener#serialEvent(javax.comm.SerialPortEvent)
     */
    public final void serialEvent(SerialPortEvent event) {
        // 数据可用
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            LogUtils.info("[事件]-收到数据上传事件...");
            // 处理数据
            this.readData(event);
            LogUtils.info("[事件]-数据上传事件响应完成.");
        }

        // 输出可用
        else if (event.getEventType() == SerialPortEvent.OUTPUT_BUFFER_EMPTY) {
            LogUtils.info("[事件]-数据输出完成.");
        }

        // 其他事件
        else {
            LogUtils.info("[串口]-无需处理的事件-" + event.getEventType());
        }
    }

    /**
     * 是否有效
     */
    public final boolean isValid() {
        return this.valid;
    }

    /**
     * 读取数据到缓存区中
     */
    private final DataMSG readData(SerialPortEvent evt) {
        this.lock.lock();
        try {
            // 循环读取数据
            // int idx = 0;
            IntBuffer buffer = IntBuffer.allocate(1024);
            while (true) {
                // 读取数据
                int data = this.input.read();
                // LogUtils.debug("[数据]-上传数据[" + StringUtils.leftPad(Integer.toString((idx++)), 2, "0") + "]：" + DigitUtils.toHex(data));
                // 缓存数据
                buffer.put(data);

                if (data == -1) {
                    // 末尾
                    break;
                }

                if (buffer.position() >= 3) {
                    int one = buffer.get(buffer.position() - 3);
                    if (one == DataUtils.TAIL[0]) {
                        int two = buffer.get(buffer.position() - 2);
                        if (two == DataUtils.TAIL[1]) {
                            int three = buffer.get(buffer.position() - 1);
                            if (three == DataUtils.TAIL[2]) {
                                // 消息尾
                                break;
                            }
                        }
                    }
                }
            }

            // limit=position, position=0
            buffer.flip();

            String msg = DigitUtils.toHex(buffer.array(), buffer.limit());
            LogUtils.info("[上传]-上传数据：" + msg);

            // 最小长度
            boolean save = false;
            if (buffer.limit() >= DataUtils.MIN_SIZE) {
                int[] value = new int[buffer.limit()];
                for (int i = 0; i < value.length; i++) {
                    value[i] = buffer.get();
                }

                // 数据合法(数据头和数据尾)
                int[] head = new int[DataUtils.HEAD.length];
                System.arraycopy(value, 0, head, 0, head.length);
                if (Arrays.equals(DataUtils.HEAD, head)) {
                    int[] tail = new int[DataUtils.TAIL.length];
                    System.arraycopy(value, (value.length - tail.length), tail, 0, tail.length);
                    if (Arrays.equals(DataUtils.TAIL, tail)) {
                        // 保存数据
                        save = true;
                        this.data.add(value);
                        LogUtils.info("[上传]-上传消息：" + DigitUtils.toHex(value));

                        // 触发事件
                        this.onDataChange();
                    }
                }
            }

            if (!save) {
                LogUtils.warn("[上传]-丢弃数据：" + msg);
            }
        } catch (Exception e) {
            LogUtils.error("[上传]-串口读取数据异常！", e);
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    /**
     * 触发数据可用事件
     */
    private final DataMSG onDataChange() {
        if (this.listener == null) {
            this.listener = NopDataListener.INSTANCE;
        }

        // 输出全部可用数据
        int[] data = this.findDataMSG();
        while (data != null) {
            // 输出数据
            DataItem item = DataUtils.parse(data);
            this.listener.onData(item);
            // 下一条数据
            data = this.findDataMSG();
        }

        return this;
    }

    /**
     * 输出数据
     */
    public final DataMSG writeData(byte[] data) {
        if (data == null) {
            return this;
        }

        // 数据转换
        int[] value = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            value[i] = data[i];
        }

        // 数据下发
        return this.writeData(value);
    }

    /**
     * 输出数据
     */
    public final DataMSG writeData(int[] data) {
        if (data == null) {
            return this;
        }

        this.lock.lock();

        String hex = DigitUtils.toHex(data);
        try {
            LogUtils.info("[下发]-输出数据[" + hex + "]...");

            // 输出数据
            ByteBuffer buffer = ByteBuffer.allocate(data.length * 4);
            for (int value : data) {
                buffer.put((byte) value);
            }

            // limit=position, position=0
            buffer.flip();

            this.output.write(buffer.array(), 0, buffer.limit());

            LogUtils.info("[下发]-输出数据[" + hex + "]完成！");
        } catch (Exception e) {
            LogUtils.error("[下发]-输出数据[" + hex + "]异常！", e);
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    /**
     * 从缓存区中获取数据报文
     */
    public final int[] findDataMSG() {
        this.lock.lock();
        try {
            if (this.data.isEmpty()) {
                return null;
            } else {
                return this.data.remove(0);
            }
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 关闭连接
     */
    public final void closePort() {
        // 输入/输出流
        IOUtils.closeQuietly(this.input);
        IOUtils.closeQuietly(this.output);

        // 关闭串口
        if (this.valid) {
            this.dataPort.close();
            this.valid = false;
        }

        LogUtils.info("[串口]-串口[" + this.portName + "]成功关闭！");
    }

}
