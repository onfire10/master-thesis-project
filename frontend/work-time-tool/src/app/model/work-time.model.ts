export interface IWorkTime {
  id?: string;
  employeeId?: string;
  projectId?: string;
  from?: string;
  to?: string;
  info?: string;
}

export class WorkTime implements IWorkTime {
  constructor(
    public id?: string,
    public employeeId?: string,
    public projectId?: string,
    public from?: string,
    public to?: string,
    public info?: string,
  ) {}
}
