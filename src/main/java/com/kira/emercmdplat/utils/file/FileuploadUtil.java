package com.kira.emercmdplat.utils.file;

import com.kira.emercmdplat.pojo.FileReq;
import com.kira.emercmdplat.pojo.FilesReq;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.PropertiesUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.*;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/8 01:18
 * @Description:文件上传工具类
 */
public class FileuploadUtil {

    /**
     * 属性配置
     */
    private static PropertiesUtils pro = PropertiesUtils.getInstance();

    /**
     * 方法描述：根据文件的绝对路径创建一个文件对象.
     * @param filePath 文件的绝对路径
     * @return 返回创建的这个文件对象
     */
    public static File createFile(String filePath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(filePath);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            //创建失败返回null
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 判断这个文件是否存在，不存在就创建
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("目标文件创建失败...");
            }
        }
        Runtime.getRuntime().exec("chmod 777 -R " + filePath);
        return file;
    }

    /**
     * 方法描述：判断extension中是否存在extName
     * @param extension 使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip
     * @param extName   文件的后缀名
     */
    private static void isContains(String extension, String extName) {
        if (StringUtils.isNotEmpty(extension)) {
            // 切割文件扩展名
            String[] exts = StringUtils.split(extension, ",");
            if (ArrayUtils.isNotEmpty(exts)) {
                assert exts != null;
                List<String> extList = Arrays.asList(exts);
                //判断
                if (!extList.contains(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            }
        }
    }

    /**
     * 方法描述：处理上传的图片
     * @param serverPath 图片的绝对路径
     * @param childFile  子文件夹
     * @param extName    文件的后缀
     */
    private static String thumbnails(String serverPath, String childFile, String extName)
            throws IOException {
        // 压缩后的相对路径文件名
        String toFilePath = getDestPath(childFile, extName);

        // scale：图片缩放比例
        // outputQuality：图片压缩比例
        // toFile：图片位置
        // outputFormat：文件输出后缀名
        // Thumbnails 如果用来压缩 png 格式的文件，会越压越大，
        // 得把png格式的图片转换为jpg格式
        if ("png".equalsIgnoreCase(extName)) {
            // 由于outputFormat会自动在路径后加上后缀名，所以移除以前的后缀名
            String removeExtensionFilePath = FilenameUtils.removeExtension(toFilePath);
            Thumbnails.of(serverPath).scale(1f)
                    .outputQuality(0.5f)
                    .outputFormat("jpg")
                    .toFile(getServerPath(removeExtensionFilePath));
            toFilePath = removeExtensionFilePath + ".jpg";
        } else {
            Thumbnails.of(serverPath).scale(1f).outputQuality(0.5f)
                    .toFile(getServerPath(toFilePath));
        }

        // 删除被压缩的文件
        FileUtils.forceDelete(new File(serverPath));

        return toFilePath;
    }

    /**
     * 方法描述：生成文件文件名
     * @param childFile 子目录
     * @param extName   后缀名
     */
    private static String getDestPath(String childFile, String extName) {
        //规则：  子目录/年月日_随机数.后缀名
        String sb = childFile
                + DateUtil.getNowTimestamp().getTime()
                + "." + extName;
        return sb;
    }

    /**
     * 方法描述：生成文件在的实际的路径
     * @param destPath 文件的相对路径
     */
    private static String getServerPath(String destPath) {
        // 文件分隔符转化为当前系统的格式
        return FilenameUtils.separatorsToSystem(pro.getAttachmentGainPath() + destPath);
    }

    /**
     * 方法描述：上传文件.
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @param isImage       上传的是否是图片，如果是就会进行图片压缩；如果不希望图片被压缩，则传false，让其以文件的形式来保存
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    private static FileResult saveFile(MultipartFile multipartFile, String childFile, String extension, Boolean isImage) throws IOException {
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw new IOException("上传的文件对象不存在...");
        }
        // 文件名
        String fileName = multipartFile.getOriginalFilename();
        // 文件后缀名
        String extName = FilenameUtils.getExtension(fileName);
        if (StringUtils.isEmpty(extName)) {
            throw new RuntimeException("文件类型未定义不能上传...");
        }
        // 判断文件的后缀名是否符合规则
        isContains(extension, extName);
        // 创建目标文件的名称，规则请看destPath方法
        String destPath = getDestPath(childFile, extName);
        // 文件的实际路径
        String serverPath = getServerPath(destPath);
        // 创建文件
        File destFile = createFile(serverPath);
        // 保存文件
        multipartFile.transferTo(destFile);

        // 拼装返回的数据
        FileResult result = new FileResult();
        //如果是图片，就进行图片处理
        if (BooleanUtils.isTrue(isImage)) {
            // 图片处理
            String toFilePath = thumbnails(serverPath, childFile, extName);
            // 得到处理后的图片文件对象
            File file = new File(getServerPath(toFilePath));
            // 压缩后的文件后缀名
            String extExtName = FilenameUtils.getExtension(toFilePath);
            // 源文件=源文件名.压缩后的后缀名
            String extFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(toFilePath);
            result.setFileSize(file.length());
            result.setServerPath(destPath);
            result.setFileName(extFileName);
            result.setExtName(extExtName);
        } else {
            result.setFileSize(multipartFile.getSize());
            result.setFileName(fileName);
            result.setExtName(extName);
            result.setServerPath(destPath);
        }
        return result;
    }

    /**
     * 方法描述：上传文件.
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public static FileResult saveFile(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return saveFile(multipartFile, childFile, extension, false);
    }

    /**
     * 方法描述：上传图片.
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public static FileResult saveImage(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return saveFile(multipartFile, childFile, extension, true);
    }

    public static List<String> saveFileByBase64(FilesReq filesReq, String path, String attachmentGainPath) {
        List<String> fileList = new ArrayList<>();
        for (FileReq fileReq : filesReq.getFileReqList()) {
            byte[] byteData = null;
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                String str = fileReq.getFileContent();
                String extension = fileReq.getExtension();
                str = str.replaceAll(" ", "+");
                byteData = decoder.decodeBuffer(str);
                for (int i = 0; i < byteData.length; ++i) {
                    // 调整异常数据
                    if (byteData[i] < 0) {
                        byteData[i] += 256;
                    }
                }
                String uuid = UUID.randomUUID().toString();
                String fileUrl = FilenameUtils.separatorsToSystem(attachmentGainPath + path + uuid + "." + extension);
                File file = new File(fileUrl);
                if (!file.exists()) {
                    file.createNewFile();
                }
                Runtime.getRuntime().exec("chmod 777 -R " + fileUrl);
                fileList.add(FilenameUtils.separatorsToSystem(path + uuid + "." + extension));
                FileOutputStream out = new FileOutputStream(file);
                out.write(byteData);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }

    /**
     * 给图片添加文字水印
     * @param filesReq
     * @param path
     * @param attachmentGainPath
     * @param waterFileName 水印文字内容
     * @return
     */
    public static List<String> addWaterMark(FilesReq filesReq, String path, String attachmentGainPath, String waterFileName) {
        Font font = new Font("宋体", Font.PLAIN, 24);
        List<String> fileList = new ArrayList<>();
        String waterFileUrl = FilenameUtils.separatorsToSystem(attachmentGainPath + path + waterFileName);
        File waterFile = new File(waterFileUrl);
        try {
        Image waterImage = ImageIO.read(waterFile);
        int waterWidth = waterImage.getWidth(null);
        int waterHeight = waterImage.getHeight(null);
        for (FileReq fileReq : filesReq.getFileReqList()) {
            byte[] byteData = null;
            BASE64Decoder decoder = new BASE64Decoder();

                String str = fileReq.getFileContent();
                String extension = fileReq.getExtension();
                str = str.replaceAll(" ", "+");
                byteData = decoder.decodeBuffer(str);
                for (int i = 0; i < byteData.length; ++i) {
                    // 调整异常数据
                    if (byteData[i] < 0) {
                        byteData[i] += 256;
                    }
                }
                String uuid = UUID.randomUUID().toString();
                String fileUrl = FilenameUtils.separatorsToSystem(attachmentGainPath + path + uuid + "." + extension);
                File file = new File(fileUrl);
                if (!file.exists()) {
                    file.createNewFile();
                }
                // 读取原图片信息
                InputStream in = new ByteArrayInputStream(byteData);
                Image srcImg = ImageIO.read(in);//文件转化为图片
                int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
                int srcImgHeight = srcImg.getHeight(null);//获取图片的高
                // 加水印
                BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bufImg.createGraphics();

//                g.setColor(Color.BLACK); //根据图片的背景设置水印颜色
//                g.setBackground(Color.WHITE);
                g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
                float alpha = 0.8f; // 透明度
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
                // 表示水印图片的位置
                g.drawImage(waterImage, 0,0, waterWidth/2, waterHeight/2, null);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            /*----------对显示的文字进行处理------------------*/
//                AttributedString ats = new AttributedString(waterMarkContent);
//                RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//                rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//                g.setFont(font);
//                g.setRenderingHints(rh);
//
//                ats.addAttribute(TextAttribute.FONT, font, 0, waterMarkContent.length());
//                AttributedCharacterIterator iter = ats.getIterator();
//                g.drawString(iter, 20, 30);  //画出水印
//                g.drawString("名称是中国石油呼和浩特石化公司，负责人是李石化。", 20, 70);
//                g.drawString("联系电话是13266873569。", 20, 110);
//                g.drawString("地址内蒙古自治区呼和浩特市赛罕区金河镇中国石油加油站(赛罕区金河镇格尔图小学东南)", 20, 150);

                g.dispose();

                Runtime.getRuntime().exec("chmod 777 -R " + fileUrl);
                fileList.add(FilenameUtils.separatorsToSystem(path + uuid + "." + extension));
                FileOutputStream out = new FileOutputStream(file);
                ImageIO.write(bufImg, fileReq.getExtension(), out);
                out.flush();
                out.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }
    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void downLoad(HttpServletResponse response, String dirOrFile, String targetFullName) {
        try {
            File file = new File(dirOrFile);
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();

                response.reset();
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(targetFullName, "UTF-8"));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
                IOUtils.closeQuietly(outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
