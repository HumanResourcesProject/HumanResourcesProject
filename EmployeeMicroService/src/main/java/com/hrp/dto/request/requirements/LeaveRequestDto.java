package com.hrp.dto.request.requirements;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveRequestDto {
    private String token;
    private String type; // TRAVEL, HOSPITAL etc.
    private String startDate;
    private String finishDate;
    private int amountOfDay;

}
