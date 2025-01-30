import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComercianteComponent } from './comerciante.component';

describe('ComercianteComponent', () => {
  let component: ComercianteComponent;
  let fixture: ComponentFixture<ComercianteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComercianteComponent]
    });
    fixture = TestBed.createComponent(ComercianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
