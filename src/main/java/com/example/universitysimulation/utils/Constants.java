package com.example.universitysimulation.utils;

public class Constants {
    public static String academicTitleMissingId(Long id) {
        return String.format("Academic title with id %d not found", id);
    }

    public static String academicTitleHistoryMissingId(Long id) {
        return String.format("Academic title history with id %d not found", id);
    }

    public static String memberMissingId(Long id) {
        return String.format("Member with id %d not found", id);
    }

    public static String departmentMissingId(Long id) {
        return String.format("Department with id %d not found", id);
    }

    public static String subjectMissingId(Long id) {
        return String.format("Subject with id %d not found", id);
    }

    public static String educationTitleMissingId(Long id) {
        return String.format("Education title with id %d not found", id);
    }

    public static String scientificFieldMissingId(Long id) {
        return String.format("Scientific field with id %d not found", id);
    }
}
