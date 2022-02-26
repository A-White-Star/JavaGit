package com.example.vshopadmin.common;

import com.example.vshopadmin.model.ShangPinTuPian;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Base64Util {
    public static String imgToBase64String(String filePath) {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        File f = new File(filePath);
        try {
            BufferedImage bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return "data:image/jpg;base64, "+encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String imgToBase64StringSmall(String filePath) {
//        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//        File f = new File(filePath);
//        try {
//            BufferedImage bi = ImageIO.read(f);
////            int width = bi.getWidth();
////            int height = bi.getHeight();
//            /**
//             * 根据缩放比较 构建新的BufferImage 对象
//             */
//            BufferedImage destBufferImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
//            /**
//             * 绘制 缩小  后的图片
//             */
//            destBufferImage.getGraphics().drawImage(bi, 0, 0, 64, 64, null);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(destBufferImage, "jpg", baos);
//            byte[] bytes = baos.toByteArray();
//            return encoder.encodeBuffer(bytes).trim();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
