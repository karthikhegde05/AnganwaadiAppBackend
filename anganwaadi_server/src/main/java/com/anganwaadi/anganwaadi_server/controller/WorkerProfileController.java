// package com.anganwaadi.anganwaadi_server.controller;

// import java.util.Optional;

// import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
// import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @RestController
// @CrossOrigin("http://localhost:8100")
// public class WorkerProfileController {
//     private AnganwaadiWorkerService anganwaadiWorkerService;
//     private AnganwaadiWorker loggedWorker;

//     public WorkerProfileController(AnganwaadiWorkerService anganwaadiWorkerService){
//         this.anganwaadiWorkerService = anganwaadiWorkerService;
//         this.loggedWorker = null;
//     }

//     @RequestMapping(value="/workerProfile/{awwId}", method=RequestMethod.GET)
//     public @ResponseBody SendWorkerProfileDTO fetchWorkerProfileDetails(@PathVariable String awwId){
//         Long givenAwwId = Long.parseLong(awwId);

//         Optional<AnganwaadiWorker> worker = anganwaadiWorkerService.getWorkerById(givenAwwId);
//         SendWorkerProfileDTO nwWorker = new SendWorkerProfileDTO();

//         if(worker.isEmpty()){
//             nwWorker.setNullObj(true);
//         }

//         else {
//             AnganwaadiWorker awWorker = worker.get();
//             nwWorker.setNullObj(false);
//             nwWorker.setName(awWorker.getName());
//             nwWorker.setContactNumber(awWorker.getContactNumber());
//             nwWorker.setAnganwaadiAddress(awWorker.getAnganwaadi().getAddress());
//             nwWorker.setAnganwaadiLocation(awWorker.getAnganwaadi().getLocation());
//             nwWorker.setUsername(awWorker.getRegDetails().getUserId());
//             nwWorker.setEmail(awWorker.getRegDetails().getEmail());
//             nwWorker.setRole(awWorker.getRegDetails().getRole());
//         }


//         return nwWorker;
//     }
// }


// @Data
// @Getter @Setter @AllArgsConstructor @NoArgsConstructor
// class SendWorkerProfileDTO{
//     private boolean nullObj;
//     private String name;
//     private String contactNumber;
    
//     private String username;
//     private String email;
//     private String role;

//     private String anganwaadiAddress;
//     private String anganwaadiLocation;

// }
