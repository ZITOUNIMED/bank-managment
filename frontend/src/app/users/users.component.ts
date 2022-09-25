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

  openModalUserRole(){
    const modalRef  = this.modalService.open(UserModalComponent);
    modalRef.componentInstance.roles = this.roles;

    modalRef.result.then((user) => {
      if(user){
        this.addUser(user);
      }
    }, (reason) => {
      console.log(reason);
    });
  }

  private addUser(user: UserModel): void {
    const roleStr: string = user.role as string;
    const role = this.roles.find(item => item.code === roleStr);
    if(role){
      user.role = role;
      this.usersService.saveUser(user)
      .subscribe(() => {
        this.loadUsers();
      });
    }
    
  }

  edit(): void {
    alert('edit');
  }

  remove(): void {
    alert('remove');
  }

  sendCredentials(): void {
    alert('sendCredentials');
  }
}
