import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReclamationTechnicienComponent } from './reclamation-technicien.component';

describe('ReclamationTechnicienComponent', () => {
  let component: ReclamationTechnicienComponent;
  let fixture: ComponentFixture<ReclamationTechnicienComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReclamationTechnicienComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReclamationTechnicienComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
