package com.paraciuman.hackathon.requestBodyTypes;

import com.paraciuman.hackathon.model.Agenda;

public class AgendaEmailRequestBody {
    Agenda agenda;
    String email;

    public AgendaEmailRequestBody() {
    }

    public AgendaEmailRequestBody(Agenda agenda, String email) {
        this.agenda = agenda;
        this.email = email;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
