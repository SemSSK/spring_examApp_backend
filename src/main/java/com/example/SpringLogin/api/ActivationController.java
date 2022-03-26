package com.example.SpringLogin.api;

import com.example.SpringLogin.LogInWork.ActivationCode;
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


    @PostMapping("")
    public ResponseEntity<String> activate(@RequestBody Map<String,String> activation_code){

        CustomUserDetails user = contextHandlerClass.getCurrentLoggedInUser();

        String enteredCode = activation_code.get("code");
        ActivationCode code = ActivationCode.codesMap.get(user.getUsername());

        if(code != null){
            ActivationCode.codesMap.remove(user.getUsername());
            if(code.isValid() && code.getCode().equals(enteredCode)){
                contextHandlerClass.setCurrentLoggedInUserAuthorities();
                return new ResponseEntity<String>("Ok",HttpStatus.OK);
            }
            else{
                SecurityContextHolder.clearContext();
                return new ResponseEntity<String>("Activation code invalide or not logged in", HttpStatus.NOT_FOUND);
            }
        }
        else{
            SecurityContextHolder.clearContext();
            return new ResponseEntity<String>("No key", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successfully");
    }

}
