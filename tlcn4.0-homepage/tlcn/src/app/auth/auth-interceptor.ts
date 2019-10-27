import { HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import {Router} from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { tap } from 'rxjs/operators';
const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private token: TokenStorageService,private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        let authReq = req;
        const token = this.token.getToken();
        if (token != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
        }
        return next.handle(authReq).pipe( tap(() => {},
        (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
           return;
          }
          this.token.signOut();
          this.router.navigate(['login']);
        }
      }));
    }
}
export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];