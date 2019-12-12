import { Component, OnInit, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { PopupService } from '../popup.service';
import { DepartmentService } from '../department.service';
import { NotificationService } from '../notification.service';

// import { EmployeeService } from '../../shared/employee.service';
// import { DepartmentService } from '../../shared/department.service';
// import { NotificationService } from '../../shared/notification.service';

@Component({
  selector: 'app-testpopup',
  templateUrl: './testpopup.component.html',
  styleUrls: ['./testpopup.component.css']
})
export class TestpopupComponent implements OnInit {

  @Input() DrinktypeDetails = { drinkType: 0 }
  constructor(private service: PopupService,
    private departmentService: DepartmentService,
    private notificationService: NotificationService,
    public dialogRef: MatDialogRef<TestpopupComponent>) { }



  ngOnInit() {
    this.service.getEmployees();
    this.DrinktypeDetails.drinkType = this.service.form.get('$key').value;
  }

  onClear() {
    this.service.form.reset();
    this.service.initializeFormGroup1();
    this.service.form.get('$key').setValue(this.DrinktypeDetails.drinkType);
    this.notificationService.success(':: Submitted successfully');
  }

  onSubmit() {
    if (this.service.form.valid) {
      if (!this.service.form.get('$key').value)
        this.service.insertEmployee(this.service.form.value);
      else
      this.service.updateEmployee(this.service.form.value);
      this.service.form.reset();
      //this.service.initializeFormGroup();
      this.notificationService.success(':: Submitted successfully');
      this.onClose();
    }
  }

  onClose() {
    this.service.form.reset();
    //this.service.initializeFormGroup();
    this.dialogRef.close();
  }

}

