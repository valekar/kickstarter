import { Component, OnInit } from '@angular/core';
import { ProjectsService} from '../../services/projects.service';
import { Validators, FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ResObj,ProjectVM, KickStarter} from '../../../models/project';
import {SlimLoadingBarService} from 'ng2-slim-loading-bar';

import {  NbSpinnerService } from '@nebular/theme';
@Component({
  selector: 'app-top-projects',
  templateUrl: './top-projects.component.html',
  styleUrls: ['./top-projects.component.scss'],
  providers:[ProjectsService]
})
export class TopProjectsComponent implements OnInit {

  public myForm: FormGroup; 
  
  categories:Array<String>;
  subCategories:Array<String>;
  countries:Array<String>;
  enabled :boolean=true;
  finalResults: Array<KickStarter>;
  constructor(private projectServices:ProjectsService,private _fb:FormBuilder,private slimbar:SlimLoadingBarService) {
    
   }


  ngOnInit() {

    this.getCategories();
    this.getCountries();
    this.getSubCategories();

    this.myForm = this._fb.group({
      category:['',Validators.required],
      subCategory:['',Validators.required],
      country:['',Validators.required]
    });
  }


  getTopProjects(model: FormGroup) {
    this.enabled = false;
    this.slimbar.start();
    let projectVM = new ProjectVM();
    projectVM.category = model.controls["category"].value;
    projectVM.subCategory = model.controls["subCategory"].value;
    projectVM.country = model.controls["country"].value;

    this.projectServices.getTopProjects(projectVM).subscribe((results:Array<KickStarter>)=>{
      this.slimbar.complete();  
      this.enabled = true;
      this.finalResults = results;
    });

  }


  getCategories(){
    this.projectServices.getCategories().subscribe((results:Array<String>)=>{
        this.categories = results;
        //console.log(results);
    });
  }


  getSubCategories(){
    this.projectServices.getSubCategories().subscribe((results:Array<String>)=>{
        this.subCategories = results;
    });
  }

  getCountries(){
    this.projectServices.getCountries().subscribe((results:Array<String>)=>{
        this.countries = results;
    });
  }

}
