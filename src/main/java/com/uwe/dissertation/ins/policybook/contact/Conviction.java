package com.uwe.dissertation.ins.policybook.contact;

import java.time.LocalDate;

public class Conviction {

    private String code;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return String.format("code:%s date:%s", code, date.toString());
    }
}
