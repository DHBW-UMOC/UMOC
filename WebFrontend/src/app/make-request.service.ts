import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MakeRequestService {

  constructor(private http: HttpClient) { }

  public getURL(url: String): Observable<any>{
    return this.http.get('http://localhost:8080/api/' + url);
  }
}
