import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ErrorMessageService {

  getErrorMessage(error: HttpErrorResponse): string {
    if (error.error && error.error.error) {
      return error.error.error;
    }

    switch (error.status) {
      case 400:
        return 'Ungültige Eingabe. Bitte überpüfen Sie die Daten.';
      case 401:
        return 'Ungültige Anmeldedaten.'
      case 404:
        return 'Eintrag nicht gefunden.';
      case 409:
        return 'Dieser Eintrag existiert bereits.';
      case 422:
        return 'Diese Aktion darf nicht durchgeführt werden.'
      case 500:
        return 'Ein unerwarteter Fehler ist aufgetreten.';
      case 503:
        return 'Service steht momentan nicht zur Verfügung.';
    }

    return 'Ein unbekannter Fehler ist aufgetreten.';
  }
}
