package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.*;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.*;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.QuickReportService;
import com.kira.emercmdplat.service.VerifyReportService;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: kira
 * @Date: 2020/4/8 23:49
 * @Description:
 */
@Service
public class VerifyReportServiceImpl implements VerifyReportService {

    @Autowired
    private VerifyReportMapper vrm;
    @Autowired
    private EventMapper em;
    @Autowired
    private QuickReportMapper qrm;
    @Autowired
    private MessageMapper mm;
    @Autowired
    private ReservePlanMapper rpm;

    @Transactional
    @Override
    public int insert(VerifyReport verifyReport) {
        EventResult eventResult = em.selectById(verifyReport.getEventId());
        if (eventResult == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (eventResult.getStatus() == EventStatus.FINISH.getNo() && eventResult.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        boolean result;
        VerifyReport _verifyReport = vrm.selectByEventId(verifyReport.getEventId());
        if (_verifyReport != null){
            verifyReport.setId(verifyReport.getId());
            result = vrm.update(verifyReport);
        }else {
            result =  vrm.insert(verifyReport) > 0?true:false;
        }
        if (result) {
            QuickReport quickReport = new QuickReport();
            quickReport.setTitle(eventResult.getEventTitle());
            quickReport.setContent(verifyReport.getRichText());
            quickReport.setEventId(verifyReport.getEventId());
            quickReport.setOrigin(QuickReportOrigin.VERIFY_REPORT_ORIGIN.getNo());
            quickReport.setEditId(eventResult.getContactId());
            quickReport.setIssueTime(DateUtil.getNowStr());
            JSONObject json = new JSONObject();
            json.put("richText", verifyReport.getRichText());

            // 文件的实际路径
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
            String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath");
            String uuid = UUID.randomUUID().toString();

            String toPath = FilenameUtils.separatorsToSystem(attachmentGainPath + path + uuid + ".pdf");
            String content = PDFTemplateUtil.freeMarkerRender(json, "/ftlFile/pdf.ftl");
            try {
                PDFTemplateUtil.createPdf(content, toPath);
                verifyReport.setQuickReportAddr(path + uuid + ".pdf");
                vrm.update(verifyReport);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "pdf文件生成失败");
            }
            quickReport.setPdfAddr(path + uuid + ".pdf");
            quickReport.setSubmitId(eventResult.getContactId().intValue());
            qrm.insert(quickReport);
            //生成PDF
            Event event = new Event();
            event.setId(eventResult.getId());
            event.setProcess(EventProcess.VERIFY_REPORT.getNo());
            //新增一条消息
            Message message = new Message();
            message.setEventId(event.getId());
            message.setContactId(verifyReport.getContactId());
            message.setVId(verifyReport.getId());
            message.setStatus(MessageStatus.MESSAGE_UNREAD.getNo());
            message.setType(MessageType.NORMAL_TYPE.getNo());
            mm.insert(message);
            //更新事件进程
            em.update(event);
            //预先生成一条预案记录
            ReservePlan reservePlan = new ReservePlan();
            reservePlan.setEventId(verifyReport.getEventId());
            reservePlan.setStatus(ReservePlanStatus.UNEDIT.getNo());
            reservePlan.setStartTime(DateUtil.getNowStr());
            rpm.insert(reservePlan);
            return 1;
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.hashCode(), "新增核实报告失败");
        }
    }

    @Override
    public boolean delete(Long verifyReportId) {
        return vrm.delete(verifyReportId);
    }

    @Override
    public boolean update(VerifyReport pojo) {
        return vrm.update(pojo);
    }

    @Override
    public VerifyReport selectById(Long verifyReportId) {
        return vrm.selectById(verifyReportId);
    }

    @Override
    public List<VerifyReport> queryForAllOrPage(VerifyReport verifyReport) {
        if (verifyReport != null && verifyReport.getPage() != null) {
            verifyReport.setPage((verifyReport.getPage() - 1) * verifyReport.getPageSize());
        }
        return vrm.queryForAllOrPage(verifyReport);
    }

    @Override
    public Long queryForCounts(VerifyReport pojo) {
        return vrm.queryForCounts(pojo);
    }
}
