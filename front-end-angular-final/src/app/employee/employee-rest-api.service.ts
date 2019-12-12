import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentemployee } from './contentemployee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeRestApiService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/employee';
   employeetypeDetails = {id:null, branchShop: '', email:'', employeeType:'', name:''}
  constructor(private http: HttpClient,public router: Router) { }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentemployee> {
    return this.http.get<Contentemployee>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getEmployeenothaveaccountsortbyemployeetype(employeetypename): Observable<Contentemployee> {
    return this.http.get<Contentemployee>(this.apiURL + '/get-all-not-account?nameEmployeeType=' + employeetypename)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getEmployeebybranchshopid(id): Observable<Contentemployee> {
    return this.http.get<Contentemployee>(this.apiURL + '/get-by-branch-shop?branchShopId=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentemployee> {
    return this.http.get<Contentemployee>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentemployee> {
    console.log('haha' + JSON.stringify(employee));
    return this.http.post<Contentemployee>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentemployee> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentemployee>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentemployee>(this.apiURL + '/delete?id=' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // Error handling 
  handleError(error) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       // Get client-side error
       errorMessage = error.error.message;
     } else {
       // Get server-side error
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     //this.router.navigateByUrl(`/login`);
     if(error.status === 500){
      window.alert("Email đã tồn tại vùi lòng nhập email khác!");
     }
     window.alert(errorMessage);
     return throwError(errorMessage);
  }
  
}

