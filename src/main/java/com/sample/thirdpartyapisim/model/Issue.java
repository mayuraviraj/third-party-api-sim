package com.sample.thirdpartyapisim.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Issue {
    private String issue_id;
    private String type;
    private String current_state;
    private List<ChangeLog> changelogs;
}
