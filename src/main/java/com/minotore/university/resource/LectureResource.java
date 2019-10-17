package com.minotore.university.resource;

import com.minotore.university.model.Conflict;
import com.minotore.university.model.University;
import com.minotore.university.repository.UniversityRepository;
import com.minotore.university.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureResource {

    private static final String TIMETABLE_XML = "src/main/resources/timetable.xml";

    @Autowired
    private LectureService lectureService;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/conflicts")
    public ResponseEntity<List<Conflict>> getConflicts() {
        University university = new University();

        try {
            university = this.universityRepository.mapDataFromFile(TIMETABLE_XML);
        } catch (JAXBException e) {
            return ResponseEntity.notFound().build();
        }
        List<Conflict> conflicts = this.lectureService.getConflicts(university);

        return ResponseEntity.ok(conflicts);
    }
}
