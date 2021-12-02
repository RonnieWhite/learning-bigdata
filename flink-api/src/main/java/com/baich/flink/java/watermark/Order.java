package com.baich.flink.java.watermark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-01
 * Time : 09:23
 * Description:
 * Modified By:
 * version : v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String orderId;
    private Integer money;
    private Long eventTime;
}
