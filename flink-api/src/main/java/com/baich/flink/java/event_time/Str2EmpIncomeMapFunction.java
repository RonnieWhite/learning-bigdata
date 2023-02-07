package com.baich.flink.java.event_time;

import org.apache.flink.api.common.functions.MapFunction;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023-02-06
 * Time : 09:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Str2EmpIncomeMapFunction implements MapFunction<String, EmployeeIncome> {
    @Override
    public EmployeeIncome map(String value) throws Exception {
        // flowId,employeeName,income,flowTime
        String[] fields = value.split(",");
        if (fields.length == 4) {
            return new EmployeeIncome(Integer.parseInt(fields[0]), fields[1], Float.parseFloat(fields[2]), Long.valueOf(fields[3]));
        }
        return new EmployeeIncome();
    }
}
