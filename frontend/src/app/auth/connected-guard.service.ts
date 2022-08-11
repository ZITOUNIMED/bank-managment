import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class ConnectedGuardService implements CanActivate {
    constructor(private router: Router){}
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const isConnected = sessionStorage.getItem('isConnected') === 'true';
        if(!isConnected){
            return this.router.navigate(['/sign-in']);
        }
        return isConnected;
    }
}