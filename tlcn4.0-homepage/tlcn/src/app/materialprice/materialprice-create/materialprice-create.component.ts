import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { MaterialpriceRestApiService } from '../materialprice-rest-api.service';
import { MaterialRestApiService } from 'src/app/material/material-rest-api.service';

@Component({
  selector: 'app-materialprice-create',
  templateUrl: './materialprice-create.component.html',
  styleUrls: ['./materialprice-create.component.css']
})
export class MaterialpriceCreateComponent implements OnInit {

  @Input() employeetypeDetails = { date: '',materialId: null,price: null}
  info: any;
  Content: any = [];
  Content1: any = {};
  constructor(
    public restApi: MaterialpriceRestApiService, 
    public restApi1: MaterialRestApiService,
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
    this.restApi.getEmployeetype(this.employeetypeDetails.materialId).subscribe((data: {}) => {
      this.Content1 = data; 
      if(this.Content1.content.materialId == this.employeetypeDetails.materialId){
        window.alert('Bảng giá đã tồn tại vui lòng kiểm tra lại!');
      }else{
        this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
         this.router.navigate(['/materialprice-list'])
        })
      } 
    })
    
    
  }

}




