package com.minotore.university.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "curriculum")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter @Getter @NoArgsConstructor
public class Curriculum {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "lecture")
    private List<String> lecturesList;

}
