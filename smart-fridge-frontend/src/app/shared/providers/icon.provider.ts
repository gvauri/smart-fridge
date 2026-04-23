import {inject, makeEnvironmentProviders, provideAppInitializer} from '@angular/core';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';

export function provideIcons() {
  return makeEnvironmentProviders([
    provideAppInitializer(() => {
      const registry = inject(MatIconRegistry);
      const sanitizer = inject(DomSanitizer);
      registry.addSvgIcon('delete', sanitizer.bypassSecurityTrustResourceUrl('icons/delete.svg'));
      registry.addSvgIcon('edit', sanitizer.bypassSecurityTrustResourceUrl('icons/edit.svg'));
      registry.addSvgIcon('vegetable', sanitizer.bypassSecurityTrustResourceUrl('icons/vegetable.svg'));
    })
  ]);
}
