import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleModel } from '../../../shared/models/role.model';
import { UserModel } from '../../model/user-model';

@Component({
  selector: 'app-user-modal',
  templateUrl: './user-modal.component.html',
  styleUrls: ['./user-modal.component.css']
})
export class UserModalComponent implements OnInit {
  userForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    login: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
    email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(100)]),
    roleCode: new FormControl('', [Validators.required]),
  });

  @Input() roles: RoleModel[] = [];
  @Input() user: UserModel;

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
    if(this.user){
      this.userForm.get('firstName')?.setValue(this.user.firstName);
      this.userForm.get('lastName')?.setValue(this.user.lastName);
      this.userForm.get('login')?.setValue(this.user.login);
      this.userForm.get('email')?.setValue(this.user.email);
      this.userForm.get('roleCode')?.setValue(this.user.role.code);
    }
  }


  saveUser(): void {
    if(this.userForm.valid){
      const value = this.userForm.value;
      this.modal.close(value);
    }
  }
}
