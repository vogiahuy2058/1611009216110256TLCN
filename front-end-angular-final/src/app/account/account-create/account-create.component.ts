import { Component, OnInit } from '@angular/core';
import { HeroService } from 'src/app/tabletype/hero.service';
import { Hero } from 'src/app/tabletype/hero';
import { Role } from '../role';
import { AccountRestApiService } from '../account-rest-api.service';
import { Router } from '@angular/router';
import { EmployeeRestApiService } from 'src/app/employee/employee-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';
import { EmployeetypeRestApiService } from 'src/app/employeetype/employeetype-rest-api.service';

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit {
  ordersData = [
    { name: '' },
    { name: 'admin' },
    { name: 'hr' },
    { name: 'branch manager' },
    { name: 'cashier' },
    { name: 'accountant' },
    { name: 'chef' }
  ];
  ordersDatasend = [];
  // employeeDetails = { email: '',idEmployee:null, password: '', role: [] , username:'' }
  hehe: any = {}
  name: string;
  Content:any = [];
  Contentemployee:any = [];
  Contentemployeetype:any = [];
  employeeName : string;
  heroes: Role[];
  constructor(public heroService: HeroService,
    public CheckRegion: CheckService,
    public restApi: AccountRestApiService,
    public restApi1: EmployeeRestApiService,
    public restApiEmployeetype: EmployeetypeRestApiService,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public router: Router,
    public dialogRef: MatDialogRef<AccountCreateComponent>
     ) { }

  ngOnInit() {
    this.loademployeetype()
    // this.loademployee();
  }
  send(){
    this.ordersDatasend = this.ordersData
  }
  add(name: string): void {
    name = name.trim();
    
    let customObj = new Role();
    customObj.name = name;
    for (let Role of this.heroService.empList2) {
      if (Role.name == name) {
        window.alert('Quyền đã được chọn');
        return;
      }
    }
    this.heroService.empList2.push(customObj);
    this.restApi.employeeDetails.role.push(name);
    console.log('haha' + this.restApi.employeeDetails.role)
    console.log('haha' + JSON.stringify(this.restApi.employeeDetails) )
  }

  
  delete(role: Role): void {
    this.heroService.empList2 = this.heroService.empList2.filter(h => h !== role);
    this.name = role.name
    console.log(this.name +'  ' + role.name)
    this.restApi.employeeDetails.role = this.restApi.employeeDetails.role.filter(h => h !== this.name);
    console.log('haha' + this.restApi.employeeDetails.role)
  }
  loademployee(){
    return this.restApi1.getEmployeenothaveaccountsortbyemployeetype(this.employeeName).subscribe((data: {}) => {
      this.Contentemployee = data;
    })
  }
  loademployeetype(){
    return this.restApiEmployeetype.getEmployeetypes().subscribe((data: {}) => {
      this.Contentemployeetype = data;     
    })
  }
  addEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeeDetails).subscribe((data: {}) => {
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'account';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'account';
    this.router.navigate(['/home'])
  }
  
}

