package com.toec.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;

/**
 * 二维码工具类
 *
 * @ClassName: BarcodeUtils.java
 * @version: v1.0.0
 * @author: pll
 * @date: 2018年6月4日 下午2:51:54
 */
public class CodeUtil {

    private static final int QRCOLOR = 0xFF000000; // 二维码颜色 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    private static final int WIDTH = 215; // 二维码宽
    private static final int HEIGHT = 215; // 二维码高
    private static final int WORLDWIDTH = 700; // 二维码宽
    private static final int WORDHEIGHT = 275; // 加文字二维码高


    /**
     * 用于设置QR二维码参数
     */
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;

        {
            // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 设置编码方式
            put(EncodeHintType.CHARACTER_SET, "utf-8");
            put(EncodeHintType.MARGIN, 0);
        }
    };


    /**
     * 设置 Graphics2D 属性  （抗锯齿）
     *
     * @param graphics2D
     */
    private static void setGraphics2D(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        graphics2D.setStroke(s);
    }



    /**
     * 把带logo的二维码下面加上文字
     *
     * @param image
     * @param words
     * @return
     */
    private static BufferedImage insertWords(BufferedImage image, String words) {
        // 新的图片，把带logo的二维码下面加上文字
        if (StringUtils.isNotEmpty(words)) {

            //创建一个带透明色的BufferedImage对象
            BufferedImage outImage = new BufferedImage(WORLDWIDTH, WORDHEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D outg = outImage.createGraphics();

            outg.setColor(Color.WHITE);//设置笔刷白色
            outg.fillRect(0,0,WORLDWIDTH,WORDHEIGHT);//填充整个屏幕
            outg.setColor(Color.WHITE);
            setGraphics2D(outg);

            // 画二维码到新的面板
            outg.drawImage(image, 0, WORDHEIGHT - HEIGHT - 5, image.getWidth(), image.getHeight(), null);
            // 画文字到新的面板
            Color color = new Color(0, 0, 0);
            outg.setColor(color);
            // 字体、字型、字号
            outg.setFont(new Font("微软雅黑", Font.PLAIN, 26));
            //文字长度
            int strWidth = outg.getFontMetrics().stringWidth(words);
            //总长度减去文字长度的一半  （居中显示）
            int wordStartX = WIDTH  + 10;
            //height + (outImage.getHeight() - height) / 2 + 12
            int wordStartY = 150;
            // 画文字
            outg.drawString(words, wordStartX, wordStartY);

            //画实验室名称
            //文字长度
            words = "XX实验室";
            strWidth = outg.getFontMetrics().stringWidth(words);
            //总长度减去文字长度的一半  （居中显示）
            wordStartX = 10;
            //height + (outImage.getHeight() - height) / 2 + 12
            wordStartY = 40;
            // 画文字
            outg.drawString(words, wordStartX, wordStartY);

            outg.dispose();
            outImage.flush();
            return outImage;
        }
        return image;
    }

    public static void drawQRCode(String qrUrl, String words, HttpServletResponse response) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }

            String filename = URLEncoder.encode(words, "UTF-8");
            //设置response头信息
            response.reset();
            response.setContentType("image/png");        //改成输出png文件
            response.setHeader("Content-disposition","attachment; filename=" + filename +".png" );
            OutputStream out = response.getOutputStream();

            // 新的图片，把带logo的二维码下面加上文字
            image=insertWords(image,words);
            image.flush();
            ImageIO.write(image, "png", out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}