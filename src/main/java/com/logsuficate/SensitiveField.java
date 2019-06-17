package com.logsuficate;

import lombok.ToString;

@ToString
public class SensitiveField {

    @Logsuficate("_USER_PASSWORD_")
    public String password = "SADASdasdasd";

}
