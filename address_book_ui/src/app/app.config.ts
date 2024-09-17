import { ApplicationConfig, provideZoneChangeDetection, importProvidersFrom } from '@angular/core';
import { provideRouter, withEnabledBlockingInitialNavigation } from '@angular/router';
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from '@env/environment';
import { HttpClient } from '@angular/common/http';
import { routes } from './app.routes';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';

export const appConfig: ApplicationConfig = {
  
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes, withEnabledBlockingInitialNavigation()),    
    importProvidersFrom(
      HttpClient,
      AuthModule.forRoot({
        domain: environment.auth0Domain,     
        clientId: environment.auth0Client, 
        authorizationParams: {
          redirect_uri:  environment.ssrRedirectUri,
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
      }),
      BrowserModule,
      BrowserAnimationsModule,
      DatePipe
    ),
  ],
};
