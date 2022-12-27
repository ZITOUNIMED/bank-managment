import { Component, OnInit } from '@angular/core';
import { DataService } from './data.service';
import { MessagesService } from './messages.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  data: any;
  messages: any;
  constructor(private dataService: DataService, private messagesService: MessagesService) { }

  ngOnInit(): void {
    this.dataService.getData()
    .subscribe(data => {
      this.data = data;
    });

    this.messagesService.getMessages()
    .subscribe(messages => {
      this.messages = messages;
    })
  }

}
