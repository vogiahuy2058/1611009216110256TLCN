import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { SupplycontractdetailRestApiService } from '../supplycontractdetail-rest-api.service';
import { SupplycontractRestApiService } from 'src/app/supplycontract/supplycontract-rest-api.service';
import { MaterialRestApiService } from 'src/app/material/material-rest-api.service';
import { MaterialpriceRestApiService } from 'src/app/materialprice/materialprice-rest-api.service';

@Component({
  selector: 'app-supplycontractdetail-create',
  templateUrl: './supplycontractdetail-create.component.html',
  styleUrls: ['./supplycontractdetail-create.component.css']
})
export class SupplycontractdetailCreateComponent implements OnInit {

  @Input() employeetypeDetails = { amount: null, deliveryTime:'', materialId:null, paymentTime:'', supplyContractId: null, unitPrice:null}
  info: any;
  Content:any = [];
  Content1:any = [];
  Content2:any = [];
  constructor(
    public restApi2: MaterialRestApiService,
    public restApi3: MaterialpriceRestApiService,
    public restApi: SupplycontractdetailRestApiService,
    public restApi1: SupplycontractRestApiService, 
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
    this.loadEmployeetype1()
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      console.log('trong hàm'+this.Content);
    })
  }
  loadEmployeetype1() {
    return this.restApi3.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content1);
    })
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/supplycontract-list'])
    })
  }

  loadlaitrang(){
    //load đơn vị theo bảng giá
    var d = new Date(this.employeetypeDetails.paymentTime);
    this.restApi3.getEmployeetype(this.employeetypeDetails.materialId).subscribe((data: {}) => {
      this.Content2 = data;   
      this.employeetypeDetails.unitPrice = this.Content2.content.price; 
    })
    // định chuẩn ngày
    d.setHours(0, -d.getTimezoneOffset(), 0 , 0)
    console.log(d.toISOString());
  }

}



