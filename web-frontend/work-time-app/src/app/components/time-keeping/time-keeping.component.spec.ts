import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeKeepingComponent } from './time-keeping.component';

describe('TimeKeepingComponent', () => {
  let component: TimeKeepingComponent;
  let fixture: ComponentFixture<TimeKeepingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimeKeepingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimeKeepingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
