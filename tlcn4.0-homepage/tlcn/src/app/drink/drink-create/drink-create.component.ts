import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DrinkRestApiService } from '../drink-rest-api.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';

@Component({
  selector: 'app-drink-create',
  templateUrl: './drink-create.component.html',
  styleUrls: ['./drink-create.component.css']
})
export class DrinkCreateComponent implements OnInit {

  @Input() employeetypeDetails = { description: '', drinkType: '', name: '' }
  info: any;
  Content: any = [];
  constructor(
    public restApi: DrinkRestApiService,
    public restApi1: DrinktypeRestApiService,
    private token: TokenStorageService,
    public router: Router
  ) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    }
    this.loadEmployeetype()
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      console.log('trong hàm' + this.Content);
    })
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/drink-list'])
    })
  }

}


