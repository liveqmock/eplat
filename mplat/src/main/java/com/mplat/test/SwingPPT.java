/**
 * Alipay.com Inc. Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.mplat.test;

import com.mplat.io.UnicodeInputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

/**
 * @author shizihu
 * @version $Id: SwingPPT.java, v 0.1 2012-12-24 ����12:54:49 shizihu Exp $
 */
public class SwingPPT {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new MyReadPPT();
    }

    static class MyReadPPT extends JFrame {

        private static final long serialVersionUID = 1L;
        JFrame frame = new JFrame();
        JButton dr = new JButton("导入文件");
        JButton sy = new JButton("首页");
        JButton syy = new JButton("上一页");
        JButton xyy = new JButton("下一页");
        JButton my = new JButton("末页");
        JButton tc = new JButton("退出");
        JPanel s_panel = new JPanel();
        jPanel c_panel = new jPanel();
        int index;
        int end;
        JFileChooser filechooser = new JFileChooser();

        public MyReadPPT() {
            super("梁妙的PPT演示程序");

            index = 0;
            // frame 外观设置
            frame.setSize(730, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            s_panel.add(dr);
            s_panel.add(sy);
            s_panel.add(syy);
            s_panel.add(xyy);
            s_panel.add(my);
            s_panel.add(tc);
            s_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().add(s_panel, BorderLayout.SOUTH);

            c_panel.setSize(650, 600);
            c_panel.setLocation(100, 40);
            c_panel.setVisible(false);
            frame.getContentPane().add(c_panel, BorderLayout.CENTER);

            dr.addActionListener(new drMenuListener());
            sy.addActionListener(new syMenuListener());
            syy.addActionListener(new syyMenuListener());
            xyy.addActionListener(new xyyMenuListener());
            my.addActionListener(new myMenuListener());
            tc.addActionListener(new tcMenuListener());
        }

        // 导入菜单动作：导入文件的同时并将PPT转化成图片
        class drMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                try {
                    String CHAR_SET = "UTF-8";
                    InputStream is = new UnicodeInputStream(new FileInputStream("D:/CodeSVN/apteck/dataplatform/share/mbill/商户对账中心-相关业务分享.ppt"), CHAR_SET);
                    SlideShow ppt = new SlideShow(is);
                    is.close();

                    Dimension pgsize = ppt.getPageSize();
                    Slide[] slide = ppt.getSlides();
                    end = slide.length - 1;

                    for (int i = 0; i < slide.length; i++) {
                        BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
                                BufferedImage.TYPE_INT_RGB);
                        Graphics2D graphics = img.createGraphics();

                        // clear the drawing area
                        graphics.setPaint(Color.white);
                        graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                        // render
                        slide[i].draw(graphics);

                        // save the output
                        OutputStream out = new FileOutputStream("c:/ppt/slide-" + (i + 1) + ".jpg");
                        ImageIO.write(img, "jpg", out);
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "文件导入成功！");
                c_panel.setVisible(true);
                c_panel.repaint();

            }
        }

        class syMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                if (index != 0) {
                    index = 0;
                    c_panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "已经到达首页！");
                }
            }
        }

        class syyMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                if (index != 0) {
                    index--;
                    c_panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "已经到达首页！");
                }
            }
        }

        class xyyMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                if (index != end) {
                    index++;
                    c_panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "已经到达末页！");
                }
            }
        }

        class myMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                if (index != end) {
                    index = end;
                    c_panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "已经到达末页！");
                }
            }
        }

        class tcMenuListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                frame.dispose();
            }
        }

        class jPanel extends JPanel {

            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {
                Image image = new ImageIcon("c:/ppt/slide-" + (index + 1) + ".jpg").getImage();
                g.drawImage(image, 0, 0, this);
            }
        }
    }
}
