import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { SupplierRestApiService } from '../supplier-rest-api.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { CheckService } from 'src/app/check.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-supplier-create',
  templateUrl: './supplier-create.component.html',
  styleUrls: ['./supplier-create.component.css']
})
export class SupplierCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { address:'',email:'',name:'',note:'',phone:'',taxCode:'',totalPurchase: null}
  id: any;
  info: any;
  constructor(
    public CheckRegion: CheckService,
    public restApi: SupplierRestApiService,
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<SupplierCreateComponent>
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
  }

  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      //////////
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'supplier';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'supplier';
      this.router.navigate(['/home'])
    })
  }
  onSubmit() {
    if (this.restApi.form.valid) {
      if (!this.restApi.form.get('id').value) {
       
        this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Thêm thành công');
          this.restApi.form.reset(); //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'supplier';
          this.router.navigate(['/home'])
        })
      } else {
        this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Sửa thành công');
          this.restApi.form.reset();  //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'supplier';
          this.router.navigate(['/home'])
        })
      }
    }
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }
  onClose() {
    this.restApi.form.reset();  //thêm vào
    this.dialogRef.close();
    this.CheckRegion.danhco = 'supplier';
    this.router.navigate(['/home'])
  }

}


