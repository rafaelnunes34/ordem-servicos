import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ClientsListComponent } from "./clients-list/clients-list.component";

const routes: Routes = [
    {path: '', component: ClientsListComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ClientsListRoutingModule { }