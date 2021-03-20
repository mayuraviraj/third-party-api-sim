package com.sample.thirdpartyapisim.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ChangeLog {
    private String changed_on;
    private String from_state;
    private String to_state;
}
