import {Component, computed, inject} from '@angular/core';
import {NgOptimizedImage} from '@angular/common';
import {AuthService} from '../../shared/services/auth.service';
import {MatToolbar} from '@angular/material/toolbar';
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    NgOptimizedImage,
    MatToolbar,
    MatButton,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  private authService = inject(AuthService);
  protected isLoggedIn = computed(() => this.authService.isLoggedIn);

  protected logout(): void {
    this.authService.logout();
  }

}
