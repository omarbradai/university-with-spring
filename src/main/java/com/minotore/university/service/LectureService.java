package com.minotore.university.service;

import com.minotore.university.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LectureService {

    public static final String ROOM_CONFLICTS_KEY = "roomConflicts";
    public static final String CURRICULAR_CONFLICTS_KEY = "curricularConflicts";


    /**
     * return room of conflicts and curricular conflicts
     * @param university
     * @return a map that contains room conflicts and curricular conflicts
     */
    public List<Conflict> getConflicts(University university) {
        List<Lecture> lectureList = university.getLectures();
        List<Conflict> conflictList = new ArrayList<>();

        ListIterator<Lecture> lectureIterator = lectureList.listIterator();

        lectureIterator.forEachRemaining(lecture -> {
            List<Lecture> lecturesToCompareWith = lectureList.subList(lectureIterator.nextIndex(), lectureList.size()-1);
            ListIterator<Lecture> lectureToCompareIterator = lecturesToCompareWith.listIterator();
            lectureToCompareIterator.forEachRemaining(lectureToCheckWith -> {
                if (this.hasRoomConflict(lecture, lectureToCheckWith)) {
                    Conflict conflict = new Conflict();
                    conflict.setFirstLecture(lecture);
                    conflict.setSecondLecture(lectureToCheckWith);
                    conflict.setType(ROOM_CONFLICTS_KEY);
                    conflictList.add(conflict);
                }

                if (this.hasCurricularConflict(university.getCurriculumList(), lecture, lectureToCheckWith)) {
                    Conflict conflict = new Conflict();
                    conflict.setFirstLecture(lecture);
                    conflict.setSecondLecture(lectureToCheckWith);
                    conflict.setType(CURRICULAR_CONFLICTS_KEY);
                    conflictList.add(conflict);
                }
            });
        });

        return conflictList;
    }

    /**
     * check if there is a room conflict between the two lecture
     * @param firstLecture
     * @param secondLecture
     * @return true if a room  conflict exists
     */
    private boolean hasRoomConflict(Lecture firstLecture, Lecture secondLecture) {

        boolean hasConflict = firstLecture.getBookings().stream()
            .anyMatch(firstBooking ->
                secondLecture.getBookings().stream()
                    .anyMatch(secondBooking -> firstBooking.getWeekDay().equals(secondBooking.getWeekDay())
                                    && firstBooking.getRoom().equals(secondBooking.getRoom())
                                    && hasTimeConflict(firstBooking, secondBooking)
                    )
            );

        return hasConflict;
    }

    /**
     * check if there is a time conflict between the two lecture
     * @param firstBooking
     * @param secondBooking
     * @return true if a time  conflict exists
     */
    private boolean hasTimeConflict(Booking firstBooking, Booking secondBooking) {
        if (firstBooking.getStartTime().isBefore(firstBooking.getStartTime()) && firstBooking.getEndTime().isAfter(secondBooking.getStartTime())
                || firstBooking.getStartTime().isAfter(secondBooking.getStartTime()) && secondBooking.getEndTime().isAfter(firstBooking.getStartTime())
        ) {
            return true;
        }

        return false;
    }

    /**
     * check if there is a curricular conflict between the two lecture
     * @param curriculumList
     * @param firstLecture
     * @param secondLecture
     * @return true if a curricular conflict exists
     */
    private boolean hasCurricularConflict(List<Curriculum> curriculumList, Lecture firstLecture, Lecture secondLecture) {
        if (firstLecture.getCurriculum(curriculumList).equals(secondLecture.getCurriculum(curriculumList))) {
            boolean hasConflict =  firstLecture.getBookings().stream()
                    .anyMatch(firstBooking -> secondLecture.getBookings().stream()
                        .anyMatch(secondBooking -> firstBooking.getWeekDay().equals(secondBooking.getWeekDay()) && this.hasTimeConflict(firstBooking, secondBooking)));

            return hasConflict;
        }

        return false;
    }
}
