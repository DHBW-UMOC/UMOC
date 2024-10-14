import { Component } from '@angular/core';
import {MakeRequestService} from "../make-request.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})

export class RegisterComponent {
  username: string = '';
  password: string = '';
  email: string = '';

  constructor(private makeRequestService: MakeRequestService) {}

  onSubmit() {
    const userData = {
      username: this.username,
      password: this.password,
      email: this.email
    };

    this.makeRequestService.getURL('register/'+userData.username+'/'+userData.password+'/'+userData.email)
    .subscribe({
      next: (response: any) => {
        console.log('Response from API:', response);
      },
      error: (error: any) => {
        console.error('Error while calling API:', error);
      }
    });
  }
}
