import {computed, inject, Injectable, signal} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {jwtDecode} from 'jwt-decode';
import {LoginDTO} from '../models/auth/login.dto';
import {SignupDTO} from '../models/auth/signup.dto';
import {AuthUser} from '../models/auth/auth-user.model';
import {JwtToken} from '../models/auth/jwt-token.type';
import {AuthResponse} from '../models/auth/auth-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly baseurl: string = 'http://localhost:8080/auth';
  private http = inject(HttpClient);
  private _currentUser = signal<AuthUser | null>(null);
  isLoggedIn = computed(() => this._currentUser() !== null);
  userId = computed<string | null>(() => this._currentUser()?.id ?? null);

  login(dto: LoginDTO): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseurl}/login`, dto)
      .pipe(tap((response: AuthResponse) => this.handleAuthSuccess(response.token)));
  }

  signup(dto: SignupDTO): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseurl}/signup`, dto)
      .pipe(tap((response: AuthResponse) => this.handleAuthSuccess(response.token)));
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this._currentUser.set(null);
  }

  restoreSession(): void {
    const token = localStorage.getItem('authToken');
    if (!token) return;

    try {
      this.handleAuthSuccess(token);
    } catch {
      this.logout();
    }
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  private handleAuthSuccess(token: string): void {
    const decoded: JwtToken = jwtDecode<JwtToken>(token);

    if (decoded.exp * 1000 < Date.now()) {
      throw new Error('Token expired');
    }

    localStorage.setItem('authToken', token);

    this._currentUser.set({
      id: decoded.sub,
      // email: decoded.email bruuchts maybe no?
    });
  }
}
