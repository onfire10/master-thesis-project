import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {IWorkTime, WorkTime} from "../../model/work-time.model";
import {RestService} from "../../service/rest.service";

@Component({
  selector: 'app-time-keeping',
  templateUrl: './time-keeping.component.html',
  styleUrls: ['./time-keeping.component.scss']
})
export class TimeKeepingComponent implements OnInit {

  public defaultTime = [new Date().getHours(), 0 , 0]

  options: FormGroup;
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto');

  workTimeForm = this.fb.group({
    id: [],
    employeeId: [],
    projectId: [],
    startDate: [],
    endDate: [],
    info: [],
  });

  constructor(private fb: FormBuilder,
              private restService: RestService
  ) {
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
    });
  }

  ngOnInit(): void {
  }

  confirmation(entry: boolean): void {
    if (entry) {
      //this.carService.bookCar(this.createFromForm());
    }
  }

  updateForm(workTime: IWorkTime): void {
    this.workTimeForm.patchValue({
      id: workTime.id,
      employeeId: workTime.employeeId,
      projectId: workTime.projectId,
      startDate: workTime.from,
      endDate: workTime.to,
      info: workTime.info,
    });
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
