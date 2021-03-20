package com.sample.thirdpartyapisim;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.thirdpartyapisim.model.ChangeLog;
import com.sample.thirdpartyapisim.model.FeedSampleDataRequest;
import com.sample.thirdpartyapisim.model.Issue;
import com.sample.thirdpartyapisim.model.GetIssueResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Slf4j
public class ThirdPartyApiSimApplication {

	public static void main(String[] args) throws JsonProcessingException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mma z");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println(formatter.format(new Date()));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 12);
		calendar.set(Calendar.MINUTE, 0);

		ChangeLog changeLog1 = new ChangeLog(formatter.format(calendar.getTime()), "open", "in_progress");

		calendar.set(Calendar.DAY_OF_MONTH, 3);
		ChangeLog changeLog2 = new ChangeLog(formatter.format(calendar.getTime()), "in_progress", "testing");

		calendar.set(Calendar.DAY_OF_MONTH, 21);
		ChangeLog changeLog3 = new ChangeLog(formatter.format(calendar.getTime()), "testing", "deploy");

		List<ChangeLog> changeLogs = Arrays.asList(changeLog1, changeLog2, changeLog3);

		Issue issue = new Issue("issue1", "bug", "open", changeLogs);
		GetIssueResponse getIssueResponse = new GetIssueResponse("project1", Arrays.asList(issue));


		log.info("====> " + new ObjectMapper().writeValueAsString(new FeedSampleDataRequest(10, getIssueResponse)));
		SpringApplication.run(ThirdPartyApiSimApplication.class, args);
	}

}
