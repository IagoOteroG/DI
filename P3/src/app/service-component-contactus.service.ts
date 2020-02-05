import { Injectable } from '@angular/core';
import { contactMessage } from './contactMessage';

@Injectable({
  providedIn: 'root'
})
export class ServiceComponentContactusService {

  contacts: string[] = [];

  add(contac: contactMessage) {

    var contactmessage = "name:|" + contac.name + "|apellido|" + contac.apellido + "|sapellido|" + contac.sapellido + "|textarea|" + contac.textarea + "|email|" + contac.email;

    this.contacts.push(contactmessage);
    console.log(this.contacts);
  }

  clear() {
    this.contacts = [];
  }
}

