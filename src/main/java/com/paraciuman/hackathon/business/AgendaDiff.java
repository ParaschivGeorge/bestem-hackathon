package com.paraciuman.hackathon.business;

import com.paraciuman.hackathon.model.Agenda;

public class AgendaDiff {

    public enum DiffType {
        SAME(0), MODIFIED(1), CALL_PLACES(2), CALL_WEATHER(3), CALL_BOTH(4);
        private final int value;

        private DiffType(int value) {
            this.value = value;
        }
    }

    public static DiffType agendaDiff(Agenda agenda1, Agenda agenda2) {

        if (agenda1.getLocation() != agenda2.getLocation())
            return DiffType.CALL_BOTH;
        if ((agenda1.getStartDate() != agenda2.getStartDate()) ||
                (agenda1.getEndDate() != agenda2.getEndDate()))
            return DiffType.CALL_WEATHER;
        if (agenda1.getPreferences() != agenda2.getPreferences())
            return DiffType.CALL_PLACES;
        if (agenda1.getDays() != agenda2.getDays())
            return DiffType.MODIFIED;

        return DiffType.SAME;
    }
}
