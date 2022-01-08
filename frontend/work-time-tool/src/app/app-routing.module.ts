import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TimeKeepingComponent} from "./components/time-keeping/time-keeping.component";


const routes: Routes = [
  {
    path: '',
    component: TimeKeepingComponent,
    pathMatch: 'full',
    canActivate: []
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
