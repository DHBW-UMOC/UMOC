import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private socket$: WebSocketSubject<any>;

  constructor() {
    this.socket$ = webSocket({
      url: 'ws://localhost:8080/chat',
      deserializer: (e) => e.data,
      serializer: (value) => value
    });
  }

  sendMessage(message: string) {
    this.socket$.next(message);
  }

  getMessages(): Observable<any> {
    return this.socket$.asObservable();
  }

  close() {
    this.socket$.complete();
  }
}
