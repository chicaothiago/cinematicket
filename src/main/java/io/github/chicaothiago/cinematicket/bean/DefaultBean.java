package io.github.chicaothiago.cinematicket.bean;

import java.util.Calendar;

public class DefaultBean {
    public Calendar created_at;
    public Calendar updated_at;
    public Calendar deleted_at;
    public Integer countFields = 3;

    public Calendar getCreated_at() {
        return created_at;
    }

    public Calendar getUpdated_at() {
        return updated_at;
    }

    public Calendar getDeleted_at() {
        return deleted_at;
    }

    public Integer getCountFields() {
        return countFields;
    }
}
