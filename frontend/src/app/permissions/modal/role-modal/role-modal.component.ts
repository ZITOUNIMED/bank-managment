import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-role-modal',
  templateUrl: './role-modal.component.html',
  styleUrls: ['./role-modal.component.scss']
})
export class RoleModalComponent implements OnInit {

  roleForm = new FormGroup({
    code: new FormControl('', [Validators.required]),
    label: new FormControl('', [Validators.required]),
  });
  

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  saveRole(){
    if(this.roleForm.valid){
      const value = this.roleForm.value;
      this.modal.close(value);
    }
  }
}
