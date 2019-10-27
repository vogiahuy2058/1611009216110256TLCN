import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeetypeRestApiService } from '../employeetype-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-employeetype-create',
  templateUrl: './employeetype-create.component.html',
  styleUrls: ['./employeetype-create.component.css']
})
export class EmployeetypeCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: ''}
  info: any;
  constructor(
    public restApi: EmployeetypeRestApiService, 
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
      this.router.navigate(['/employeetype-list'])
    })
  }

}
