import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeetypeRestApiService } from '../employeetype-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
//popstart
import { MatDialogRef } from '@angular/material';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { CheckService } from 'src/app/check.service';
//popupend
@Component({
  selector: 'app-employeetype-create',
  templateUrl: './employeetype-create.component.html',
  styleUrls: ['./employeetype-create.component.css']
})
export class EmployeetypeCreateComponent implements OnInit {
  //Coment code employeetypeDetails copy nó xuống service rest api thêm biến id:any để kiểm tra là chúng ta sẽ tạo mới hay update
  //@Input() employeetypeDetails = { name: ''}
  id: any;
  info: any;
  checkvalidate = 1;
  constructor(
    //popstart
    public CheckRegion: CheckService,
    //popupend
    public restApi: EmployeetypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    //popstart
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<EmployeetypeCreateComponent>
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
    //popstart
    this.id = this.restApi.employeetypeDetails.id
    //popupend
  }
  //popstart
  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'employeetype';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'employeetype';
      this.router.navigate(['/home'])
    })
  }
  check(){
      this.checkvalidate = 2;
      console.log(this.checkvalidate + 'hehe')
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'employeetype';
    this.router.navigate(['/home'])
  }
  //popupend
}
