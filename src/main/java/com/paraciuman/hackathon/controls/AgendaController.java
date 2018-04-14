package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Preference;
import com.paraciuman.hackathon.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/agenda/add/{email}")
    public void addAgenda(@PathVariable("email") String email){

    }

    @RequestMapping(method = RequestMethod.POST, value = "/agenda/update")
    public void updateAgenda(@RequestBody final Agenda agenda){
        if(agendaRepository.findById(agenda.getId()) == null)
            return;
        agendaRepository.save(agenda);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/agenda/delete/{id}")
    public void deleteAgenda(@PathVariable("id") long id){
        if(agendaRepository.findById(id) == null){
            return;
        }
        agendaRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/agenda/get/{id}")
    public Agenda getAgenda(@PathVariable("id") long id){
        Agenda agenda = agendaRepository.findById(id);
        if(agenda == null){
            return null;
        }
        return agenda;
    }

    /*@RequestMapping(method = RequestMethod.POST,value = "/agenda/up/{email}")
    public
*/
}
