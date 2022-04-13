package com.example.SpringLogin.Controllers.Etudiant;

import com.example.SpringLogin.Services.EtudiantService.CopieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiant/copie")
public class CopieController {

    @Autowired
    private CopieService copieService;

    @GetMapping("")
    public ResponseEntity<?> sendCopie(){
        return null;
    }
}
