import {Component, DestroyRef, inject, signal} from '@angular/core';
import {AuthService} from '../../shared/services/auth.service';
import {ErrorMessageService} from '../../shared/services/error-message.service';
import {Router, RouterLink} from '@angular/router';
import {MessageType} from '../../shared/models/message.type';
import {LoginDTO} from '../../shared/models/auth/login.dto';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {finalize} from 'rxjs';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {MessageBox} from '../../component/message-box/message-box';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-login',
  imports: [
    MatProgressSpinner,
    MessageBox,
    FormsModule,
    MatFormField,
    MatLabel,
    ReactiveFormsModule,
    MatButton,
    MatInput,
    RouterLink
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private errorService = inject(ErrorMessageService);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  protected readonly MessageType = MessageType;
  protected isLoading = signal<boolean>(false);
  protected hasErrorOccurred = signal<boolean>(false);
  protected errorMessage = signal<string>('');

  protected readonly loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  });

  protected login(): void {
    this.isLoading.set(true);
    if (this.loginForm.valid) {
      const credentials: LoginDTO = {
        email: this.loginForm.get("email")?.value ?? '',
        password: this.loginForm.get("password")?.value ?? ''
      };

      this.authService.login(credentials)
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
