package com.william.stock.entity.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtData {
    /**
     * 用户编号
     */
    private String userNo;
}
