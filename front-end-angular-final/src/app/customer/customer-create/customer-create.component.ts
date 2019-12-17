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
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';



@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  // public minDate: Date = new Date("05/07/2017");
  // public maxDate: Date = new Date("05/27/2017");
  // public dateValue: Date = new Date("05/16/2017");
  // @Input() employeetypeDetails = { address: '', birthDay:'', customerType:'', email:'',name:'',note:'',phone:'',sex: true,totalPurchase: 0}

  id: any;
  info: any;
  Content: any = [];
  //validate s dùng cho selectbox
  contentDetail: any = []
  selectedProfile: any = []
   //validate e
  name: any
  // gender: any
  constructor(
    public CheckRegion: CheckService,
    //popupend
    public restApi: CustomerRestApiService,
    public restApi1: CustomertypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    //popstart
    private notificationService: NotificationService,
    //validate s dùng cho format ngày tháng
    public datepipe: DatePipe,
    //validate e
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
    //validate s dùng cho ngày tháng 
    this.restApi.form.get('birthDay').setValue(this.datepipe.transform(this.restApi.form.get('birthDay').value, 'yyyy-MM-dd'))
    //validate e 
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      //validate s 
      this.contentDetail = this.Content.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile = this.contentDetail[0].name;
        // this.gender = true
        console.log('customertype' + this.selectedProfile)
      } else {
        this.selectedProfile = this.restApi.form.get('customerType').value
        //validate s đừng quan tâm vì không còn checkbox radio nào khác
        if(this.restApi.form.get('sex').value === true){
          this.restApi.form.get('gender').setValue('1')
        }else{
          this.restApi.form.get('gender').setValue('2')
        }
        //validate e
        // this.gender = this.restApi.form.get('sex').value
        console.log('customertype' + this.selectedProfile)
      }
      //validate e
      // this.restApi.form.setValue({customerType: ['khac hang']});
      console.log('trong hàm' + this.Content);
    })
  }
  //validate s 
  onSubmit() {
    if (this.restApi.form.valid) {
      //nếu có selectbox
      this.restApi.form.get('customerType').setValue(this.selectedProfile)
      //nếu có time date
      this.restApi.form.get('birthDay').setValue(this.datepipe.transform(this.restApi.form.get('birthDay').value, 'yyyy-MM-dd'))
      //bỏ gender vì không còn ridio button
      if(this.restApi.form.get('gender').value === '1'){
        this.restApi.form.get('sex').setValue(true)
      }else{
        this.restApi.form.get('sex').setValue(false)
        console.log(this.restApi.form.get('sex').value)
      }
      // kết thúc bỏ
      // this.restApi.form.get('sex').setValue(this.gender)
      if (!this.restApi.form.get('id').value) {
        // this.restApi.employeetypeDetails.name = this.restApi.form.get('name').value
        // this.restApi.employeetypeDetails.address = this.restApi.form.get('address').value
        // this.restApi.employeetypeDetails.birthDay = this.restApi.form.get('birthDay').value
        // this.restApi.employeetypeDetails.birthDay = this.datepipe.transform(this.restApi.employeetypeDetails.birthDay, 'yyyy-MM-dd');
        // this.restApi.employeetypeDetails.customerType = this.selectedProfile.name
        // this.restApi.employeetypeDetails.email = this.restApi.form.get('email').value
        // this.restApi.employeetypeDetails.note = this.restApi.form.get('note').value
        // this.restApi.employeetypeDetails.phone = this.restApi.form.get('phone').value
        // this.restApi.employeetypeDetails.sex = this.restApi.form.get('sex').value
        // this.restApi.employeetypeDetails.totalPurchase = this.restApi.form.get('totalPurchase').value

        this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Thêm thành công');
          this.restApi.form.reset(); //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'customer';
          this.router.navigate(['/home'])
        })
      } else {
        
        this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Sửa thành công');
          this.restApi.form.reset();  //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'customer';
          this.router.navigate(['/home'])
        })
      }
      // this.restApi.updateEmployee(this.restApi.form.value);
      //this.service.initializeFormGroup();
      // this.notificationService.success(':: Submitted successfully');
      // this.onClose();
      // this.CheckRegion.danhco = 'employeetype';
      // this.router.navigate(['/home'])
    }
  }
  //validate e

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
      this.restApi.form.reset();
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
  // add(name){
  //   this.name = name
  //   console.log('lay value select box')
  //   console.log(this.name)
  // }
  
  onClose() {
    //validate s
    this.restApi.form.reset(); //thêm bước này
    //validate e
    this.dialogRef.close();
    this.CheckRegion.danhco = 'customer';
    this.router.navigate(['/home'])
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }

}


