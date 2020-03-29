import { Injectable } from '@angular/core';
import { contactMessage } from './contactMessage';
import { mensajes } from './message-list';

@Injectable({
  providedIn: 'root'
})
export class ServiceComponentContactusService {

  contacts: string[] = [];

  add(contac: contactMessage) {

   mensajes.push(contac);
    console.log(mensajes);
  }

  clear() {
    this.contacts = [];
  }
}

