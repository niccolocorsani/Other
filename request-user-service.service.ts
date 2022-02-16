import { Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {UserResponse} from '../response/user-response';

@Injectable({
  providedIn: 'root'
})
export class RequestUserServiceService {
  private url = 'http://localhost:8080';
  private listElements: Array<UserResponse> = [];

  /*    String headerXauthSubject = req.getHeader("x-auth-subject");
        String headerXauthRole = req.getHeader("x-auth-roles");
        String eMail = req.getHeader("x-auth-email");
        String username = req.getHeader("x-auth-username");
        String userid = req.getHeader("x-auth-userid");*/

  public httpOptions = {
    headers: new HttpHeaders({
      'x-auth-subject': 'soggettoProva',
      'x-auth-roles': 'ruoloProva ',
      'x-auth-email': 'example@example',
      'x-auth-username': 'userexample',
      'x-auth-userid': 'userexample'
    })
  };

  myObserver = {
    error: (err: any) => alert('Observer got an error: ' + err), // in caso di errori (di vario genere)
  };


  constructor(public http: HttpClient) {
  }


  // LISTA SERVIZI AGGIUNTIVI
  public getUserById(): Observable<any> {
    this.http.get<any>(this.url + '/operations-backend/api/user/1', this.httpOptions).subscribe(data => {

      },
      error => {
        alert('messaggio di errore' + error.message+'..');
      });
    this.http.get<any>(this.url + '/operations-backend/api/user/2').subscribe(this.myObserver);
    return this.http.get<any>(this.url + '/operations-backend/api/user/2');
    /*return this.http.get<any>(`${this.url}/operations-backend/api/user`).pipe(map((data: any) => {
       data.forEach(element => {
         this.listElements.push('ooo');
       });
       alert(this.listElements[0].email);
       return this.listElements;
     }));*/
  }

  public getUserList(): any {
    //// metodo asicnrono
    this.http.get<any>(this.url + '/operations-backend/api/user', this.httpOptions).pipe(
      // delay(1000),
      map((data: any) => {
        // @ts-ignore
        data.forEach(element => {
          this.listElements.push(new UserResponse(element));
          let response = new UserResponse(element);
        });
        return this.listElements;
      })).subscribe(this.myObserver);
    return this.listElements;
  }

  public getUserListWithoutHTTP(): any {
    //// metodo asicnrono
    this.listElements.splice(0, this.listElements.length);
    this.http.get<any>(this.url + '/operations-backend/api/user').pipe(
      // delay(1000),
      map((data: any) => {
        // @ts-ignore
        data.forEach(element => {

          this.listElements.push(new UserResponse(element));
          let response = new UserResponse(element);
        });
        return this.listElements;
      })).subscribe(this.myObserver);
    return this.listElements;
  }

  public postUserWithHeader(httpOptions: any): Observable<any> {
    return this.http.post<any>(this.url + '/operations-backend/api/user', httpOptions);
  }


  public postUser(userName: string, email: string): any {
    let userResponse = new UserResponse();
    userResponse.username = userName;
    userResponse.email = email;
    userResponse.name = '------';
    this.http.post<UserResponse>(this.url + '/operations-backend/api/user', userResponse).pipe(
      catchError(this.handleError)
    ).subscribe(this.myObserver); ////sembra che senza sto subscribe non sia in grado di fare la richiesta
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }
}


