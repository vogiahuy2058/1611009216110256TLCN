import { Injectable } from '@angular/core';
import { JsonAdaptor } from '@syncfusion/ej2-data';
var jwt_decode = require('jwt-decode');
const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';
const DATE = '';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private roles: Array<string> = [];
  constructor() { }
  signOut() {
    window.sessionStorage.clear();
  }
  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }
  public getdateexpire(): string {
    console.log(sessionStorage.getItem(TOKEN_KEY))
    const test = sessionStorage.getItem(TOKEN_KEY)
    let payload = sessionStorage.getItem(TOKEN_KEY).split('.')[1]

    payload = window.atob(payload)
    var payloadArray = JSON.parse(payload);
    // console.log(payloadArray.roles[1].authority)
    // console.log('haha ' + JSON.stringify(payload))
    return payloadArray.exp;
  }
  public getdateinit(): string {
    console.log(sessionStorage.getItem(TOKEN_KEY))
    const test = sessionStorage.getItem(TOKEN_KEY)
    let payload = sessionStorage.getItem(TOKEN_KEY).split('.')[1]

    payload = window.atob(payload)
    var payloadArray = JSON.parse(payload);
    
    return payloadArray.iat;
  }
  checklogin(){
      let exp = this.getdateexpire()
      let iat = this.getdateinit()
      let now = new Date().getTime();
      var check = (parseFloat(exp) - parseFloat(iat)) * 1000
      let check1 = now - check
      var inittime = parseFloat(iat) * 1000
      // var setupTime = localStorage.getItem('setupTime');
      // if (setupTime == null) {
      //   localStorage.setItem('setupTime', now.toString())
      // } else {
      //   console.log(now - check + ' ' + parseFloat(setupTime) )
      //   console.log('xóa cache')
        if (now - check > inittime) {
          console.log('xóa cache')
          localStorage.clear()
          window.sessionStorage.clear();
          // localStorage.setItem('setupTime', now.toString());
        }
      // }
      console.log(exp + ' ' + iat + ' ' + now + ' ' +  check  + ' ' +  check1)
  }
  // public setuptime(): void {
  //   var minutes = 1; // Reset when storage is more than 24hours
  //   var now = new Date().getTime();
  //   var setupTime = localStorage.getItem('setupTime');
  //   if (setupTime == null) {
  //     localStorage.setItem('setupTime', now.toString())
  //   } else {
  //     if (now - Date.parse(setupTime) > minutes * 60 * 1000) {
  //       localStorage.clear()
  //       window.sessionStorage.clear();
  //       localStorage.setItem('setupTime', now.toString());
  //     }
  //   }
  // }
  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }
  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }
  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }
  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
      return this.roles;
    }
  }
  public savedate(date: string) {
    window.sessionStorage.removeItem(DATE);
    window.sessionStorage.setItem(DATE, date);
  }
  public getdate(): string {
    return sessionStorage.getItem(DATE);
  }
  getTokenExpirationDate(token: string): Date {
    const decoded = jwt_decode(token);

    if (decoded.exp === undefined) return null;

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if (!token) token = this.getToken();
    if (!token) return true;

    const date = this.getTokenExpirationDate(token);
    if (date === undefined) return false;
    return !(date.valueOf() > new Date().valueOf());
  }
}

