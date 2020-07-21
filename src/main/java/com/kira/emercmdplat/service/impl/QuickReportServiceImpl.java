package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.QuickReportMapper;
import com.kira.emercmdplat.mapper.VerifyReportMapper;
import com.kira.emercmdplat.pojo.QuickReport;
import com.kira.emercmdplat.pojo.QuickReportResult;
import com.kira.emercmdplat.pojo.VerifyReport;
import com.kira.emercmdplat.service.QuickReportService;
import com.kira.emercmdplat.utils.PDFTemplateUtil;
import com.kira.emercmdplat.utils.PropertiesUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: kira
 * @Date: 2020/4/13 03:51
 * @Description:
 */
@Service
public class QuickReportServiceImpl implements QuickReportService {

    @Autowired
    private QuickReportMapper qrm;
    @Autowired
    private VerifyReportMapper vrm;

    @Override
    public int insert(QuickReport pojo) {
        return qrm.insert(pojo);
    }

    @Override
    public boolean delete(Long id) {
        return qrm.delete(id);
    }

    @Override
    public boolean update(QuickReport pojo) {
        // 文件的实际路径
        String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
        String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath");
        String uuid = UUID.randomUUID().toString();
        JSONObject json = new JSONObject();
        json.put("richText", pojo.getContent());

        String toPath = FilenameUtils.separatorsToSystem(attachmentGainPath + path + uuid + ".pdf");
        String content = PDFTemplateUtil.freeMarkerRender(json, "/ftlFile/pdf.ftl");
        try {
            PDFTemplateUtil.createPdf(content, toPath);
            VerifyReport verifyReport = vrm.selectByEventId(pojo.getEventId());
            verifyReport.setQuickReportAddr(path + uuid + ".pdf");
            vrm.update(verifyReport);
            pojo.setPdfAddr(path + uuid + ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "pdf文件生成失败");
        }
        return qrm.update(pojo);
    }

    @Override
    public QuickReportResult selectById(Long id) {
        return qrm.selectById(id);
    }

    @Override
    public List<QuickReportResult> queryForAllOrPage(QuickReport quickReport) {
        if (quickReport != null && quickReport.getPage() != null) {
            quickReport.setPage((quickReport.getPage() - 1) * quickReport.getPageSize());
        }
        return qrm.queryForAllOrPage(quickReport);
    }

    @Override
    public Long queryForCounts(QuickReport quickReport) {
        return qrm.queryForCounts(quickReport);
    }
}
