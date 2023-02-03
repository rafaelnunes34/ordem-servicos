import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ModelsFormComponent } from "./models-form/models-form.component";
import { ModelsListComponent } from "./models-list/models-list.component";

const routes: Routes = [
    {path: '', component: ModelsListComponent},
    {path: 'form', component: ModelsFormComponent},
    {path: 'form/:id', component: ModelsFormComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ModelsRoutingModule { }