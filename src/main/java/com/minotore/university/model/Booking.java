

package com.minotore.university.model;

import com.minotore.university.adapter.LocalTimeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;


@XmlRootElement(name = "booking")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter @NoArgsConstructor
public class Booking {

    @XmlElement(name = "room")
    private String room;

    @XmlElement(name = "weekday")
    private String weekDay;

    @XmlJavaTypeAdapter(type = LocalTime.class, value = LocalTimeAdapter.class)
    @XmlElement(name = "startTime")
    private LocalTime startTime;

    @XmlJavaTypeAdapter(type = LocalTime.class, value = LocalTimeAdapter.class)
    @XmlElement(name = "endTime")
    private LocalTime endTime;

    private Lecture lecture;

}
