package com.example.my_springcloud.exception.spring_exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
@AllArgsConstructor
public enum ResultCode {

    /*
     * 通用错误码 Code约定
     * 0表示成功[SUCCESS],
     * 10000 - 19999表示业务警告[WARN_],           这种code不是常规武器，能免则免。
     * 20000 - 29999表示通用错误代码[ERR_],        各个系统通用的错误代码。
     * 30000 - 39999表示业务自定义错误代码[DIY_]
     * 40000 - 49999表示系统错误[SYS_],            系统错误单独拉出来，作为独立区域。理论上这部分也是通用的，不可以自定义。
     */
    SUCCESS("0", "操作成功"),
    ERR_LACK_PARAM("20001", "请求参数不正确"),
    ERR_NO_LOGIN("20002", "用户未登录"),
    ERR_NO_RIGHT("20003", "没有权限访问该资源"),
    ERR_NO_SERVICE("20004", "资源不存在"),
    ERR_WRONG_STATUS("20005", "资源的当前状态不支持该操作"),
    ERR_LACK_CONFIG("20006", "缺少必要的配置项"),
    ERR_PROCESS_FAIL("20007", "业务处理失败"),
    ERR_THIRD_API_FAIL("20008", "调用第三方接口失败"),
    ERR_IS_DELETED("20009", "资源已删除"),
    ERR_UPDATE_FAIL("20010", "更新操作失败"),
    SYS_MAINTENANCE("40001", "系统维护中"),
    SYS_BUSY("40002", "系统繁忙"),
    SYS_EXCEPTION("40003", "系统异常");

    private String code;
    private String msg;

    public static ResultCode get(String code) {
        ResultCode[] var1 = values();

        for (ResultCode statusEnum : var1) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }

    public String getErrorMsg(Object... params) {
        String errorMsg;
        if (params != null && params.length != 0) {
            MessageFormat msgFmt = new MessageFormat(this.msg);
            errorMsg = msgFmt.format(params);
        } else {
            errorMsg = this.msg;
        }

        return errorMsg;
    }

}
