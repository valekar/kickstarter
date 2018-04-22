import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalPledgeComponent } from './total-pledge.component';

describe('TotalPledgeComponent', () => {
  let component: TotalPledgeComponent;
  let fixture: ComponentFixture<TotalPledgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TotalPledgeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalPledgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
