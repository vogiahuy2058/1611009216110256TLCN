import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
import { EmployeetypeRestApiService } from '../employeetype/employeetype-rest-api.service';
import { CheckService } from '../check.service';
declare var $: any;
declare var jQuery: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private roles: string[];
  private authority: string;
  private authorityad: string;
  private authorityhr: string;
  private authoritychef: string;
  private authoritybrm: string;
  private authorityacc: string;
  private authoritycashier: string;
  info: any;
  constructor(
    private token: TokenStorageService,
    private router: Router,
    public Checkregion: CheckService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
      
    };
    console.log('xuat hien1')
    // this.token.setuptime()
    // console.log(this.token.getToken1())
    if (this.token.getToken()) {
      console.log('xuat hien2')
      this.token.checklogin()
      console.log('xuat hien3')
      this.router.navigate(['home'])
    } else {
      console.log('xuat hien4')
      this.router.navigate(['login'])
    }
    //Mỗi lần làm xong thì check xem nó tên gì navigation tới trang đó(làm nhiều if)
    console.log('xuat hien5')
    console.log(this.Checkregion.danhco)
    if (this.Checkregion.danhco === 'employeetype') {

      this.router.navigate(['/employeetype-list'])
    }
    if (this.Checkregion.danhco === 'brandshop') {
      this.router.navigate(['/branchshop-list'])
    }
    if (this.Checkregion.danhco === 'unit') {
      this.router.navigate(['/unit-list'])
    }
    if (this.Checkregion.danhco === 'supplycontract') {
      this.router.navigate(['/supplycontract-list'])
    }
    if (this.Checkregion.danhco === 'customertype') {
      this.router.navigate(['/customertype-list'])
    }
    if (this.Checkregion.danhco === 'drinktype') {
      this.router.navigate(['/drinktype-list'])
    }
    if (this.Checkregion.danhco === 'materialtype') {
      this.router.navigate(['/materialtype-list'])
    }
    if (this.Checkregion.danhco === 'ordertype') {
      this.router.navigate(['/ordertype-list'])
    }
    if (this.Checkregion.danhco === 'customer') {
      this.router.navigate(['/customer-list'])
    }
    if (this.Checkregion.danhco === 'drink') {
      this.router.navigate(['/drink-list'])
    }
    if (this.Checkregion.danhco === 'material') {
      this.router.navigate(['/material-list'])
    }
    if (this.Checkregion.danhco === 'supplier') {
      this.router.navigate(['/supplier-list'])
    }
    if (this.Checkregion.danhco === 'employee') {
      this.router.navigate(['/employee-list'])
    }
    if (this.Checkregion.danhco === 'account') {
      this.router.navigate(['/account-list'])
    }
    console.log('token')
    this.Checkregion.danhco = '';
    console.log('xuat hien6')
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        console.log('xuat hien')
        if (role === 'ROLE_ADMIN') {
          this.authorityad = 'ad';
          return true;
        } if (role === 'ROLE_HR') {
          this.authorityhr = 'hr';
          return true;
        } if (role === 'ROLE_BRANCH_MANAGER') {
          this.authoritybrm = 'brm';
          return true;
        } if (role === 'ROLE_CASHIER') {
          this.authoritycashier = 'cashier';
          return true;
        } if (role === 'ROLE_ACCOUNTANT') {
          this.authorityacc = 'acc';
          return true;
        } if (role === 'ROLE_CHEF') {
          this.authoritychef = 'chef';
          return true;
        }
        this.authority = 'user';
        return false;
      });
    }else{
      this.router.navigate(['/login'])
    }
    jQuery("#menu").metisMenu();
    
  }
  
  logout() {
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}
