import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhereweareComponent } from './whereweare.component';

describe('WhereweareComponent', () => {
  let component: WhereweareComponent;
  let fixture: ComponentFixture<WhereweareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhereweareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhereweareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
