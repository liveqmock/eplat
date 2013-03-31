package test.test.swt;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ShapeWindow {

    public static void main(String[] args) {
        ShapeWindow window = new ShapeWindow();
        window.open();
    }

    private Display   display;
    private Image     image;
    private Shell     shell;
    private ImageData id;
    private Region    region;

    private void open() {
        // TODO Auto-generated method stub      
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    private void createContents() {
        // TODO Auto-generated method stub
        display = Display.getDefault();
        shell = new Shell(display, SWT.NO_TRIM);
        shell.setText("形状窗口");

        image = getImage();
        region = new Region();
        //区分image是α还是β像素
        if (id.alphaData != null) {
            for (int y = 0; y < id.height; y++) {
                for (int x = 0; x < id.width; x++) {
                    if (id.getAlpha(x, y) == 255) {
                        region.add(x, y, 1, 1);
                    }
                }
            }
        } else {
            ImageData mask = id.getTransparencyMask();
            for (int y = 0; y < mask.height; y++) {
                for (int x = 0; x < mask.width; x++) {
                    if (mask.getPixel(x, y) != 0) {
                        region.add(x, y, 1, 1);
                    }
                }
            }
        }
        shell.setRegion(region);
        shell.setSize(id.width, id.height);
        //shell.setSize(image.getBounds().width, image.getBounds().height);
        Listener listen = new Listener() {
            int startX, startY;

            public void handleEvent(Event e) {
                //添加esc按钮事件
                if (e.type == SWT.KeyDown && e.character == SWT.ESC) {
                    shell.dispose();
                }
                //添加鼠标点击事件
                if (e.type == SWT.MouseDown && e.button == 1) {
                    startX = e.x;
                    startY = e.y;
                }
                //添加鼠标图东窗体事件
                if (e.type == SWT.MouseMove && (e.stateMask & SWT.BUTTON1) != 0) {
                    Point p = shell.toDisplay(e.x, e.y);
                    p.x -= startX;
                    p.y -= startY;
                    shell.setLocation(p);
                }
                //窗体重新绘制
                if (e.type == SWT.Paint) {
                    //将不规则图片设为窗体背景，这样就完成了不规则窗体的绘制
                    e.gc.drawImage(image, id.x, id.y);
                }
            }
        };
        shell.addListener(SWT.KeyDown, listen);
        shell.addListener(SWT.MouseDown, listen);
        shell.addListener(SWT.MouseMove, listen);
        shell.addListener(SWT.Paint, listen);
    }

    private Image getImage() {
        Image im = null;
        InputStream is = ShapeWindow.class.getResourceAsStream("/shape.png");
        id = new ImageData(is);
        if (id != null) {
            im = new Image(display, id);
        }
        try {
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return im;
    }
}
