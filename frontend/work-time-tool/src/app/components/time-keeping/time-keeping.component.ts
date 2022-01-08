import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {FormBuilder} from "@angular/forms";
import {WorkTime} from "../../model/work-time.model";
import {Time} from "@angular/common";
import {RestService} from "../../service/rest.service";

@Component({
  selector: 'app-time-keeping',
  templateUrl: './time-keeping.component.html',
  styleUrls: ['./time-keeping.component.scss']
})
export class TimeKeepingComponent implements OnInit {

  workTimeForm = this.fb.group({
    id: [],
    employeeId: [],
    projectId: [],
    startDate: [],
    endDate: [],
    info: [],
  });

  constructor(public data: WorkTime,
              private fb: FormBuilder,
              private dialog: MatDialogRef<TimeKeepingComponent>,
              private restService: RestService
  ) { }

  ngOnInit(): void {
  }

  confirmation(entry: boolean): void {
    if (entry) {
      //this.carService.bookCar(this.createFromForm());
    }
    this.dialog.close();
  }


  private createFromForm(): WorkTime {
    return {
      id: this.workTimeForm.get(['id'])!.value,
      employeeId: this.workTimeForm.get(['employeeId'])!.value,
      projectId: this.workTimeForm.get(['projectId'])!.value,
      from: this.workTimeForm.get(['startDate'])!.value,
      to: this.workTimeForm.get(['endDate'])!.value,
      info: this.workTimeForm.get(['info'])!.value,
    }
  }
}
