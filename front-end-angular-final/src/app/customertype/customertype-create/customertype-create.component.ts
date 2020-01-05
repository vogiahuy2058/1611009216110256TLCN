
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { CustomertypeRestApiService } from '../customertype-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-customertype-create',
  templateUrl: './customertype-create.component.html',
  styleUrls: ['./customertype-create.component.css']
})
export class CustomertypeCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { name: ''}
  id: any;
  info: any;
  constructor(
    public CheckRegion: CheckService,
    public restApi: CustomertypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    //popstart
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<CustomertypeCreateComponent>
    //popupend
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
    this.id = this.restApi.employeetypeDetails.id
    //popupend
  }

  //validate s 
  onSubmit() {
    if (this.restApi.form.valid) {
      
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
          this.CheckRegion.danhco = 'customertype';
          this.router.navigate(['/home'])
        })
      } else {
        
        this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Sửa thành công');
          this.restApi.form.reset();  //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'customertype';
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
  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      //////////
      this.restApi.form.reset();
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'customertype';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'customertype';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
    //validate s
    this.restApi.form.reset(); //thêm bước này
    //validate e
    this.CheckRegion.danhco = 'customertype';
    this.router.navigate(['/home'])
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }

}

