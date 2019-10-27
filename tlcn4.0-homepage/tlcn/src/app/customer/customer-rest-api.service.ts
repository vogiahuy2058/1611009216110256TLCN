import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentcustomer } from './contentcustomer';

@Injectable({
  providedIn: 'root'
})
export class CustomerRestApiService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/customer';

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
  getEmployeetypes(): Observable<Contentcustomer> {
    return this.http.get<Contentcustomer>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentcustomer> {
    return this.http.get<Contentcustomer>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentcustomer> {
    return this.http.post<Contentcustomer>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentcustomer> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentcustomer>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentcustomer>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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
     window.alert(errorMessage);
     return throwError(errorMessage);
  }
  
}

