package com.commoditymanage.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 接口返回对象
 * @author ~
 * @date 2022/04/09 17:56
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private T data;

    /**
     * 请求成功
     * @author ~
     * @date 2022/04/09 17:56
     */
    public static <T> ApiResponse success(T data) {
        return ApiResponse.success("请求成功", data);
    }

    /**
     * 请求成功
     * @author ~
     * @date 2022/04/09 17:56
     */
    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(Boolean.TRUE, message, data);
    }

    /**
     * 请求失败
     * @author ~
     * @date 2022/04/09 17:56
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * 请求失败
     * @author ~
     * @date 2020-03-07 11:06
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse(Boolean.FALSE, message, null);
    }
}
