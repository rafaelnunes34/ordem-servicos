import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { OrderFormComponent } from "./order-form/order-form.component";

const routes: Routes = [

    {path: 'form', component: OrderFormComponent},
  
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OrdersRoutingModule { }