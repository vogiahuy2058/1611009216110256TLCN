import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

import { MaterialRestApiService } from '../material-rest-api.service';
import { MaterialtypeRestApiService } from 'src/app/materialtype/materialtype-rest-api.service';
import { UnitRestApiService } from 'src/app/unit/unit-rest-api.service';

@Component({
  selector: 'app-material-create',
  templateUrl: './material-create.component.html',
  styleUrls: ['./material-create.component.css']
})
export class MaterialCreateComponent implements OnInit {

  @Input() employeetypeDetails = { inventory: null,materialType:'',maxInventory: null,minInventory: null,name: '', unit: ''}
  info: any;
  Content: any = [];
  Content1: any = [];
  constructor(
    public restApi: MaterialRestApiService, 
    public restApi1: MaterialtypeRestApiService,
    public restApi2: UnitRestApiService,
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
    this.loadtabletype1();
  }
  loadtabletype(){
    this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
    })
  }
  loadtabletype1(){
    this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
    })
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/material-list'])
    })
  }

}



