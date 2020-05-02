package com.kira.emercmdplat.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;

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

    public static void createPdf(String content, String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider();
        fontImp.register("/templates/font/simsun.ttc");
        InputStream is = new ByteArrayInputStream(content.getBytes("utf-8"));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, null, Charset.forName("UTF-8"), (FontProvider)fontImp);
        document.close();
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
