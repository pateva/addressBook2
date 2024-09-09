import { ApplicationConfig, provideZoneChangeDetection, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from '../environment/environment';
import { HttpClient } from '@angular/common/http';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser'; 

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    // Importing required modules with Auth0 config for CSR
    importProvidersFrom(
      HttpClient,
      AuthModule.forRoot({
        domain: environment.auth0Domain,      // Auth0 domain from environment.ts
        clientId: environment.auth0Client,  // Auth0 client ID from environment.ts
        authorizationParams: {
          redirect_uri: window.location.origin, // Using window.location.origin is fine for CSR
          audience: environment.auth0Audience, // Auth0 audience from environment.ts
        },
        useRefreshTokens: false, // Example: adjust as per your needs
        httpInterceptor: {
          allowedList: [
            {
              uri: environment.api + '/*',
              tokenOptions: {
                authorizationParams: {
                  audience: environment.auth0Audience, // Audience for API requests
                },
              },
            },
          ],
        },
      })
    ),
  ],
};