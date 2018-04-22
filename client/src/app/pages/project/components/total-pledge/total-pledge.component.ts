import { Component, OnInit } from '@angular/core';
import { ProjectsService} from '../../services/projects.service';
import { Validators, FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ResObj,ProjectVM, TotalPledge} from '../../../models/project';
import {SlimLoadingBarService} from 'ng2-slim-loading-bar';

@Component({
  selector: 'app-total-pledge',
  templateUrl: './total-pledge.component.html',
  styleUrls: ['./total-pledge.component.scss'],
  providers:[ProjectsService]
})
export class TotalPledgeComponent implements OnInit {
  public myForm: FormGroup; 
  
  categories:Array<String>;
  subCategories:Array<String>;
  countries:Array<String>;
  enabled :boolean=true;
  finalResults: Array<TotalPledge>;
  constructor(private projectServices:ProjectsService,private _fb:FormBuilder,private slimbar:SlimLoadingBarService) { }

  ngOnInit() {
    this.getCategories();
    this.getCountries();

    this.myForm = this._fb.group({
      category:['',Validators.required],
      country:['',Validators.required]
    });
  }


  getTotalPlege(model: FormGroup) {
    this.enabled = false;
    this.slimbar.start();
    let projectVM = new ProjectVM();
    projectVM.category = model.controls["category"].value;
    projectVM.country = model.controls["country"].value;

    this.projectServices.getTotalPledged(projectVM).subscribe((results:Array<TotalPledge>)=>{
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

  getCountries(){
    this.projectServices.getCountries().subscribe((results:Array<String>)=>{
        this.countries = results;
    });
  }

}
