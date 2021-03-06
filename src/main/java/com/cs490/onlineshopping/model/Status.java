package com.cs490.onlineshopping.model;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    RECEIVED(0),
    INPROGRESS(1),
    CANCELED(2),
    SHIPPED(3),
    DELIVERIED(4),
    PAID(5);
	
	private int value;
    private static Map map = new HashMap<>();

    private Status(int value) {
        this.value = value;
    }

    static {
        for (Status status : Status.values()) {
            map.put(status.value, status);
        }
    }

    public static Status valueOf(int status) {
        return (Status) map.get(status);
    }

    public int getValue() {
        return value;
    }
}
