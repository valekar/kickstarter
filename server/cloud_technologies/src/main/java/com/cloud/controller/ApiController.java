package com.cloud.controller;


import com.cloud.model.Count;
import com.cloud.service.IWordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Example for showing the use of Word count</p>
 */
@RequestMapping("/api")
@RestController
public class ApiController {

    IWordCountService iWordCountService;

    @Autowired
    public ApiController(IWordCountService iWordCountService){
        this.iWordCountService = iWordCountService;
    }

    @RequestMapping(value = "/wordcount", method = RequestMethod.GET)
    Callable<ResponseEntity<List<Count>>> wordCount() {
       List<Count> wordCounts = iWordCountService.count();
        return () -> ResponseEntity.ok(wordCounts);
    }
}