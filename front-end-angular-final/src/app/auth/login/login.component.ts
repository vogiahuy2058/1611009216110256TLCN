import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginInfo } from '../auth-login-info';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token-storage.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  info: any;
  private loginInfo: AuthLoginInfo;
  constructor(private authService: AuthService, private router: Router, private tokenStorage: TokenStorageService,
    private token: TokenStorageService) { }

  ngOnInit() {
    // const USERNAME_KEY = 'AuthUsername';
    // sessionStorage.getItem(USERNAME_KEY);
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    // console.log(this.token.getToken1())
    // this.token.setuptime();
    if (this.info.token != null) {
      this.router.navigate(['home'])
    }
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }
  onSubmit() {
    console.log(this.form);
    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password
    );
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.content.accessToken);
        this.tokenStorage.saveUsername(data.content.username);
        this.tokenStorage.saveAuthorities(data.content.authorities)

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        // this.reloadPage();
        this.router.navigate(['home'])
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    )
  }

}
