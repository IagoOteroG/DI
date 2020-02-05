import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { contactMessage } from '../contactMessage';
import {ServiceComponentContactusService} from '../service-component-contactus.service'

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {


  constructor(private contactUs: ServiceComponentContactusService ) { }
  form: FormGroup;
  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      name: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(16)]),
      apellido: new FormControl('', [Validators.required, Validators.maxLength(16)]),
      comment: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(100)])

    })
  }
  onSubmit(name: string, apellido: string, sapellido: string, textarea: string, email: string) {

    
    this.contactUs.add({name,apellido,sapellido,textarea,email} as contactMessage);
   

    this.form.reset("");
    
  }

}
