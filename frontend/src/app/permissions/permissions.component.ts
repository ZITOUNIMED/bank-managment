import { Component, OnInit } from '@angular/core';
import { RoleModel } from './models/role.model';
import { PermissionsService } from './permissions.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  userRole: RoleModel = {} as RoleModel;

  constructor(private permissionsService: PermissionsService) { }

  ngOnInit(): void {
    this.permissionsService.getUserRole().subscribe(userRole => (this.userRole = userRole));
  }
}
