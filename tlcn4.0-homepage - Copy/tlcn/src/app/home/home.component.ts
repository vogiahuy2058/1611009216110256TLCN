import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
declare var $: any;
declare var jQuery:any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private roles: string[];
  private authority: string;
  info: any;
  constructor(private token: TokenStorageService,private router : Router) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.token.getToken()){
      this.router.navigate(['home'])
    }else{
      this.router.navigate(['login'])
    }
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_CUSTOMER') {
          this.authority = 'customer';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
    jQuery("#menu").metisMenu();
  }
  logout(){
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}
