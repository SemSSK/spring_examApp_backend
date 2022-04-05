package com.example.SpringLogin.Controllers.Enseignant;

import com.example.SpringLogin.Entities.Examen;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Services.EnseignantService.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enseignant/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping("")
    public ResponseEntity<?> getExamen(@RequestBody Module module){
        try{
            return new ResponseEntity<Examen>(examenService.getModuleExam(module), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addExamen(@RequestBody Examen examen){
        try{
            examenService.addExamen(examen);
            return ResponseEntity.ok("Exam added successfuly");
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExamen(@PathVariable(name = "id") Long id){
        try{
            examenService.deleteExamen(id);
            return ResponseEntity.ok("Exam deleted successfuly");
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> modifyExamen(@RequestBody Examen examen){
        try{
            examenService.modifyExamen(examen);
            return ResponseEntity.ok("Exam Modified successfuly");
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }



}
