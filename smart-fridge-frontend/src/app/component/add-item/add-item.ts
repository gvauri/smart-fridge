import {Component, inject, signal} from '@angular/core';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel, MatSuffix} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';
import {ItemService} from '../../shared/services/item.service';
import {IconType} from '../../shared/models/item/item-icon.type';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {ErrorMessageService} from '../../shared/services/error-message.service';
import {MessageBox} from '../message-box/message-box';
import {MessageType} from '../../shared/models/message.type';


@Component({
  selector: 'app-add-item',
  imports: [
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatInput,
    MatSelect,
    MatSuffix,
    MatOption,
    MatDialogActions,
    MatButton,
    MatDialogTitle,
    MatDatepicker,
    MatDatepickerToggle,
    MatDatepickerInput,
    MessageBox
  ],
  templateUrl: './add-item.html',
  styleUrl: './add-item.scss',
})
export class AddItem {
  protected errorMessage = signal<string>('');
  protected hasErrorOccurred = signal<boolean>(false);
  private readonly service = inject(ItemService)
  private readonly errorService = inject(ErrorMessageService);
  private readonly dialogRef = inject(MatDialogRef<AddItem>);
  private readonly fb = inject(FormBuilder);
  protected readonly MessageType = MessageType;
  protected readonly iconTypes = Object.values(IconType).filter((v): v is IconType => typeof v === 'string');
  protected readonly formAdd = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    icon: [null as IconType | null, Validators.required],
    amount: [1, [Validators.required, Validators.min(1)]],
    buyingDate: [null as Date | null, Validators.required],
    expirationDate: [null as Date | null, Validators.required],
  });

  protected submit(): void {
    if (this.formAdd.valid) {
      this.service.createItem({
        icon: this.formAdd.get("icon")?.value ?? IconType.OTHER,
        name: this.formAdd.get("name")?.value ?? '',
        description: this.formAdd.get("description")?.value ?? '',
        buyingDate: this.formAdd.get("buyingDate")?.value ?? new Date(),
        expirationDate: this.formAdd.get("expirationDate")?.value ?? new Date(),
        amount: this.formAdd.get("amount")?.value ?? 1,
      }).subscribe({
        next: () => this.cancel(),
        error: (error) => {
          this.hasErrorOccurred.set(true);
          this.errorMessage.set(this.errorService.getErrorMessage(error));
        }
      });
    } else {
      this.formAdd.markAllAsTouched()
    }
  }

  protected cancel(): void {
    this.dialogRef.close();
  }
}
