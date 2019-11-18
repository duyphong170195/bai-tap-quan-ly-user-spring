package com.example.baitapquanlyuser.controllers;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.services.MstGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Controller
public class MstGroupController {



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "group";
    }
}
