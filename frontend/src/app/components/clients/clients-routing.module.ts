import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ClientsFormComponent } from "./clients-form/clients-form.component";
import { ClientsListComponent } from "./clients-list/clients-list.component";

const routes: Routes = [
    {path: '', component: ClientsListComponent},
    {path: 'form', component: ClientsFormComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ClientsListRoutingModule { }