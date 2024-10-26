package com.iza.modules;

public enum Entitys {
    PRODUCT("Product"),
    CUSTOMER("Customer"),
    ;

    private String name;
    private Entitys( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getPlural(){
        return name.concat("s");
    }
}
