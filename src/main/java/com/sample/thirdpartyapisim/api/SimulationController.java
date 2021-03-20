package com.sample.thirdpartyapisim.api;

import com.sample.thirdpartyapisim.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SimulationController {

    private Map<String, FeedSampleDataRequest> temporaryData = new HashMap<>();

    public SimulationController() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mma z");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.MINUTE, 0);

        List<Issue> issues = new ArrayList<>();
        List<ChangeLog> changeLogs1 = new ArrayList<>();
        changeLogs1.add(new ChangeLog(formatter.format(calendar.getTime()), "created", "open"));

        calendar.set(Calendar.DAY_OF_MONTH, 3);
        changeLogs1.add(new ChangeLog(formatter.format(calendar.getTime()), "open", "in_progress"));

        calendar.set(Calendar.MONTH, 2);
        changeLogs1.add(new ChangeLog(formatter.format(calendar.getTime()), "in_progress", "close"));

        Issue issue1 = new Issue("issue1", "bug", "open", changeLogs1);
        issues.add(issue1);

        List<ChangeLog> changeLogs2 = new ArrayList<>();
        changeLogs2.add(new ChangeLog(formatter.format(calendar.getTime()), "created", "open"));

        calendar.set(Calendar.DAY_OF_MONTH, 5);
        changeLogs2.add(new ChangeLog(formatter.format(calendar.getTime()), "open", "in_progress"));

        calendar.set(Calendar.MONTH, 6);
        changeLogs2.add(new ChangeLog(formatter.format(calendar.getTime()), "in_progress", "close"));

        Issue issue2 = new Issue("issue2", "cr", "open", changeLogs2);
        issues.add(issue2);

        GetIssueResponse getIssueResponse = new GetIssueResponse("sample_project", issues);
        FeedSampleDataRequest feedSampleDataRequest = new FeedSampleDataRequest(0, getIssueResponse);

        temporaryData.put(feedSampleDataRequest.getGetIssueResponse().getProject_id(), feedSampleDataRequest);
    }

    @PostMapping("/feed")
    ResponseEntity createSampleData(@RequestBody FeedSampleDataRequest feedSampleDataRequest) {
        temporaryData.put(feedSampleDataRequest.getGetIssueResponse().getProject_id(), feedSampleDataRequest);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/getissues")
    ResponseEntity<GetIssueResponse> getIssues(@RequestBody final GetIssueRequest getIssueRequest) {
        log.info("Received project id {}", getIssueRequest.getProject_id());
        if(temporaryData.containsKey(getIssueRequest.getProject_id())){
            FeedSampleDataRequest sampleData = temporaryData.get(getIssueRequest.getProject_id());
            if(sampleData.getDelayToSimulate() > 0 ) {
                try {
                    log.info("Simulating delay {}", TimeUnit.SECONDS.toMillis(sampleData.getDelayToSimulate()));
                    Thread.sleep(TimeUnit.SECONDS.toMillis(sampleData.getDelayToSimulate()));
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            return ResponseEntity.ok(sampleData.getGetIssueResponse());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
