import {Component, DestroyRef, inject, signal} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {MessageBox} from '../../component/message-box/message-box';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import {MessageType} from '../../shared/models/message.type';
import {AuthService} from '../../shared/services/auth.service';
import {ErrorMessageService} from '../../shared/services/error-message.service';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {finalize} from 'rxjs';
import {SignupDTO} from '../../shared/models/auth/signup.dto';

@Component({
  selector: 'app-signup',
  imports: [
    MatButton,
    MatFormField,
    MatInput,
    MatLabel,
    MatProgressSpinner,
    MessageBox,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './signup.html',
  styleUrl: './signup.scss',
})
export class Signup {
  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private errorService = inject(ErrorMessageService);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  protected readonly MessageType = MessageType;
  protected isLoading = signal<boolean>(false);
  protected hasErrorOccurred = signal<boolean>(false);
  protected errorMessage = signal<string>('');

  protected readonly signupForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  });

  protected signup(): void {
    this.isLoading.set(true);
    if (this.signupForm.valid) {
      const credentials: SignupDTO = {
        email: this.signupForm.get("email")?.value ?? '',
        password: this.signupForm.get("password")?.value ?? '',
        name: this.signupForm.get("name")?.value ?? '',
      };

      this.authService.signup(credentials)
        .pipe(takeUntilDestroyed(this.destroyRef), finalize(() => this.isLoading.set(false)))
        .subscribe({
          next: () => this.router.navigateByUrl('/'),
          error: (error) => {
            this.hasErrorOccurred.set(true);
            this.errorMessage.set(this.errorService.getErrorMessage(error));
          }
        });
    }
  }

}
