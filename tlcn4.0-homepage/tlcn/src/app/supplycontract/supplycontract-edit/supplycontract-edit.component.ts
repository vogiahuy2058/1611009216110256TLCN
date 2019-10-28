import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { SupplycontractRestApiService } from '../supplycontract-rest-api.service';
import { SupplierRestApiService } from 'src/app/supplier/supplier-rest-api.service';

@Component({
  selector: 'app-supplycontract-edit',
  templateUrl: './supplycontract-edit.component.html',
  styleUrls: ['./supplycontract-edit.component.css']
})
export class SupplycontractEditComponent implements OnInit {

  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  Content1:any = [];
  Content2:any = [];
  @Input() employeetypeDetails = { branchShop: '', date:'',id: this.id, supplier:'', totalPrice:null }
  constructor(
    public restApi2: BranchshopRestApiService,
    public restApi: SupplycontractRestApiService,
    public restApi1: SupplierRestApiService,
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
      this.employeetypeDetails.branchShop = this.Content.content.branchShop; 
      this.employeetypeDetails.date = this.Content.content.date;
      this.employeetypeDetails.supplier = this.Content.content.supplier;
      this.employeetypeDetails.totalPrice = this.Content.content.totalPrice; 
    })
    this.loadEmployeetype()
    this.loadEmployeetype1()
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content1);
    })
  }
  loadEmployeetype1() {
    return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content2 = data;
      console.log('trong hàm'+this.Content2);
    })
  }

  updateEmployeetype() {
    
    if(window.confirm('Are you sure, you want to update?')){
      this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
        this.router.navigate(['/supplycontract-list'])
      })
    }
  }

}


