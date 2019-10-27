import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { TabletypeRestApiService } from '../tabletype-rest-api.service';

@Component({
  selector: 'app-tabletype-create',
  templateUrl: './tabletype-create.component.html',
  styleUrls: ['./tabletype-create.component.css']
})
export class TabletypeCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: ''}
  info: any;
  constructor(
    public restApi: TabletypeRestApiService, 
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
      this.router.navigate(['/tabletype-list'])
    })
  }

}


