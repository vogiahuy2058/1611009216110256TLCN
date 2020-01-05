import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentinvoice } from './contentinvoice';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import * as _ from 'lodash';
import { DatePipe } from '@angular/common';
import { Contentinvoicedetail } from './contentinvoicedetail';
import { Invoicedetail } from './invoicedetail';
import { Contentinvoicedetailcheck } from './contentinvoicedetailcheck';
import { Invoicedetailcheck } from './invoicedetailcheck';

@Injectable({
  providedIn: 'root'
})
export class InvoiceRestApiService {

   // Define API
  apiURL = 'http://localhost:8888/api/v1/invoice';
  apiURLInvoiceDetail = 'http://localhost:8888/api/v1/invoice-detail';
  InvoiceDetailList: Array<Invoicedetail> = [];
  InvoiceDetailListCheck: Array<Invoicedetailcheck> = [];
  InvoiceDetails = {
    customerPhone: null,  date: 0,id: null, numberPosition: null, orderType: 'Ngồi tại bàn',paymentStatus:false,realPay:0,  totalDiscount: 0,
    totalPrice: 0, vat: 0
  }
  constructor(private http: HttpClient,public router: Router) { }

  // form: FormGroup = new FormGroup({
  //   $key: new FormControl(null),
  //   fullName: new FormControl('', Validators.required),
  //   email: new FormControl('', Validators.email),
  //   mobile: new FormControl('', [Validators.required, Validators.minLength(8)]),
  //   city: new FormControl(''),
  //   gender: new FormControl('1'),
  //   department: new FormControl(0),
  //   hireDate: new FormControl(''),
  //   isPermanent: new FormControl(false)
  // });
  form: FormGroup = new FormGroup({
    drinkId: new FormControl(0),
    amount: new FormControl(0),
    discount: new FormControl(0),
    invoiceId: new FormControl(null),
    price: new FormControl(0),
    unitPrice: new FormControl(0),
    serial: new FormControl(0),
    id: new FormControl(0),
    drinkName: new FormControl('')
  });
  forminvoice: FormGroup = new FormGroup({
    invoiceId: new FormControl(0),
  });

  // initializeFormGroup() {
  //   this.form.setValue({
  //     $key: null,
  //     fullName: '',
  //     email: '',
  //     mobile: '',
  //     city: '',
  //     gender: '1',
  //     department: 0,
  //     hireDate: '',
  //     isPermanent: false
  //   });
  // }
  initializeFormGroup(drinkId, invoiceId,serial, drinkName) {
    this.form.setValue({
      drinkId: drinkId,
      amount: 0,
      discount: 0,
      invoiceId: invoiceId,
      price: 0,
      unitPrice: 0,
      serial: serial,
      id: 0,
      drinkName: drinkName

    });
  }
  callinvoiceId(invoiceId){
    this.forminvoice.setValue({
      invoiceId: invoiceId
    })
  }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  
  editEmployeetypelist(employee: Invoicedetail[]): Observable<Contentinvoicedetail> {
    console.log('tao ne: ' + JSON.stringify(employee));
    return this.http.put<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/edit-list', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentinvoice> {
    return this.http.get<Contentinvoice>(this.apiURL + '/get-all-true')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getEmployeetypesfalse(): Observable<Contentinvoice> {
    return this.http.get<Contentinvoice>(this.apiURL + '/get-all-false')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getmaxid(): Observable<Contentinvoice> {
    return this.http.get<Contentinvoice>(this.apiURL + '/get-max-id')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getmaxidinvoicedetail(): Observable<Contentinvoicedetail> {
    return this.http.get<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/get-max-id')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentinvoice> {
    return this.http.get<Contentinvoice>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  getEmployeetypefromdatetodate(idf,idt,brsid): Observable<Contentinvoice> {
    return this.http.get<Contentinvoice>(this.apiURL + '/get-all-date-to-date?branchShopId='+ brsid +'&fromDate=' + idf + '&toDate=' + idt)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  getInvoiceDetail(id): Observable<Contentinvoicedetail> {
    return this.http.get<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/get-by-id-invoice?invoiceId=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  getInvoiceDetail1(id): Observable<Contentinvoicedetailcheck> {
    return this.http.get<Contentinvoicedetailcheck>(this.apiURLInvoiceDetail + '/get-by-id-invoice?invoiceId=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  getInvoiceDetailbyid(id): Observable<Contentinvoicedetail> {
    return this.http.get<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/get-by-id?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  getAllInvoice(id): Observable<Contentinvoicedetail> {
    return this.http.get<Contentinvoicedetail>(this.apiURL + '/get-full-invoice?invoiceId=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  deteleInvoiceDetailbyid(drinkId,Id,invoiceId): Observable<Contentinvoicedetail> {
    return this.http.delete<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/delete?drinkId='+ drinkId + '&id='+Id+'&invoiceId=' + invoiceId)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentinvoice> {
    console.log('haha' + JSON.stringify(employee));
    return this.http.post<Contentinvoice>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  
  createInvoiceDetail(invoiceDetail): Observable<Contentinvoicedetail> {
    console.log('haha' + JSON.stringify(invoiceDetail));
    return this.http.post<Contentinvoicedetail>(this.apiURLInvoiceDetail + '/create', JSON.stringify(invoiceDetail), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentinvoice> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentinvoice>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentinvoice>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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
  getEmployees() {
    // this.employeeList = this.firebase.list('employees');
    // return this.employeeList.snapshotChanges();
  }

  insertEmployee(employee) {
    // this.employeeList.push({
    //   fullName: employee.fullName,
    //   email: employee.email,
    //   mobile: employee.mobile,
    //   city: employee.city,
    //   gender: employee.gender,
    //   department: employee.department,
    //    hireDate: employee.hireDate == "" ? "" : this.datePipe.transform(employee.hireDate, 'yyyy-MM-dd'),
    //   isPermanent: employee.isPermanent
    // });
  }

  updateEmployee(employee) {
    // this.employeeList.update(employee.$key,
    //   {
    //     fullName: employee.fullName,
    //     email: employee.email,
    //     mobile: employee.mobile,
    //     city: employee.city,
    //     gender: employee.gender,
    //     department: employee.department,
    //      hireDate: employee.hireDate == "" ? "" : this.datePipe.transform(employee.hireDate, 'yyyy-MM-dd'),
    //     isPermanent: employee.isPermanent
    //   });
  }

  deleteEmployee($key: string) {
    // this.employeeList.remove($key);
  }

  populateForm(employee) {
    this.form.setValue(_.omit(employee,'departmentName'));
  }
  
}


