import {Component, DestroyRef, inject, signal} from '@angular/core';
import {AuthService} from '../../shared/services/auth.service';
import {ErrorMessageService} from '../../shared/services/error-message.service';
import {Router} from '@angular/router';
import {MessageType} from '../../shared/models/message.type';
import {LoginDTO} from '../../shared/models/auth/login.dto';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {finalize} from 'rxjs';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {MessageBox} from '../../component/message-box/message-box';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [
    MatProgressSpinner,
    MessageBox,
    FormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private authService = inject(AuthService);
  private errorService = inject(ErrorMessageService);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  protected readonly MessageType = MessageType;
  protected isLoading = signal<boolean>(false);
  protected hasErrorOccurred = signal<boolean>(false);
  protected errorMessage = signal<string>('');

  // TODO: implement form

  protected login(): void {
    this.isLoading.set(true);

    const credentials: LoginDTO = {
      email: '',
      password: ''
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
