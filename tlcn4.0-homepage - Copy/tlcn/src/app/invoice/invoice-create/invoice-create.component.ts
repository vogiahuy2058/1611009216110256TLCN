import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { InvoiceRestApiService } from '../invoice-rest-api.service';
import { OrdertypeRestApiService } from 'src/app/ordertype/ordertype-rest-api.service';

@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.css']
})
export class InvoiceCreateComponent implements OnInit {

  @Input() employeetypeDetails = { branchShop: '', coffeeTable:'', customerPhone:'', numberPosition: 0, orderType: '',
   paymentStatus: false, totalDiscount: 0, totalPrice: 0, vat: 0 }
  info: any;
  Content:any = [];
  Content1:any = [];
  constructor(
    public restApi2: BranchshopRestApiService,
    public restApi: InvoiceRestApiService,
    public restApi1: OrdertypeRestApiService, 
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
      this.router.navigate(['/invoice-list'])
    })
  }

}



