import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {RegisterComponent, RegisterPageComponent} from "./register-page/register-page.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RegisterComponent, RegisterPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'WebFrontend';
}
