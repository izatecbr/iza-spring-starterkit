package com.iza.modules.access;

public enum Functionality {
    CUSTOMERS("CUSTOMERS","Cadastro de clientes"),
    PRODUCTS("PRODUCTS","Cadastro de produtos"),
    USERS("USERS","Gestão de usuários");

    private String id;
    private String label;

    private Functionality(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }
}
