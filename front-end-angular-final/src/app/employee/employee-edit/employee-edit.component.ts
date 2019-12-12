import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeRestApiService } from '../employee-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { EmployeetypeRestApiService } from 'src/app/employeetype/employeetype-rest-api.service';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {

  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  Content1:any = [];
  Content2:any = [];
  @Input() employeetypeDetails = { branchShop: '', email:'', employeeType:'', id: this.id, name:'' }
  constructor(
    public restApi2: BranchshopRestApiService,
    public restApi: EmployeeRestApiService,
    public restApi1: EmployeetypeRestApiService,
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
      this.employeetypeDetails.branchShop = this.Content.content.branchShop;
      this.employeetypeDetails.email = this.Content.content.email;
      this.employeetypeDetails.employeeType = this.Content.content.employeeType; 
    })
    this.loadEmployeetype()
    this.loadEmployeetype1()
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content);
    })
  }
  loadEmployeetype1() {
    return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content2 = data;
      console.log('trong hàm'+this.Content1);
    })
  }

  updateEmployeetype() {
    
    if(window.confirm('Are you sure, you want to update?')){
      this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
        this.router.navigate(['/employee-list'])
      })
    }
  }

}

