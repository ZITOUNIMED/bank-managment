package bank.managment.backend.services.impl;

import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import bank.managment.backend.entities.TraceData;
import bank.managment.backend.export.TraceDataExporter;
import bank.managment.backend.services.IExportService;

@Service
public class ExportServiceImpl implements IExportService {

	@Override
	public void exportTraceData(List<TraceData> tracesData, OutputStream output) {
		TraceDataExporter exporter = new TraceDataExporter();
		exporter.export(tracesData, output);
	}
}
