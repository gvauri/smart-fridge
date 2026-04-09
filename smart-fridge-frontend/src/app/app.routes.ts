import {Routes} from '@angular/router';
import {Container} from './container/container';
import {Login} from './auth/login/login';
import {Signup} from './auth/signup/signup';
import {authGuard} from './shared/guards/auth-guard';

export const routes: Routes = [
  {
    path: '',
    component: Container,
    canActivate: [authGuard]
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'signup',
    component: Signup
  }
];
