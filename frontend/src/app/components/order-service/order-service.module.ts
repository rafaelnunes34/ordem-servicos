import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { OrderFormComponent } from './order-form/order-form.component';
import { OrdersRoutingModule } from './orders-routing.module';



@NgModule({
  declarations: [
    OrderFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    OrdersRoutingModule
  ]
})
export class OrderServiceModule { }
