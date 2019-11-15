import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { SupplierRestApiService } from 'src/app/supplier/supplier-rest-api.service';
import { SupplycontractRestApiService } from '../supplycontract-rest-api.service';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-supplycontract-create',
  templateUrl: './supplycontract-create.component.html',
  styleUrls: ['./supplycontract-create.component.css']
})
export class SupplycontractCreateComponent implements OnInit {

  @Input() employeetypeDetails = { branchShop: '', date:'', supplier:'', totalPrice:null}
  @Input() employeetypeDetaillist = {  amount: null, deliveryTime:'', materialId:null, paymentTime:'', supplyContractId: null, unitPrice:null}

  info: any;
  Content2:any = [this.employeetypeDetaillist];
  Content:any = [];
  Content1:any = [];
  constructor(
    public restApi2: BranchshopRestApiService,
    public restApi: SupplycontractRestApiService,
    public restApi1: SupplierRestApiService, 
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
    return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content1);
    })
  }

  addEmployeetype(dataEmployeetype) {
    
    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/supplycontract-list'])
    })
  }

}


