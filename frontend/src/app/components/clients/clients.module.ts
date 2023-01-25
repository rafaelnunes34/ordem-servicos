import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClientsListComponent } from './clients-list/clients-list.component';
import { ClientsListRoutingModule } from './clients-routing.module';
import { ClientsFormComponent } from './clients-form/clients-form.component';

@NgModule({
  declarations: [
    ClientsListComponent,
    ClientsFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ClientsListRoutingModule
  ]
})
export class ClientsModule { }
