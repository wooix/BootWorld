package com.example.bootworld.welcome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootworld.welcome.model.outDTO;
import com.example.bootworld.welcome.model.welcomeDTO;



@RestController
@RequestMapping("/welcome")

public class WelcomeController {
    // case1. simple String response

    // person/John
    @RequestMapping(value = "/person/{param}", method=RequestMethod.GET)
    public String getPerson(@PathVariable String param) {
        return new String("Welcome to BootWorld " + param);
    }
    /* 
     * postMapping("/insert") 
     * {
     *  "name": "John",
     *  "age": "30"
     * }
    */
    // json/insert
    @PostMapping("/person")
    public String insertPerson(@RequestBody welcomeDTO dto) {
        String msg = new String("Name: " + dto.getName() + " Age: " + dto.getAge());
        System.out.println(msg);
        return msg;
    }

    // case2. json request/response

    // json/person?name=John&age=30
    @GetMapping("/json/person")
    public @ResponseBody outDTO getPersonJson (@RequestParam("name") String name, @RequestParam("age") String age) {
        String msg = new String("Name: " + name + " Age: " + age);
        outDTO out = new outDTO();
        out.setMsg(msg);
        return out;
    }
    
    // json/person
    @PostMapping("/json/person")
    public @ResponseBody outDTO insertPersonJson(@RequestBody welcomeDTO dto) {
        String msg = new String("Name: " + dto.getName() + " Age: " + dto.getAge());
        outDTO out = new outDTO();
        out.setMsg(msg);
        return out;
    }
    

    // case3. entity response
    // ResponseEntity를 이용하여 http status code를 설정할 수 있다.

    @GetMapping("/entity/person")
    public ResponseEntity<outDTO> getPersonEntity (@RequestParam("name") String name, @RequestParam("age") String age) {
        String msg = new String("Name: " + name + " Age: " + age);
        outDTO out = new outDTO();
        out.setMsg(msg);
        // return ResponseEntity.ok(out);
        return ResponseEntity.badRequest().body(out);
    }
    
    @PostMapping("/entity/person")
    public ResponseEntity<outDTO> insertPersonEntity(@RequestBody welcomeDTO dto) {
        String msg = new String("Name: " + dto.getName() + " Age: " + dto.getAge());
        outDTO out = new outDTO();
        out.setMsg(msg);
        //return ResponseEntity.ok(out);
        return ResponseEntity.notFound().build();
    }
    

}
	