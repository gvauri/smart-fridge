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
      registry.addSvgIcon('bread', sanitizer.bypassSecurityTrustResourceUrl('icons/bread.svg'));
      registry.addSvgIcon('dairy', sanitizer.bypassSecurityTrustResourceUrl('icons/dairy.svg'));
      registry.addSvgIcon('drink', sanitizer.bypassSecurityTrustResourceUrl('icons/drink.svg'));
      registry.addSvgIcon('fish', sanitizer.bypassSecurityTrustResourceUrl('icons/fish.svg'));
      registry.addSvgIcon('fruit', sanitizer.bypassSecurityTrustResourceUrl('icons/fruit.svg'));
      registry.addSvgIcon('meat', sanitizer.bypassSecurityTrustResourceUrl('icons/meat.svg'));
      registry.addSvgIcon('sauce', sanitizer.bypassSecurityTrustResourceUrl('icons/sauce.svg'));
      registry.addSvgIcon('snack', sanitizer.bypassSecurityTrustResourceUrl('icons/snack.svg'));
      registry.addSvgIcon('spice', sanitizer.bypassSecurityTrustResourceUrl('icons/spice.svg'));
      registry.addSvgIcon('sweet', sanitizer.bypassSecurityTrustResourceUrl('icons/sweet.svg'));
      registry.addSvgIcon('dessert', sanitizer.bypassSecurityTrustResourceUrl('icons/dessert.svg'));
      registry.addSvgIcon('other', sanitizer.bypassSecurityTrustResourceUrl('icons/question-mark.svg'));
    })
  ]);
}
