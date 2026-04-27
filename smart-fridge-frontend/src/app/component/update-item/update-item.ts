import {Component, inject, signal} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel, MatSuffix} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {ItemService} from '../../shared/services/item.service';
import {ErrorMessageService} from '../../shared/services/error-message.service';
import {IconType} from '../../shared/models/item/item-icon.type';
import {Item} from '../../shared/models/item/item.model';
import {MessageBox} from '../message-box/message-box';
import {MessageType} from '../../shared/models/message.type';

@Component({
  selector: 'app-update-item',
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
    MessageBox,
  ],
  templateUrl: './update-item.html',
  styleUrl: './update-item.scss',
})
export class UpdateItem {
  protected errorMessage = signal<string>('');
  protected hasErrorOccurred = signal<boolean>(false);
  private readonly service = inject(ItemService);
  private readonly errorService = inject(ErrorMessageService);
  private readonly dialogRef = inject(MatDialogRef<UpdateItem>);
  private readonly item = inject<Item>(MAT_DIALOG_DATA);
  private readonly fb = inject(FormBuilder);
  protected readonly MessageType = MessageType;
  protected readonly iconTypes = Object.values(IconType).filter((v): v is IconType => typeof v === 'string');
  protected readonly formUpdate = this.fb.group({
    name: [this.item.name, Validators.required],
    description: [this.item.description, Validators.required],
    icon: [this.item.icon as IconType | null, Validators.required],
    amount: [this.item.amount, [Validators.required, Validators.min(1)]],
    expirationDate: [this.item.expirationDate as Date | null, Validators.required],
  });

  protected submit(): void {
    if (this.formUpdate.valid) {
      this.service.updateItem(this.item.id, {
        icon: this.formUpdate.get('icon')?.value ?? IconType.OTHER,
        name: this.formUpdate.get('name')?.value ?? '',
        description: this.formUpdate.get('description')?.value ?? '',
        expirationDate: this.formUpdate.get('expirationDate')?.value ?? new Date(),
        amount: this.formUpdate.get('amount')?.value ?? 1,
      }).subscribe({
        next: () => this.cancel(),
        error: (error) => {
          this.hasErrorOccurred.set(true);
          this.errorMessage.set(this.errorService.getErrorMessage(error));
        }
      });
    } else {
      this.formUpdate.markAllAsTouched();
    }
  }

  protected cancel(): void {
    this.dialogRef.close();
  }
}
