package com.sample.thirdpartyapisim.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetIssueResponse {
    private String project_id;
    private List<Issue> issues;

}
