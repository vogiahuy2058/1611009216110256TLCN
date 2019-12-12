
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
  id:any;
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
    if(!this.token.getToken()){
      this.router.navigate(['login'])
    }
    this.id = this.restApi.employeetypeDetails.id
    //popupend
  }

  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
//////////
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
    this.CheckRegion.danhco = 'customertype';
    this.router.navigate(['/home'])
  }

}

