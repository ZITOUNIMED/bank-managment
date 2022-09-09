package bank.managment.backend.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExportTraceDataScheduler {
	private static final Logger logger = LoggerFactory.getLogger(ExportTraceDataScheduler.class);
	@Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    
    @Scheduled(cron = "0 0 08 1 * ?", zone = "Europe/Paris")
    public void perform() {
    	logger.info("Launch Export Trace Data Job.");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("JobId", System.currentTimeMillis())
                .toJobParameters();
        try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (JobRestartException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
    }
}
