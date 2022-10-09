package com.dewi.sentinel.exception;

import java.util.HashMap;

/**
 * @author MrBird
 */
public class HzfcResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public HzfcResponse success() {
        this.put("message", "成功");
        return this;
    }

    public HzfcResponse success(Object data) {
        this.put("message", "成功");
        this.put("data", data);
        return this;
    }

    public HzfcResponse fail(Object data) {
        this.put("message", "失败");
        this.put("data", data);
        return this;
    }

    public HzfcResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public HzfcResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public HzfcResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
