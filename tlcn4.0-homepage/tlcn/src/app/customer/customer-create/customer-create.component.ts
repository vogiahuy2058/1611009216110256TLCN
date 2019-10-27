import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeetypeRestApiService } from '../../employeetype/employeetype-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { CustomerRestApiService } from '../customer-rest-api.service';
import { CustomertypeRestApiService } from 'src/app/customertype/customertype-rest-api.service';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  public minDate: Date = new Date ("05/07/2017");
  public maxDate: Date = new Date ("05/27/2017");
  public dateValue: Date = new Date ("05/16/2017");
  @Input() employeetypeDetails = { address: '', birthDay:'', customerType:'', email:'',name:'',note:'',phone:'',sex: true,totalPurchase: 0}
  info: any;
  Content:any = [];
  constructor(
    public restApi: CustomerRestApiService,
    public restApi1: CustomertypeRestApiService, 
    private token: TokenStorageService,
    public router: Router
  ) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(!this.token.getToken()){
      this.router.navigate(['login'])
    }
    this.loadEmployeetype()
    
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      console.log('trong hàm'+this.Content);
    })
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/customer-list'])
    })
  }

}


