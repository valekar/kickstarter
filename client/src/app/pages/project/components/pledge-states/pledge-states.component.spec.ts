import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PledgeStatesComponent } from './pledge-states.component';

describe('PledgeStatesComponent', () => {
  let component: PledgeStatesComponent;
  let fixture: ComponentFixture<PledgeStatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PledgeStatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PledgeStatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
