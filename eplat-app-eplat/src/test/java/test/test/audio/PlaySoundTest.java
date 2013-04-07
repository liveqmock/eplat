/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.audio;

import java.io.File;

import com.atom.apps.eplat.SWTUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: PlaySoundTest.java, V1.0.1 2013-4-7 下午8:41:39 $
 */
public class PlaySoundTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("D:/ACLS8000/userdata_common/HLMan/Resourse/CPR_Sounds/021.wav");
        SWTUtils.playAudio(file);
        
        SWTUtils.dispose();
    }

}
