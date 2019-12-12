import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeetypeRestApiService } from '../../employeetype/employeetype-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { CustomerRestApiService } from '../customer-rest-api.service';
import { CustomertypeRestApiService } from 'src/app/customertype/customertype-rest-api.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';
import { CheckService } from 'src/app/check.service';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  public minDate: Date = new Date("05/07/2017");
  public maxDate: Date = new Date("05/27/2017");
  public dateValue: Date = new Date("05/16/2017");
  // @Input() employeetypeDetails = { address: '', birthDay:'', customerType:'', email:'',name:'',note:'',phone:'',sex: true,totalPurchase: 0}
  id: any;
  info: any;
  Content: any = [];
  constructor(
    public CheckRegion: CheckService,
    //popupend
    public restApi: CustomerRestApiService,
    public restApi1: CustomertypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    //popstart
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<CustomerCreateComponent>
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
    this.id = this.restApi.employeetypeDetails.id
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      console.log('trong hàm' + this.Content);
    })
  }

  // addEmployeetype(dataEmployeetype) {
  //   var d = new Date(this.employeetypeDetails.birthDay);
  //   d.setHours(0, -d.getTimezoneOffset(), 0 , 0)
  //   this.employeetypeDetails.birthDay = d.toISOString();
  //   this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
  //     this.router.navigate(['/customer-list'])
  //   })
  // }
  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    var d = new Date(this.restApi.employeetypeDetails.birthDay);
    d.setHours(0, -d.getTimezoneOffset(), 0, 0)
    this.restApi.employeetypeDetails.birthDay = d.toISOString();
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      //////////
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'customer';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'customer';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'customer';
    this.router.navigate(['/home'])
  }

}


