package bank.managment.backend.trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

import com.google.gson.Gson;

import bank.managment.backend.config.security.UserDetailsImpl;
import bank.managment.backend.entities.TraceData;
import bank.managment.backend.services.ITraceDataService;

@Aspect
@Component
public class TraceAspect {
	private static final Logger logger = LoggerFactory.getLogger(TraceAspect.class);
	@Autowired
	private ITraceDataService traceDataService;

	@Around("@annotation(Trace)")
	public Object trace(ProceedingJoinPoint call) {
		logger.info("Start trace: " + call.getSignature().getName());
		try {
			TraceData traceData = new TraceData();
			String args = new Gson().toJson(call.getArgs());
			traceData.setArgs(args);
			traceData.setMethod(call.getSignature().getName());
			traceData.setDate(Date.valueOf(LocalDate.now()));
			UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			traceData.setLogin(userDetails.getUsername());
			traceDataService.save(traceData);
			return call.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new TraceException(e.getMessage());
		}
	}
}
