import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { ModelsListComponent } from './models-list/models-list.component';
import { ModelsFormComponent } from './models-form/models-form.component';
import { ModelsRoutingModule } from './models-routing.module';



@NgModule({
  declarations: [
    ModelsListComponent,
    ModelsFormComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ModelsRoutingModule
  ]
})
export class ModelsModule { }
