import { Component, OnInit } from '@angular/core';
import { ProjectsService} from '../../services/projects.service';
import { Validators, FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ResObj,ProjectVM, PledgeState} from '../../../models/project';
import {SlimLoadingBarService} from 'ng2-slim-loading-bar';

@Component({
  selector: 'app-pledge-states',
  templateUrl: './pledge-states.component.html',
  styleUrls: ['./pledge-states.component.scss'],
  providers:[ProjectsService]
})
export class PledgeStatesComponent implements OnInit {
  public myForm: FormGroup; 
  
  categories:Array<String>;
  state:Array<String>;
  enabled :boolean=true;
  finalResults: Array<PledgeState>
  constructor(private projectServices:ProjectsService,private _fb:FormBuilder,private slimbar:SlimLoadingBarService) { }

  ngOnInit() {
    this.getCategories();
    this.getStates();

    this.myForm = this._fb.group({
      category:['',Validators.required],
      state:['',Validators.required]
    });
  }

  getPledgeState(model: FormGroup) {
    this.enabled = false;
    this.slimbar.start();
    let projectVM = new ProjectVM();
    projectVM.category = model.controls["category"].value;
    projectVM.state = model.controls["state"].value;

    this.projectServices.getPledgeState(projectVM).subscribe((results:Array<PledgeState>)=>{
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

  getStates(){
    this.projectServices.getStates().subscribe((results:Array<String>)=>{
        this.state = results;
        //console.log(results);
    });

  }
}
