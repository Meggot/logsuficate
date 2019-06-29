package com.logsuficate;

import lombok.ToString;

@ToString
public class SensitiveField {

    public Integer userId = 1231;

    @Logsuficate("USER-PASSWORD")
    public String password = "SADASdasdasd";

    public String username = "Bradley";

    public String email = "something@email.net";

}
