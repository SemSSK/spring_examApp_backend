package com.example.SpringLogin.Controllers.Enseignant;

import com.example.SpringLogin.Entities.Examen;
import com.example.SpringLogin.Services.EnseignantService.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/enseignant/correction")
public class CorrectionController {

    @Autowired
    private CorrectionService correctionService;

    @GetMapping("")
    public ResponseEntity<?> getCopies(@RequestBody Examen examen){
        try{
            return new ResponseEntity<>(correctionService.getCopies(examen), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }



}
