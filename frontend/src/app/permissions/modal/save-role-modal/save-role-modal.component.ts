import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-save-role-modal',
  templateUrl: './save-role-modal.component.html',
  styleUrls: ['./save-role-modal.component.scss']
})
export class SaveRoleModalComponent implements OnInit {

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  save(): void {
    this.modal.close(true);
  }

  cancel(): void {
    this.modal.close(false);
  }

}
