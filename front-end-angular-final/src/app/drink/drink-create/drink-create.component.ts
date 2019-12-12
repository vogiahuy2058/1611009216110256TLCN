import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DrinkRestApiService } from '../drink-rest-api.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';
import { MatDialogRef } from '@angular/material';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { CheckService } from 'src/app/check.service';

@Component({
  selector: 'app-drink-create',
  templateUrl: './drink-create.component.html',
  styleUrls: ['./drink-create.component.css']
})
export class DrinkCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { description: '', drinkType: '', name: '' }
  id: any;
  info: any;
  Content: any = [];
  constructor(
    public CheckRegion: CheckService,
    public restApi: DrinkRestApiService,
    public restApi1: DrinktypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<DrinkCreateComponent>
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

  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      //////////
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'drink';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'drink';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'drink';
    this.router.navigate(['/home'])
  }
}


