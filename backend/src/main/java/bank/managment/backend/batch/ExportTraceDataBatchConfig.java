package bank.managment.backend.batch;

import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bank.managment.backend.entities.TraceData;
import bank.managment.backend.services.IExportService;
import bank.managment.backend.services.ITraceDataService;

@Configuration
public class ExportTraceDataBatchConfig {
	private static final Logger logger = LoggerFactory.getLogger(ExportTraceDataBatchConfig.class);
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private ITraceDataService traceDataService;
    
    @Autowired
    private IExportService exportService;
    
    @Value("${export.trace-data.folder}")
    private String exportFolder;
    
    @Bean
    public Job exportTraceData() throws Exception {
    	logger.info("Create Export Trace Data Job.");
        return this.jobBuilderFactory.get("exportTraceData")
        		.incrementer(new RunIdIncrementer())
                .start(exportTraceDataStep())
                .next(cleanTraceDataStep())
                .build();
    }
    
    @Bean
    public Step exportTraceDataStep() throws Exception {
    	logger.info("Create Export Trace Data Step.");
    	Tasklet tasklet = (contribution, chunkContext) -> {
    		logger.info("Execute Export Trace Data Tasklet.");
    		Date date = Date.valueOf(LocalDate.now().minusMonths(1));
    		List<TraceData> traceDatas = traceDataService.findByDateLessThan(date);
    		String fileName = exportFolder + "\\traceData-"+ LocalDate.now().toString() + "-" + System.currentTimeMillis() +".xlsx";
    		FileOutputStream outputStream = new FileOutputStream(fileName);
    		exportService.exportTraceData(traceDatas, outputStream);
    		return RepeatStatus.FINISHED;
    	};
    	
        return this.stepBuilderFactory
        		.get("exportTraceDataStep")
        		.tasklet(tasklet)
        		.build();
    }
    
    @Bean
    public Step cleanTraceDataStep() throws Exception {
    	logger.info("Create Clean Trace Data Step.");
    	Tasklet tasklet = (contribution, chunkContext) -> {
    		logger.info("Execute Clean Trace Data Tasklet.");
    		Date date = Date.valueOf(LocalDate.now().minusMonths(1));
    		traceDataService.deleteByDateLessThan(date);
    		return RepeatStatus.FINISHED;
    	};

        return this.stepBuilderFactory
        		.get("cleanTraceDataStep")
        		.tasklet(tasklet)
        		.build();
    }
}
