/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mplat.mgt.dto.PumpInfoDTO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;

/**
 * 注射泵/输液泵管理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: PumpMgt.java, V1.0.1 2013-2-23 上午10:11:12 $
 */
public final class PumpMgt {
    /** 类型 */
    public static final int         EJECTOR  = 1;
    public static final int         TRANSFER = 2;

    /** 类型 */
    private final int               catg;

    /** 对象ID */
    private final AtomicLong        id       = new AtomicLong(0);
    /** 对象列表 */
    private final List<PumpInfoDTO> pumps    = new ArrayList<PumpInfoDTO>();

    /**
     * 构造器
     */
    public PumpMgt(int catg) {
        this.catg = catg;

        if (this.catg != EJECTOR && this.catg != TRANSFER) {
            throw new RuntimeException("注射泵/输液泵管理器失败，未知类型[" + this.catg + "]！");
        }

        this.initPumps();
    }

    /**
     * 初始化
     */
    private final void initPumps() {
        InputStream input = null;
        try {
            String path = CfgUtils.findConfigPath();
            String file = FilenameUtils.normalize(path + "/cfgs/" + this.findFileName());
            input = new FileInputStream(file);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(input);
            NodeList drugs = doc.getElementsByTagName("drug");
            for (int i = 0; i < drugs.getLength(); i++) {
                PumpInfoDTO item = new PumpInfoDTO();

                Node node = drugs.item(i);
                item.setKey(node.getAttributes().getNamedItem("key").getNodeValue());

                item.setName(doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue());
                item.setAdvice(doc.getElementsByTagName("advice").item(i).getFirstChild().getNodeValue());
                item.setRate(doc.getElementsByTagName("rate").item(i).getFirstChild().getNodeValue());
                item.setDose(doc.getElementsByTagName("dose").item(i).getFirstChild().getNodeValue());
                item.setWeight(doc.getElementsByTagName("weight").item(i).getFirstChild().getNodeValue());

                // 处理完成
                item.setId(this.id.incrementAndGet());
                this.pumps.add(item);
            }
        } catch (Exception e) {
            LogUtils.error("初始化注射泵/输液泵信息异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 获取文件名
     */
    private String findFileName() {
        if (this.catg == EJECTOR) {
            return "PumpEjectorCfg.xml";
        } else if (this.catg == TRANSFER) {
            return "PumpTransferCfg.xml";
        }

        return null;
    }

    /**
     * 根据ID获取对象信息
     */
    public PumpInfoDTO find(long id) {
        for (PumpInfoDTO pump : this.pumps) {
            if (pump.getId() == id) {
                return pump;
            }
        }

        return null;
    }

    /**
     * 获取所有注射泵/输液泵信息
     */
    public List<PumpInfoDTO> findAll() {
        return new ArrayList<PumpInfoDTO>(this.pumps);
    }

}
