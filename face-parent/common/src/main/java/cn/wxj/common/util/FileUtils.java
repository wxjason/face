package cn.wxj.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author wxjason
 */
@Component
@Slf4j
public class FileUtils {


    /**
     * 图形交换格式
     */
    public static final String IMAGE_TYPE_GIF = "gif";

    /**
     * 联合照片专家组
     */
    public static final String IMAGE_TYPE_JPG = "jpg";

    /**
     * 联合照片专家组
     */
    public static final String IMAGE_TYPE_JPEG = "jpeg";
    /**
     * 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
     */
    public static final String IMAGE_TYPE_BMP = "bmp";
    /**
     * 可移植网络图形
     */
    public static final String IMAGE_TYPE_PNG = "png";
    /**
     * Photoshop的专用格式Photoshop
     */
    public static final String IMAGE_TYPE_PSD = "psd";

    /**
     * 删除目录
     *
     * @author fengshuonan
     * @Date 2017/10/30 下午4:15
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (CollectionUtils.isEmpty(children)) {
                return false;
            }
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 保存文件到临时目录下
     *
     * @param in
     * @param newFileName
     */
    public static String save(InputStream in, String newFileName, String filePath) throws FileNotFoundException {
        String tempFile = ResourceUtils.getFile("")
                .getAbsolutePath()
                .concat(File.separator)
                .concat("upload")
                .concat(File.separator)
                .concat("wrapper")
                .concat(File.separator)
                .concat(filePath)
                .concat(File.separator)
                .concat(newFileName);
        save(in, tempFile);
        return tempFile;
    }

