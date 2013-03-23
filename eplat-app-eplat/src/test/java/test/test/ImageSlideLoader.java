/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageSlideLoader.java, V1.0.1 2013-3-23 下午4:12:08 $
 */
public class ImageSlideLoader extends Thread {
    double               width     = 800;
    double               height    = 600;
    // 阻塞队列存储图片
    BlockingQueue<Image> images    = new ArrayBlockingQueue<>(2);
    // 图片结束
    Image                eof       = new WritableImage(1, 1);
    boolean              cancelled = false;
    String[]             resources = { "bugs-0.jpg", "bugs-1.jpg", "cooper.jpg", "tomatoes.jpg" };

    public void cancel() throws InterruptedException {
        cancelled = true;
        interrupt();
        join();
    }

    public Image getNextImage() {
        try {
            Image res = images.take();
            if (res != eof) {
                return res;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void run() {
        int id = 0;
        try {
            while (true) {
                String path = resources[id];
                InputStream is = getClass().getResourceAsStream(path);
                if (is != null) {
                    Image image = new Image(is, width, height, true, true);
                    if (!image.isError()) {
                        images.put(image);
                    }
                }
                id++;
                if (id >= resources.length) {
                    id = 0;
                }
            }
        } catch (Exception e) {
        } finally {
            if (!cancelled) {
                try {
                    images.put(eof);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
