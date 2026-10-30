import { Component } from '@angular/core';
<<<<<<< HEAD
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
=======
import { RouterOutlet } from '@angular/router';
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3

@Component({
  selector: 'app-root',
  standalone: true,
<<<<<<< HEAD
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
=======
  imports: [RouterOutlet],
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  title = 'frontend-biblioteca';
}