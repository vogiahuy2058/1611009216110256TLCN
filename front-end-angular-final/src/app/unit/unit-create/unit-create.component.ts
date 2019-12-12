import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { UnitRestApiService } from '../unit-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-unit-create',
  templateUrl: './unit-create.component.html',
  styleUrls: ['./unit-create.component.css']
})
export class UnitCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { name: ''}
  id:any;
  info: any;
  constructor(
    public CheckRegion: CheckService,
    public restApi: UnitRestApiService, 
    private token: TokenStorageService,
    private notificationService: NotificationService,
    public dialogRef: MatDialogRef<UnitCreateComponent>,
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
    this.id = this.restApi.employeetypeDetails.id
  }

  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'unit';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
       //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'unit';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
  }

}



