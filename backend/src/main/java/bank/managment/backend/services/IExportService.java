package bank.managment.backend.services;

import java.io.OutputStream;
import java.util.List;

import bank.managment.backend.entities.TraceData;

public interface IExportService {
	void exportTraceData(List<TraceData> tracesData, OutputStream output);
}
