import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const PATH = 'http://localhost:8080/api/';
const httpOptions = { headers: new HttpHeaders ({'Content-Type': 'application/json', 'responseType': 'text'})};

@Injectable({ providedIn: 'root' })


export class ApiService {

  constructor(private http: HttpClient) { }

  creatorQRC ( rete: String, password: String, type: String ): Observable<any> {
    return this.http.post( PATH + 'generateQRC', { rete, password, type }, httpOptions );
  }
}