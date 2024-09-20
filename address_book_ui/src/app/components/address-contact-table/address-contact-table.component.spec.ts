import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressContactTableComponent } from './address-contact-table.component';

describe('AddressContactTableComponent', () => {
  let component: AddressContactTableComponent;
  let fixture: ComponentFixture<AddressContactTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddressContactTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddressContactTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
