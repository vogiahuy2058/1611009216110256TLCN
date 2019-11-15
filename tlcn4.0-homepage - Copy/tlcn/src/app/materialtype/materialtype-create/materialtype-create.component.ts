import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { MaterialtypeRestApiService } from '../materialtype-rest-api.service';

@Component({
  selector: 'app-materialtype-create',
  templateUrl: './materialtype-create.component.html',
  styleUrls: ['./materialtype-create.component.css']
})
export class MaterialtypeCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: ''}
  info: any;
  constructor(
    public restApi: MaterialtypeRestApiService, 
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
      this.router.navigate(['/materialtype-list'])
    })
  }

}

