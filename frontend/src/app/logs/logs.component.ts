import { Component, OnInit } from '@angular/core';
import { TraceDataModel } from './models/trace-data.model';
import { LogsService } from './logs.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.scss']
})
export class LogsComponent implements OnInit {

  tracesData: TraceDataModel[] = [];

  constructor(private logsService: LogsService) { }

  ngOnInit(): void {
    this.logsService.getTracesData()
    .subscribe(tracesData => (this.tracesData = tracesData))
  }

  export(): void {
    this.logsService.export()
    .subscribe(blob => {
      const filename = `trace-data-${new Date().getTime()}.xlsx`;
      saveAs(blob, filename);
    });
  }
}
