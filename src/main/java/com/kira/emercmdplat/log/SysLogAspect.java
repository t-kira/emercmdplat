package com.kira.emercmdplat.log;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.enums.SysLogType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.SysLogService;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.StringUtil;
import com.kira.emercmdplat.utils.TokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: kira
 * @Date: 2020/5/4 22:07
 * @Description:系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sls;
    @Autowired
    private ContactService cs;

    @Pointcut("@annotation(com.kira.emercmdplat.annotation.MyLog)")
    public void logPointCut() {

    }

    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            sysLog.setSysLogType(myLog.value());
            sysLog.setOperation(SysLogType.getByValue(myLog.value()).getName());//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        Object arg = args[0];
        JSONObject json = JSONObject.fromObject(arg);
        Long eid = 0l;
        if (arg instanceof Event) {
            eid = StringUtil.toLongDefValue(json.get("id").toString(), 0l);
        } else if(arg instanceof VerifyEventReq) {
            Long coverEId = StringUtil.toLongDefValue(json.get("coverEId").toString(), 0l);
            Long mainEId = StringUtil.toLongDefValue(json.get("mainEId").toString(), 0l);
            if (mainEId > 0) {
                eid = mainEId;
            } else {
                eid = coverEId;
            }
        } else if(arg instanceof EventDomain) {
            JSONObject eventJson = json.getJSONObject("event");
            eid = StringUtil.toLongDefValue(StringUtil.toStr(eventJson.get("id")), 0l);
        } else if(arg instanceof ReservePlanResult) {
            int status = StringUtil.toIntDefValue(StringUtil.toStr(json.get("status")), 0);
            if (status == 4) {
                sysLog.setOperation("调整预案");
            }
            eid = StringUtil.toLongDefValue(json.get("eid").toString(), 0l);
        } else if(arg instanceof TaskExtend) {
            eid = StringUtil.toLongDefValue(json.get("eventId").toString(), 0l);
        } else {
            eid = StringUtil.toLongDefValue(json.get("eid").toString(), 0l);
        }
        sysLog.setEid(eid);
        //将参数所在的数组转换成json
        String params = json.toString();
        sysLog.setParams(params);

        sysLog.setCreateTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        //获取用户名
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        sysLog.setUserName(contactsResult.getContactName());

        //调用service保存SysLog实体类到数据库
        sls.insert(sysLog);
    }
}
