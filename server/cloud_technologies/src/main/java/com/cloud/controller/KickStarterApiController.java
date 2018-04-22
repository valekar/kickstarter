package com.cloud.controller;

import com.cloud.ViewModel.ProjectVM;
import com.cloud.model.KickStarter;
import com.cloud.model.PledgeState;
import com.cloud.model.TotalPledge;
import com.cloud.service.IKickStarterService;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.plans.logical.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;


@RestController
@RequestMapping("/api")
public class KickStarterApiController {

    private IKickStarterService starterService;

    @Autowired
    public KickStarterApiController(IKickStarterService kickStarterService){
        this.starterService = kickStarterService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    Callable<ResponseEntity<String>> testMe() {
        this.starterService.test();
        return () -> ResponseEntity.ok("test");
    }


    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    Callable<ResponseEntity<List<KickStarter>>> getProjects(@RequestBody ProjectVM projectVM) {
        List<KickStarter> kickStarterList = this.starterService.findProjects(projectVM);
        /*for(Row row : kickStarterList){
            System.out.println(row);
        }*/
        System.out.println("++++++++++++++++++++++++++");
        System.out.println(kickStarterList.size());
        return () -> ResponseEntity.ok(kickStarterList);
    }

    @RequestMapping(value = "/category/total/pledge",method = RequestMethod.POST)
    Callable<ResponseEntity<List<TotalPledge>>> getTotalAmountByCategory(@RequestBody ProjectVM projectVM) {
        List<TotalPledge> totalPledges = this.starterService.getTotalAmountByCategory(projectVM);
        return () -> ResponseEntity.ok(totalPledges);
    }


    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    Callable<ResponseEntity<List<String>>> getCategoryList(){
        List<String> categoryList = this.starterService.getCategoryList();
        return ()-> ResponseEntity.ok(categoryList);
    }

    @RequestMapping(value = "/sub/categories",method = RequestMethod.GET)
    Callable<ResponseEntity<List<String>>> getSubCategoryList(){
        List<String> subCategoryList = this.starterService.getSubCategoryList();
        return ()-> ResponseEntity.ok(subCategoryList);
    }


    @RequestMapping(value = "/countries",method = RequestMethod.GET)
    Callable<ResponseEntity<List<String>>> getCountryList(){
        List<String> countryList = this.starterService.getCountryList();
        return ()-> ResponseEntity.ok(countryList);
    }

    @RequestMapping(value = "/projects/state",method = RequestMethod.POST)
    Callable<ResponseEntity<List<PledgeState>>> getPledgeState(@RequestBody ProjectVM projectVM){
        List<PledgeState> pledgedState = this.starterService.getPledgeState(projectVM);
        return ()-> ResponseEntity.ok(pledgedState);
    }


}
