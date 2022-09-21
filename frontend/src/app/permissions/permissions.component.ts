import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleModalComponent } from './modal/role-modal/role-modal.component';
import { SaveRoleModalComponent } from './modal/save-role-modal/save-role-modal.component';
import { FonctionalityModel } from './models/fonctionality.model';
import { PermissionModel } from '../shared/models/permission.model';
import { RoleModel } from '../shared/models/role.model';
import { PermissionsService } from './permissions.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  roles: RoleModel[] = [];
  fonctionalities: FonctionalityModel[] = [];
  roleControl = new FormControl('');
  selectedRole: RoleModel | undefined;

  constructor(private permissionsService: PermissionsService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadRoles();
    this.loadFonctionalies();
    this.listenOnRoleChanged();
  }

  loadRoles(): void {
    this.permissionsService.getRoles().subscribe(roles => (this.roles = roles));
  }

  loadFonctionalies(): void {
    this.permissionsService.getFonctionalities()
    .subscribe(fonctionalities => {
      this.fonctionalities = fonctionalities;
      this.buildFonctinalitiesControls();
    });
  }

  openModalAddRole(): void {
    this.modalService.open(RoleModalComponent).result.then((role) => {
      if(role){
        this.addRole(role);
      }
    }, (reason) => {
      console.log(reason);
    });
  }

  addRole(role: RoleModel): void {
    this.permissionsService.addRole(role)
    .subscribe(() => {
      this.loadRoles();
    })
  }

  listenOnRoleChanged(): void {
    this.roleControl.valueChanges.subscribe(code => {
      this.selectedRole = this.roles.find(role => role.code === code);
      this.disableFonctionalities();
    });
  }

  removePermission(code: string): void {
    if(this.selectedRole && this.selectedRole.code !== 'ADMIN'){
      this.selectedRole.permissions = this.selectedRole.permissions
        .filter(permission => permission.code != code);
        this.disableFonctionalities();
    }
  }

  buildFonctinalitiesControls(): void {
    this.fonctionalities.forEach(fonctionality => {
      fonctionality.formControl = new FormControl();
    });
  }

  addFonctionalities(): void {
    if(this.selectedRole && this.selectedRole.code !== 'ADMIN'){
      const permissions = this.fonctionalities
      .filter(fonctionality => fonctionality.formControl.value)
      .map(fonctionality => {
        const permission: PermissionModel = {
          code: fonctionality.name,
          label: fonctionality.label
        };
        fonctionality.formControl.setValue(false);
        fonctionality.formControl.disable();
        return permission;
      });

      this.selectedRole.permissions.push(...permissions);
    }
  }

  openModalSaveRole(): void {
    this.modalService.open(SaveRoleModalComponent).result.then((decision) => {
      if(decision && this.selectedRole){
        this.permissionsService.saveRole(this.selectedRole)
        .subscribe(() => {
          this.disableFonctionalities()
          this.loadRoles();
        });
      }
    }, (reason) => {
      console.log(reason);
    });
  }

  disableFonctionalities(): void {
    if(this.selectedRole && this.selectedRole.code !== 'ADMIN'){
      this.fonctionalities.forEach(fonctionality => {
        if(this.selectedRole){
          const found = this.selectedRole.permissions.find(permission => fonctionality.name === permission.code);
          if(found){
            fonctionality.formControl.disable();
          } else {
            fonctionality.formControl.enable();
          }
        }
      })
    }
  }
}
