import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpInterceptor, HttpEvent, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ApiService } from './api.service';


@Injectable()

export class AuthInterceptor implements HttpInterceptor {

  constructor( private service: ApiService ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{ 
    request = request.clone({ withCredentials: true, });
    return next.handle(request).pipe(
      catchError(
        (error)=>{      //IF gestione errori http 401 e 403
        if(error instanceof HttpErrorResponse && error.status === 401){
          return this.handle401Error(request, next);
        }
        if(error instanceof HttpErrorResponse && error.status === 403){
          return this.handle403Error(request, next);
        }             
        return throwError(()=> error);
        }
      )
    );
  }

  private handle401Error( request: HttpRequest<any>, next: HttpHandler){
    return next.handle(request);
   }
 
   private handle403Error( request: HttpRequest<any>, next: HttpHandler){
    return next.handle(request);
   }
}

export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ];