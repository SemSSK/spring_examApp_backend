package com.example.SpringLogin.api;

import com.example.SpringLogin.LogInWork.ActivationCodeService;
import com.example.SpringLogin.LogInWork.ContextHandlerClass;
import com.example.SpringLogin.LogInWork.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/activate")
public class ActivationController {

    @Autowired
    private ContextHandlerClass contextHandlerClass;

    @Autowired
    private ActivationCodeService activationCodeService;

    @PostMapping("")
    public ResponseEntity<String> activate(@RequestBody Map<String,String> activation_code){

        CustomUserDetails user = contextHandlerClass.getCurrentLoggedInUser();

        String enteredCode = activation_code.get("code");

        if(activationCodeService.treatCode(user.getUsername(),enteredCode)){
            contextHandlerClass.setCurrentLoggedInUserAuthorities();
            return new ResponseEntity<String>("Ok",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalide activation code", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successfully");
    }

}
