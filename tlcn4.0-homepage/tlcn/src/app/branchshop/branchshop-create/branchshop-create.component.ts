import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { BranchshopRestApiService } from '../branchshop-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-branchshop-create',
  templateUrl: './branchshop-create.component.html',
  styleUrls: ['./branchshop-create.component.css']
})
export class BranchshopCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: '', address:''}
  info: any;
  constructor(
    public restApi: BranchshopRestApiService, 
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
      this.router.navigate(['/branchshop-list'])
    })
  }

}

