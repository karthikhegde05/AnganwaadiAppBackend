package com.anganwaadi.anganwaadi_server.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    
    private List<AnganwaadiWorker> workers = new ArrayList<AnganwaadiWorker>();

    public MainController(){
        workers.addAll(List.of(
            new AnganwaadiWorker(),
            new AnganwaadiWorker()
        ));
    }

    @GetMapping("/home/{id}")
    Optional<AnganwaadiWorker> getWorker(@PathVariable String id){
        for(AnganwaadiWorker wk: workers){
            if(wk.getId().equals(id))
                return Optional.of(wk);
        }

        return Optional.empty();
    }


    @GetMapping("/app/{id}")
    Optional<AnganwaadiWorker> getWorkerByIndex(@PathVariable int id){
        if(id < workers.size())
            return Optional.of(workers.get(id));

        return Optional.empty();
    }
}
