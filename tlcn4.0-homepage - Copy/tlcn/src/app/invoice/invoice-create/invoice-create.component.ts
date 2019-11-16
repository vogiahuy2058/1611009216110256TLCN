import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { InvoiceRestApiService } from '../invoice-rest-api.service';
import { OrdertypeRestApiService } from 'src/app/ordertype/ordertype-rest-api.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';
import { DrinkRestApiService } from 'src/app/drink/drink-rest-api.service';
import { Drink } from 'src/app/drink/drink';

@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.css']
})
export class InvoiceCreateComponent implements OnInit {

  @Input() employeetypeDetails = { branchShop: '' }
  info: any;
  ContentList: Drink[];
  ContentDrinktype: any = [];
  ContentDrink: any = [];
  constructor(
    public restApiDrinktype: DrinktypeRestApiService,
    public restApiDrink: DrinkRestApiService, 
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
    // this.loadEmployeetype()
    // this.loadEmployeetype1()
    this.loadDrinktype()
    
  }
  // loadEmployeetype() {
  //   return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
  //     this.Content = data;
  //     console.log('trong hàm'+this.Content);
  //   })
    
  // }
  // loadEmployeetype1() {
  //   return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
  //     this.Content1 = data;
  //     console.log('trong hàm'+this.Content1);
  //   })
  // }
  loadDrinktype() {
    return this.restApiDrinktype.getEmployeetypes().subscribe((data: {}) => {
      this.ContentDrinktype = data;
    })
  }
  loadDrinkBySort(name){
    console.log('vô rồi nè');
    return this.restApiDrink.getEmployeetypessort(name).subscribe((data: {}) => {
      this.ContentDrink = data;
      console.log('vô rồi nè mày' + this.ContentDrink);
    })
  }
  loadDrink(){
    return this.restApiDrink.getEmployeetypes().subscribe((data: {}) => {
      this.ContentDrink = data;
    })
  }

  // addEmployeetype(dataEmployeetype) {
    
  //   this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
  //     this.router.navigate(['/invoice-list'])
  //   })
  // }
  loadlaitrang(){
    console.log(this.employeetypeDetails.branchShop);
    if(this.employeetypeDetails.branchShop == "1"){
      this.loadDrink();
      console.log('vô rồi');
    }else{
      this.loadDrinkBySort(this.employeetypeDetails.branchShop);
      console.log('ra rồi');
    }
   
  }

}



