import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { ServicosRoutingModule } from './servicos-routing.module';
import { ServicosListComponent } from './servicos-list/servicos-list.component';
import { ServicosFormComponent } from './servicos-form/servicos-form.component';

@NgModule({
  declarations: [
    ServicosListComponent,
    ServicosFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ServicosRoutingModule
  ]
})
export class ServicosModule { }
