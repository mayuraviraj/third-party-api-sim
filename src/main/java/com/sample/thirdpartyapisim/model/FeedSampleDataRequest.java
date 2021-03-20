package com.sample.thirdpartyapisim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedSampleDataRequest {
    private int delayToSimulate;
    private GetIssueResponse getIssueResponse;
}
