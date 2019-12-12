import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { BranchshopRestApiService } from '../branchshop-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-branchshop-create',
  templateUrl: './branchshop-create.component.html',
  styleUrls: ['./branchshop-create.component.css']
})
export class BranchshopCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { name: '', address:''}
  info: any;
  id:any;
  constructor(
    public CheckRegion: CheckService,
    public restApi: BranchshopRestApiService, 
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    public dialogRef: MatDialogRef<BranchshopCreateComponent>
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
      this.CheckRegion.danhco = 'brandshop';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
       //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'brandshop';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'brandshop';
    this.router.navigate(['/home'])
  }

}

