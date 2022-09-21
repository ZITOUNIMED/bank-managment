import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SignInComponent } from './auth/sign-in/sign-in.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app.routing';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { AppHttpInterceptor } from './http-interceptor';
import { UsersComponent } from './users/users.component';
import { LogsComponent } from './logs/logs.component';
import { PermissionsComponent } from './permissions/permissions.component';
import { RoleModalComponent } from './permissions/modal/role-modal/role-modal.component';
import { SaveRoleModalComponent } from './permissions/modal/save-role-modal/save-role-modal.component';
import { UserModalComponent } from './users/modal/user-modal/user-modal.component';
import { ManageUsersModalComponent } from './users/modal/manage-users-modal/manage-users-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    HomeComponent,
    HeaderComponent,
    MenuComponent,
    UsersComponent,
    LogsComponent,
    PermissionsComponent,
    RoleModalComponent,
    SaveRoleModalComponent,
    UserModalComponent,
    ManageUsersModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AppHttpInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
