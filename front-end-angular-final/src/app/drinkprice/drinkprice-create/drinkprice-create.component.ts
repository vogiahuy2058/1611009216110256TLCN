import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';
import { RestApiDrinkpriceService } from '../rest-api-drinkprice.service';
import { DrinkRestApiService } from 'src/app/drink/drink-rest-api.service';
@Component({
  selector: 'app-drinkprice-create',
  templateUrl: './drinkprice-create.component.html',
  styleUrls: ['./drinkprice-create.component.css']
})
export class DrinkpriceCreateComponent implements OnInit {

  @Input() employeetypeDetails = { date: '',drinkId: null,initialPrice:null,price: null}
  info: any;
  Content: any = [];
  Content1: any = {};
  constructor(
    public restApi: RestApiDrinkpriceService, 
    public restApi1: DrinkRestApiService,
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
    this.loadtabletype();
  }
  loadtabletype(){
    this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
    })
  }

  addEmployeetype(dataEmployeetype) {
    // this.restApi.getEmployeetype(this.employeetypeDetails.drinkId).subscribe((data: {}) => {
    //   this.Content1 = data; 
    //   if(this.Content1.content.materialId == this.employeetypeDetails.drinkId){
    //     window.alert('Bảng giá đã tồn tại vui lòng kiểm tra lại!');
    //   }else{
        this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
         this.router.navigate(['/home'])
      //   })
      // } 
    })
    
    
  }

}





