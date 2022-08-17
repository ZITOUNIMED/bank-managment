import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleModalComponent } from './modal/role-modal/role-modal.component';
import { FonctionalityModel } from './models/fonctionality.model';
import { RoleModel } from './models/role.model';
import { PermissionsService } from './permissions.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  roles: RoleModel[] = [];
  fonctionalities: FonctionalityModel[] = [];

  constructor(private permissionsService: PermissionsService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadRoles();
    this.loadFonctionalies();
  }

  loadRoles(): void {
    this.permissionsService.getRoles().subscribe(roles => (this.roles = roles));
  }

  loadFonctionalies(): void {
    this.permissionsService.getFonctionalities().subscribe(fonctionalities => (this.fonctionalities = fonctionalities));
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
}
