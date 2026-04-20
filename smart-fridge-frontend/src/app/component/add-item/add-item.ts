import {Component, inject} from '@angular/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatButton} from '@angular/material/button';
import {ItemService} from '../../shared/services/item.service';
import {IconType} from '../../shared/models/item/item-icon.type';


@Component({
  selector: 'app-add-item',
  imports: [
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatInput,
    MatSelect,
    MatOption,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatDialogActions,
    MatButton,
    MatDialogTitle
  ],
  templateUrl: './add-item.html',
  styleUrl: './add-item.scss',
})
export class AddItem{
  private readonly service = inject(ItemService)

  private readonly dialogRef = inject(MatDialogRef<AddItem>);

  private readonly fb = inject(FormBuilder);

  protected readonly iconTypes = Object.values(IconType);

  protected readonly formAdd = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    icon: [null as IconType | null, Validators.required],
    amount: [1, [Validators.required, Validators.min(1)]],
    buyingDate: [null as Date | null, Validators.required],
    expirationDate: [null as Date | null, Validators.required],
  });

  protected submit(): void{
    if(this.formAdd.valid) {
      this.service.createItem({
        icon: this.formAdd.get("icon")?.value ?? IconType.OTHER,
        name: this.formAdd.get("name")?.value ?? '',
        description: this.formAdd.get("description")?.value ?? '',
        buyingDate: this.formAdd.get("buyingDate")?.value ?? new Date(),
        expirationDate: this.formAdd.get("expirationDate")?.value ?? new Date(),
        amount: this.formAdd.get("amount")?.value ?? 1,
      })
      this.cancel()
    } else {
      this.formAdd.markAllAsTouched()
    }
  }

  protected cancel(): void {
    this.dialogRef.close();
  }
}
