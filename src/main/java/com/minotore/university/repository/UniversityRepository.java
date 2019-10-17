package com.minotore.university.repository;

import com.minotore.university.model.University;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * this class is used to access file and map data from it
 */
@Repository
public class UniversityRepository {

    /**
     *
     * @param path file's path
     * @return University object mapped from file
     * @throws JAXBException
     */
    public static University mapDataFromFile(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(University.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        System.out.println("Output from our XML File: ");
        Unmarshaller um = context.createUnmarshaller();
        File file = new File(path);
        University university = (University) um.unmarshal(file);

        return university;
    }
}
