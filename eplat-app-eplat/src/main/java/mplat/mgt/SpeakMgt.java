/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.mgt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.mgt.dto.SpeakDTO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * 模拟人发音
 * 
 * @author obullxl@gmail.com
 * @version $Id: SpeakMgt.java, V1.0.1 2013-6-15 下午4:45:49 $
 */
public class SpeakMgt {
    /** 语音分类 */
    // 呼吸模式
    private static final String               MODE    = "resp_name";
    // 心音
    private static final String               HEART   = "heart_sounds";
    // 呼吸音
    private static final String               BREATH  = "breath_sounds";
    // 肠鸣音
    private static final String               PALPATE = "borborygmus_sounds";
    // 模拟人发音
    private static final String               SPEAK   = "simulator_sounds";

    /** 数据 */
    private final Map<String, List<SpeakDTO>> data    = new ConcurrentHashMap<String, List<SpeakDTO>>();

    /**
     * CTOR
     */
    public SpeakMgt() {
        InputStream input = null;
        try {
            String path = CfgUtils.findConfigPath();
            String file = FilenameUtils.normalize(path + "/cfgs/EmergeSpeakCfg.xml");
            input = new FileInputStream(file);

            XMLNode root = XMLUtils.toXMLNode(input);
            for (XMLNode node : root.getChildren()) {
                String pcode = node.getExtMap().get("code");

                List<SpeakDTO> items = this.data.get(pcode);
                if (items == null) {
                    items = new ArrayList<SpeakDTO>();
                    this.data.put(pcode, items);
                }

                for (XMLNode child : node.getChildren()) {
                    SpeakDTO item = new SpeakDTO();
                    item.setCode(child.getExtMap().get("code"));
                    item.setText(child.getExtMap().get("value"));

                    items.add(item);
                }
            }
        } catch (Exception e) {
            LogUtils.error("初始化发音信息异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 转为字符串数组
     */
    public static String[] toArray(List<SpeakDTO> items) {
        String[] dstObjs = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            dstObjs[i] = items.get(i).getText();
        }

        return dstObjs;
    }

    /**
     * 呼吸模式
     */
    public List<SpeakDTO> findModes() {
        return Collections.unmodifiableList(this.data.get(MODE));
    }

    /**
     * 心音
     */
    public List<SpeakDTO> findHearts() {
        return Collections.unmodifiableList(this.data.get(HEART));
    }

    /**
     * 呼吸音
     */
    public List<SpeakDTO> findBreaths() {
        return Collections.unmodifiableList(this.data.get(BREATH));
    }

    /**
     * 肠鸣音
     */
    public List<SpeakDTO> findPalpates() {
        return Collections.unmodifiableList(this.data.get(PALPATE));
    }

    /**
     * 模拟人发音
     */
    public List<SpeakDTO> findSpeaks() {
        return Collections.unmodifiableList(this.data.get(SPEAK));
    }

}
