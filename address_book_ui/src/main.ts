import { bootstrapApplication } from '@angular/platform-browser';
import { provideAuth0 } from '@auth0/auth0-angular';
import { AppComponent } from './app/app.component';
import { environment } from './environment/environment';
import { provideClientHydration } from '@angular/platform-browser'; 
import { withHttpTransferCacheOptions } from '@angular/platform-browser';

console.log('Auth0 Domain:', environment.auth0Domain);
console.log('Auth0 Client ID:', environment.auth0Client);

bootstrapApplication(AppComponent, {
  providers: [
    provideClientHydration(
      withHttpTransferCacheOptions({
        includePostRequests: true,
      }),
    ),
    provideAuth0({
      domain: environment.auth0Domain,
      clientId: environment.auth0Client,
      authorizationParams: {
        redirect_uri: window.location.origin,
      },
    }),
  ],
});
