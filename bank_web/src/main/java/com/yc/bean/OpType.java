package com.yc.bean;


//@Data  '@Data' is only supported on a class type    “@Data”仅在类类型上受支持
public enum OpType {
    WITHDRAW("withdraw", 1),
    DEPOSITE("deposite", 2),
    TRANSFER("transfer", 3);

    private String key;
    private int value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private OpType(String key, int value) {
        this.key = key;
        this.value = value;
    }



}
