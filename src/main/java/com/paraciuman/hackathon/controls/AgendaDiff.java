package com.paraciuman.hackathon.controls;

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

        if (agenda)

        return DiffType.SAME;
    }
}
