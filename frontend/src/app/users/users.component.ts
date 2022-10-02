import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PermissionsService } from '../permissions/permissions.service';
import { RoleModel } from '../shared/models/role.model';
import { UserModalComponent } from './modal/user-modal/user-modal.component';
import { UserModel } from './model/user-model';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: UserModel[] = [];
  roles: RoleModel[] = [];
  selectedUser: UserModel;

  constructor(private usersService: UsersService, 
    private modalService: NgbModal,
    private permissionsService: PermissionsService) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadRoles();
  }

  loadRoles(): void {
    this.permissionsService.getRoles().subscribe(roles => (this.roles = roles));
  }

  loadUsers(){
    this.usersService.getUsers()
    .subscribe(users => (this.users = users));
  }

  openModalUser(): void {
    const modalRef  = this.modalService.open(UserModalComponent);
    modalRef.componentInstance.roles = this.roles;
    modalRef.componentInstance.user = this.selectedUser;

    modalRef.result.then((user) => {
      if(user){
        this.saveUser(user);
      }
    }, (reason) => {
      console.log(reason);
    });
  }

  private saveUser(user: any): void {
    const roleCode: string = user.roleCode;
    const role = this.roles.find(item => item.code === roleCode);
    if(role){
      user.role = role;
      if(this.selectedUser){
        user.id = this.selectedUser.id;
      }
      this.usersService.saveUser(user)
      .subscribe(() => {
        this.loadUsers();
        this.selectedUser = null;
      });
    }
  }

  selectUser(user: UserModel): void {
    this.selectedUser = user;
  }

  edit(): void {
    this.openModalUser();
  }

  remove(): void {
    this.usersService.remove(this.selectedUser.id)
    .subscribe(() => {
      this.loadUsers();
      this.selectedUser = null;
    });
  }

  sendCredentials(): void {
    alert('sendCredentials');
  }
}
