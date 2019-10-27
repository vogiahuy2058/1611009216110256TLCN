import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

import { MaterialRestApiService } from '../material-rest-api.service';
import { MaterialtypeRestApiService } from 'src/app/materialtype/materialtype-rest-api.service';

@Component({
  selector: 'app-material-create',
  templateUrl: './material-create.component.html',
  styleUrls: ['./material-create.component.css']
})
export class MaterialCreateComponent implements OnInit {

  @Input() employeetypeDetails = { inventory: null,materialType:'',maxInventory: null,minInventory: null,name: ''}
  info: any;
  Content: any = [];
  constructor(
    public restApi: MaterialRestApiService, 
    public restApi1: MaterialtypeRestApiService,
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

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/material-list'])
    })
  }

}



