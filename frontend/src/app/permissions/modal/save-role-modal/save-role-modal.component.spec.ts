import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveRoleModalComponent } from './save-role-modal.component';

describe('SaveRoleModalComponent', () => {
  let component: SaveRoleModalComponent;
  let fixture: ComponentFixture<SaveRoleModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveRoleModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaveRoleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
