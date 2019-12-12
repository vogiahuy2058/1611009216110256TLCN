import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { CustomertypeRestApiService } from '../customertype-rest-api.service';

@Component({
  selector: 'app-customertype-edit',
  templateUrl: './customertype-edit.component.html',
  styleUrls: ['./customertype-edit.component.css']
})
export class CustomertypeEditComponent implements OnInit {

  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  
  @Input() employeetypeDetails = { id: this.id, name: '' }
  constructor(public restApi: CustomertypeRestApiService,
    public actRoute: ActivatedRoute,
    private token: TokenStorageService,
    public router: Router) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.info.token == null){
      this.router.navigate(['login'])
    }
    this.restApi.getEmployeetype(this.id).subscribe((data: {}) => {
      this.Content = data;    
      this.employeetypeDetails.name = this.Content.content.name;  
    })
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  updateEmployeetype() {
    
    if(window.confirm('Are you sure, you want to update?')){
      this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
        this.router.navigate(['/customertype-list'])
      })
    }
  }

}

