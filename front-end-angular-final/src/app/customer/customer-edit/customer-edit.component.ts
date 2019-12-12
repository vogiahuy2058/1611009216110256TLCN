import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { EmployeetypeRestApiService } from 'src/app/employeetype/employeetype-rest-api.service';
import { CustomerRestApiService } from '../customer-rest-api.service';
import { CustomertypeRestApiService } from 'src/app/customertype/customertype-rest-api.service';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent implements OnInit {

  public minDate: Date = new Date ("05/07/2017");
  public maxDate: Date = new Date ("05/27/2017");
  public dateValue: Date = new Date ("05/16/2017");
  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  Content1:any = [];
  @Input() employeetypeDetails =  { address: '', birthDay: '', customerType:'', email:'',id: this.id,name:'',note:'',phone:'',sex: true,totalPurchase: 0}
  constructor(
    public restApi: CustomerRestApiService,
    public restApi1: CustomertypeRestApiService,
    public actRoute: ActivatedRoute,
    private token: TokenStorageService,
    public router: Router) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.info.token == null){
      this.router.navigate(['login'])
    }
    this.restApi.getEmployeetype(this.id).subscribe((data: {}) => {
      this.Content = data;   
      this.employeetypeDetails.email = this.Content.content.email; 
      this.employeetypeDetails.address = this.Content.content.address; 
      this.employeetypeDetails.birthDay = this.Content.content.birthDay;
      this.employeetypeDetails.customerType = this.Content.content.customerType;
      this.employeetypeDetails.name = this.Content.content.name;
      this.employeetypeDetails.note = this.Content.content.note; 
      this.employeetypeDetails.phone = this.Content.content.phone;
      this.employeetypeDetails.sex = this.Content.content.sex;
      this.employeetypeDetails.totalPurchase = this.Content.content.totalPurchase;  
    })
    this.loadEmployeetype()
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content1);
    })
  }

  updateEmployeetype() {
    
    if(window.confirm('Are you sure, you want to update?')){
      this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
        this.router.navigate(['/customer-list'])
      })
    }
  }

}


