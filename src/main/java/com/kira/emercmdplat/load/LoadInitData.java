package com.kira.emercmdplat.load;

import com.kira.emercmdplat.service.DataTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/29 11:58
 * @Description:初始化数据
 */
@Component
public class LoadInitData implements CommandLineRunner {

    @Autowired
    private DataTypeService dts;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("开始初始化数据...");
        List<String> tableList = new ArrayList<>();
        tableList.add("event_development");
        tableList.add("event_param");
        tableList.add("event_receive");
        tableList.add("event_risk");
        tableList.add("feedback");
        tableList.add("leader_instruct");
        tableList.add("message");
        tableList.add("quick_report");
        tableList.add("report");
        tableList.add("reserve_plan");
        tableList.add("sys_log");
        tableList.add("task");
        tableList.add("verify_report");
        tableList.add("plan_catalog");
        tableList.add("plan_group");
        tableList.add("plan_response_guard");
        tableList.add("plan_version");
        tableList.add("plan_version_approval");
//        dts.truncateTable(tableList);
        System.out.println("数据初始化成功...");
    }
}
