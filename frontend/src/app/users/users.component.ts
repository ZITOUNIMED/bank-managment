import { Component, OnInit } from '@angular/core';
import { UserModel } from './model/user-model';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: UserModel[] = [];
  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(){
    this.usersService.getUsers()
    .subscribe(users => (this.users = users));
  }

  openModalUserRole(){}

  openModalManageUser(){}
}
