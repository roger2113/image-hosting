package gulmak.mak.imagehosting.util;


import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Images {

    public static byte[] createThumbnail(byte[] src_image, int targetWidth, int targetHeight) {
        byte[] thumbnail;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(src_image);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage image = ImageIO.read(bais);
            BufferedImage thumbnailimg = Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 200, 200);
            ImageIO.write(thumbnailimg, "jpg", baos);
            baos.flush();
            thumbnail = baos.toByteArray();
            return thumbnail;
        } catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }
}
