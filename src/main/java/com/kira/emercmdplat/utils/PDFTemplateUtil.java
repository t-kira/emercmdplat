package com.kira.emercmdplat.utils;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.json.JSONObject;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class PDFTemplateUtil {

    private static Configuration freemarkerCfg = null;

    static {
        freemarkerCfg =new Configuration();
        //freemarker的模板目录
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPdf(String content, String dest)  throws IOException, DocumentException {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont("/ftlFile/font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
//        render.getSharedContext().setBaseURL(LOGO_PATH);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
        Runtime.getRuntime().exec("chmod 666 -R " + dest);
    }

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(JSONObject json, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            template.process(json, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
