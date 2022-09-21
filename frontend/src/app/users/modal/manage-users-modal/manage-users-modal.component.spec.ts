import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageUsersModalComponent } from './manage-users-modal.component';

describe('ManageUsersModalComponent', () => {
  let component: ManageUsersModalComponent;
  let fixture: ComponentFixture<ManageUsersModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageUsersModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageUsersModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
