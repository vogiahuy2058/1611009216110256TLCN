import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DrinktypeRestApiService } from '../drinktype-rest-api.service';

@Component({
  selector: 'app-drinktype-create',
  templateUrl: './drinktype-create.component.html',
  styleUrls: ['./drinktype-create.component.css']
})
export class DrinktypeCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: ''}
  info: any;
  constructor(
    public restApi: DrinktypeRestApiService, 
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
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/drinktype-list'])
    })
  }

}


