import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./components/home/home.component";
import { LayoutComponent } from "./components/layout/layout.component";
import { LoginComponent } from "./components/login/login.component";
import { AuthGuard } from "./security/auth.guard";

const routes: Routes = [
    { path: "login", component: LoginComponent},
    { 
        path: "", component: LayoutComponent,
        canActivate: [AuthGuard],
        children: [
            {path: "", component: HomeComponent},
            {path: 'clients', loadChildren: () => import('./components/clients/clients.module').then(m => m.ClientsModule) }
        ]
    },
    { path: "**", redirectTo: "login"}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }