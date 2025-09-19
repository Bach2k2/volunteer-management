import { Routes } from '@angular/router';

import { Home } from './home/home';
import { Dashboard} from './dashboard/dashboard';
import { Login } from './login/login';
import { Register } from './register/register';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'dashboard', component: Dashboard },
    { path: 'login', component: Login },
    { path: 'register', component: Register },
];
