package com.liao27.model.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
@Data
public class AckBean {

    /**
     * 成功200、失败400 的状态
     */
    private int status;

    /**
     * 业务的编码，比如无权限是T1003
     */
    private String ackCode;

    /**
     * 业务编码对应的中文描述
     */
    private String ackDesc;

    /**
     * 额外的数据
     */
    private Map<String,Object> extraMap = Maps.newHashMap();

    /**
     * 分页数据
     */
    private PageBean pb;

    /**
     * 根据状态返回bean
     *
     * @param status 成功、失败的状态
     * @return 状态bean
     */
    public static AckBean build(int status) {
        AckBean ack = new AckBean();
        ack.setStatus(status);
        return ack;
    }

    /**
     * 根据状态和业务编码返回bean
     *
     * @param status 成功、失败的状态
     * @param code   业务编码
     * @return 状态bean
     */
    public static AckBean build(int status, String code) {
        AckBean ab = new AckBean();
        ab.setStatus(status);
        ab.setAckCode(code);
        String desc = Constants.statusMap.get(code);
        if (desc != null) {
            ab.setAckDesc(desc);
        }
        return ab;
    }

    /**
     * 根据状态和业务编码业务描述返回bean
     *
     * @param status 成功、失败的状态
     * @param code   业务编码
     * @param desc   业务描述
     * @return 状态bean
     */
    public static AckBean build(int status, String code, String desc) {
        AckBean ab = new AckBean();
        ab.setStatus(status);
        ab.setAckCode(code);
        ab.setAckDesc(desc);
        return ab;
    }
}

