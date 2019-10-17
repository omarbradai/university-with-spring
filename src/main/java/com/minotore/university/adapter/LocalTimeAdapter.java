package com.minotore.university.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {
    public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v);
    }

    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }
}
