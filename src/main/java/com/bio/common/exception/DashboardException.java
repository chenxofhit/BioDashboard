package com.bio.common.exception;

/**
 * <pre>
 * 应用运行时异常，统一抛此类
 * </pre>
 * 
 * <small> 2018年4月6日 | Aron</small>
 */
public class DashboardException extends RuntimeException {

    private static final long serialVersionUID = 6403925731816439878L;

    public DashboardException() {
        super();
    }

    public DashboardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DashboardException(String message, Throwable cause) {
        super(message, cause);
    }

    public DashboardException(String message) {
        super(message);
    }

    public DashboardException(Throwable cause) {
        super(cause);
    }

}
