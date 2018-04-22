import { Injectable } from '@angular/core';
import {CommonService} from '../../services/common.service';
import {ProjectVM} from '../../models/project';
import {Http,Response} from '@angular/http';
import {URLS} from '../../models/URLS';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { Observable } from 'rxjs/Observable';
import {  NbSpinnerService } from '@nebular/theme';
@Injectable()
export class ProjectsService {

  constructor(private http:Http,private commonService:CommonService) { 
    //this.spinner.registerLoader();
   // this.spinner.load();
  }

  getTopProjects(postVM:ProjectVM){
    //this.spinner.load();
    return this.http.post(URLS.GET_TOP_PROJECTS,postVM,this.commonService.getHeaderOptions())
      .map((res:Response)=> {
        return res.json()}
      )
      .catch((err:Response)=> {return Observable.throw(err.json())});
  }


  getTotalPledged(postVM:ProjectVM){
    return this.http.post(URLS.GET_TOTAL_PLEDGED,postVM,this.commonService.getHeaderOptions())
      .map((res:Response)=> {
        return res.json()}
      )
      .catch((err:Response)=> {return Observable.throw(err.json())});
  }

  getPledgeState(postVM:ProjectVM){
    return this.http.post(URLS.GET_PLEDGED_STATE,postVM,this.commonService.getHeaderOptions())
      .map((res:Response)=> {
        return res.json()}
      )
      .catch((err:Response)=> {return Observable.throw(err.json())});
  }

  
  getCategories(){
    return this.http.get(URLS.GET_CATEGORIES,this.commonService.getHeaderOptions())
    .map((res:Response)=>{ return res.json()})
    .catch((err:Response)=> {return Observable.throw(err.json())});
  }

  getSubCategories(){
    return this.http.get(URLS.GET_SUB_CATEGORIES,this.commonService.getHeaderOptions())
    .map((res:Response)=>{ return res.json()})
    .catch((err:Response)=> {return Observable.throw(err.json())});
  }


  getCountries(){
    return this.http.get(URLS.GET_COUNTRIES,this.commonService.getHeaderOptions())
    .map((res:Response)=>{ return res.json()})
    .catch((err:Response)=> {return Observable.throw(err.json())});
  }

  getStates(){
    return this.http.get(URLS.GET_COUNTRIES,this.commonService.getHeaderOptions())
    .map((res:Response)=>{ return res.json()})
    .catch((err:Response)=> {return Observable.throw(err.json())});
  }

}
