import { ApplicationConfig, provideZoneChangeDetection, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from '@env/environment';
import { HttpClient } from '@angular/common/http';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser'; 
import { PlatformService } from './shared/platform.service';
//TODO probably something here is not working

export const appConfig: ApplicationConfig = {
  
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    
    importProvidersFrom(
      HttpClient,
      AuthModule.forRoot({
        domain: environment.auth0Domain,     
        clientId: environment.auth0Client, 
        authorizationParams: {
          redirect_uri:  window.location.origin,
          audience: environment.auth0Audience, 
        },
        useRefreshTokens: false, 
        httpInterceptor: {
          allowedList: [
            {
              uri: environment.api + '/*',
              tokenOptions: {
                authorizationParams: {
                  audience: environment.auth0Audience, 
                },
              },
            },
          ],
        },
      })
    ),
  ],
};
