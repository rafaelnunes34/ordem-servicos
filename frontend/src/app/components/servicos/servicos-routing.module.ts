import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ServicosFormComponent } from "./servicos-form/servicos-form.component";
import { ServicosListComponent } from "./servicos-list/servicos-list.component";

const routes: Routes = [
    {path: '', component: ServicosListComponent},
    {path: 'form', component: ServicosFormComponent},
    {path: 'form/:id', component: ServicosFormComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ServicosRoutingModule { }