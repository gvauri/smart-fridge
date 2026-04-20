import {Component, inject} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from './component/header/header.component';
import {AuthService} from './shared/services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private authService = inject(AuthService);

  constructor() {
    this.authService.restoreSession();
  }
}
