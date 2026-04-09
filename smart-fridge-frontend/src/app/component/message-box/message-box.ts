import {Component, computed, input} from '@angular/core';
import {MessageType} from '../../shared/models/message.type';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-message-box',
  imports: [
    MatIcon
  ],
  templateUrl: './message-box.html',
  styleUrl: './message-box.scss',
})
export class MessageBox {
  message = input.required<string>();
  type = input.required<MessageType>();

  protected icon = computed(() => {
    switch (this.type()) {
      case MessageType.INFO:
        return 'info';
      case MessageType.WARNING:
        return 'warning';
      case MessageType.ERROR:
        return 'error';
      case MessageType.DONE:
        return 'done';
    }
  });

  protected style = computed(() => {
    switch (this.type()) {
      case MessageType.INFO:
        return 'info-message';
      case MessageType.WARNING:
        return 'warning-message';
      case MessageType.ERROR:
        return 'error-message';
      case MessageType.DONE:
        return 'done-message';
    }
  });

}
