import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterSComponent } from './footer-s.component';

describe('FooterSComponent', () => {
  let component: FooterSComponent;
  let fixture: ComponentFixture<FooterSComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FooterSComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
