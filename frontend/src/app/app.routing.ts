import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { SignInComponent } from "./auth/sign-in/sign-in.component";
import { ConnectedGuardService } from "./auth/connected-guard.service";
import { UsersComponent } from "./users/users.component";
import { LogsComponent } from "./logs/logs.component";
import { PermissionsComponent } from "./permissions/permissions.component";

const routes: Routes = [
    {path: 'sign-in', component: SignInComponent},
    {path: 'home', component: HomeComponent, canActivate: [ConnectedGuardService]},
    {path: 'users', component: UsersComponent, canActivate: [ConnectedGuardService]},
    {path: 'logs', component: LogsComponent, canActivate: [ConnectedGuardService]},
    {path: 'permissions', component: PermissionsComponent, canActivate: [ConnectedGuardService]},
    {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, {
            enableTracing: true,
            useHash: true
        })
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {

}