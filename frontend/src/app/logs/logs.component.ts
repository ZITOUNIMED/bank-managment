import { Component, OnInit } from '@angular/core';
import { TraceDataModel } from './models/trace-data.model';
import { TraceDataService } from './trace-data.service';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.scss']
})
export class LogsComponent implements OnInit {

  tracesData: TraceDataModel[] = [];

  constructor(private traceDataService: TraceDataService) { }

  ngOnInit(): void {
    this.traceDataService.getTracesData()
    .subscribe(tracesData => (this.tracesData = tracesData))
  }

}
