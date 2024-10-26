package com.iza.core.domain;

public enum Operation {
    MENU, //GET Menu hierarchy
    FULL, //FULL access operation
    INSERT,//POST
    UPDATE, //PUT
    SEARCH, //GET with params
    FIND, //GET one
    DELETE, //DELETE

    PRINT, //PRINT view/download
    EXPORT,//EXPORT csv/xls/doc
    VIEW, //VIEW readonly without SEARCH resource
    CHANGE, //PATCH
    SELECT, //GET combos/dropdown
}
