import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {catchError, throwError} from 'rxjs';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const snackBar = inject(MatSnackBar);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {

      if (error.status === 401) {
        snackBar.open('Bitte melde dich an.', 'OK', { duration: 3000, verticalPosition: 'top' });
        router.navigateByUrl('/login');
      }

      return throwError(() => error);
    })
  );
};