    public static void save(InputStream in, String newFileName) {
        OutputStream os = null;
        try {
            String path = newFileName.substring(0, newFileName.lastIndexOf(File.separator));
            File pathFile = new File(path);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File newFile = new File(newFileName);
            os = new FileOutputStream(newFile);
            int bytesRead = 0;
            int len = 1024;
            byte[] buffer = new byte[len];
            while ((bytesRead = in.read(buffer, 0, len)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void close(Writer output) {
        close((Closeable) output);
    }

    public static void close(InputStream input) {
        close((Closeable) input);
    }

    public static void close(OutputStream output) {
        close((Closeable) output);
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void download(HttpServletResponse response, String classPathFile) {
        download(response, classPathFile, ResourceUtils.CLASSPATH_URL_PREFIX);
    }

    public static void download(HttpServletResponse response, String filePath, String pathType) {
        InputStream is = null;
        OutputStream os = null;
        try {
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            if (pathType.equals(ResourceUtils.CLASSPATH_URL_PREFIX)) {
                ClassPathResource classPathResource = new ClassPathResource(filePath);
                is = classPathResource.getInputStream();
                fileName = classPathResource.getFilename();
            } else {
                File file = ResourceUtils.getFile(filePath);
                is = new FileInputStream(file);
            }

            response.addHeader("content-type", contentType(filePath).type);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//            /// 注释原因: 加上下载模版被损坏
            response.addHeader("Content-Length", String.valueOf(is.available()));
            byte[] buff = new byte[1024];

            os = response.getOutputStream();
            int i = is.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = is.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(os);
            close(is);
        }
    }

    public static ContentType contentType(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            String postFix = fileName.substring((fileName.lastIndexOf(".") + 1));
            for (ContentType contentType : ContentType.values()) {
                if (contentType.postFix.equalsIgnoreCase(postFix)) {
                    return contentType;
                }
            }
        }
        return ContentType.UNKNOWN_TYPE;
    }

    enum ContentType {
        /**
         *
         */
        UNKNOWN_TYPE("", "application/octet-stream"),
        BMP_TYPE("bmp", "application/x-bmp"),
        GIF_TYPE("gif", "image/gif"),
        IMG_TYPE("img", "application/x-img"),
        JPG_TYPE("jpg", "application/x-jpg"),
        XLS_EXCEL_TYPE("xls", "application/vnd.ms-excel"),
        XLSX_EXCEL_TYPE("xlsx", "application/vnd.ms-excel");
        String postFix;
        String type;

        ContentType(String postFix, String type) {
            this.postFix = postFix;
            this.type = type;
        }

        public String getPostFix() {
            return postFix;
        }

        public void setPostFix(String postFix) {
            this.postFix = postFix;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static String imgType(byte[] imageData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
             MemoryCacheImageInputStream is = new MemoryCacheImageInputStream(bais);) {
            Iterator<ImageReader> it = ImageIO.getImageReaders(is);

            if (!it.hasNext()) {
                throw new IOException("非图片文件");
            }
            ImageReader reader = it.next();
            return reader.getFormatName();
        }
    }

    /**
     * 检测图片类型
     *
     * @param photo
     * @return
     */
    public static boolean checkType(String photo) {
        String imgtype = photo.substring(photo.lastIndexOf(".") + 1, photo.length()).toLowerCase();
        if (!IMAGE_TYPE_PNG.equalsIgnoreCase(imgtype)
                && !IMAGE_TYPE_JPEG.equalsIgnoreCase(imgtype)
                && !IMAGE_TYPE_JPG.equalsIgnoreCase(imgtype)) {
            return false;
        }
        return true;
    }

    public static String imageBase64(byte[] data) throws IOException {
        if (Objects.nonNull(data)) {
            String type = imgType(data);
            if (StringUtils.isEmpty(type)) {
                return "";
            }
            String prefix;
            switch (type.toLowerCase()) {
                case IMAGE_TYPE_PNG:
                    prefix = "data:image/png;base64,";
                    break;
                case IMAGE_TYPE_JPEG:
                    prefix = "data:image/jpeg;base64,";
                    break;
                case IMAGE_TYPE_GIF:
                    prefix = "data:image/gif;base64,";
                    break;
                case IMAGE_TYPE_JPG:
                    prefix = "data:image/jpg;base64,";
                    break;
                default:
                    return "";
            }
            return prefix + Base64Utils.encodeToString(data);
        }
        return "";
    }

    /**
     * NIO way
     */
    public static byte[] toByteArray(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            log.error("文件未找到！" + filename);
        }
        return toByteArray(f);
    }

    /**
     * NIO way
     */
    public static byte[] toByteArray(File file) {

        if (!file.exists()) {
            log.error("文件未找到！" + file);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                /// do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            log.error("读取文件失败", e);
            return new byte[0];
        } finally {
            try {
                if (Objects.nonNull(channel)) {
                    channel.close();
                }
            } catch (IOException e) {
                log.error("读取文件失败", e);
            }
            try {
                if (Objects.nonNull(fs)) {
                    fs.close();
                }
            } catch (IOException e) {
                log.error("读取文件失败", e);
            }
        }
    }

    public static void copyInputStreamToFile(InputStream in, File targetFile) throws IOException {
        byte[] buffer = new byte[in.available()];
        OutputStream out = null;
        try {
            in.read(buffer);
            out = new FileOutputStream(targetFile);
            out.write(buffer, 0, buffer.length);
        } finally {
            in.close();
            if (Objects.nonNull(out)) {
                out.close();
            }
        }
    }

    public static void copyFile(File file, FileOutputStream destTempfos) throws IOException {

        InputStream input = null;

        try {
            input = new FileInputStream(file);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            destTempfos.write(buffer, 0, buffer.length);
        } finally {
            if (Objects.nonNull(input)) {
                input.close();
            }
        }

    }

    /**
     * @Title: saveImageBase64
     * @Description: base64字符串转化成图片
     * @param:
     * @author wuxinjian
     * @date 2019/2/27 16:40
     */
    public static boolean saveImageBase64(String imgStr, String path) {
        //图像数据为空
        if (imgStr == null) {
            return false;
        }
        try {
            //Base64解码
            byte[] b = Base64Utils.decodeFromString(imgStr);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String process(String sourceBase64, int xwidth, int yheight) {
        InputStream is = new ByteArrayInputStream(Base64Utils.decodeFromString(sourceBase64));
        //构造Image对象
        Image src = null;
        //输出到文件流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            src = ImageIO.read(is);
            //可以计算比例缩小放大（略...）

            BufferedImage tag = new BufferedImage(xwidth,yheight,BufferedImage.TYPE_INT_RGB);
            //绘制缩小后的图
            tag.getGraphics().drawImage(src,0,0,xwidth,yheight,null);


//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            ImageIO.write(tag, IMAGE_TYPE_JPG, out);
            //近JPEG编码
//            encoder.encode(tag);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Base64Utils.encodeToString(out.toByteArray());
    }

    public static byte[] file2Byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

}