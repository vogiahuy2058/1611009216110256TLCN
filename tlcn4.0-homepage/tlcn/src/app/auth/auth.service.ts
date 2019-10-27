import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthLoginInfo } from '../auth/auth-login-info';
import { ContentLogin } from './content-login';
import { retry, catchError } from 'rxjs/operators';
import { Observable,throwError  } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}; 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8888/api/v1/login';
  
  constructor(private http: HttpClient) { }
  attemptAuth(credentials: AuthLoginInfo): Observable<ContentLogin>{
    return this.http.post<ContentLogin>(this.loginUrl, credentials, httpOptions)
    // .pipe(
    //   retry(1),
    //   catchError(this.handleError)
    // )
  }
  handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
 }
}
  
