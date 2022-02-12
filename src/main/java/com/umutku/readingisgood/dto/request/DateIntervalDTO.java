package com.umutku.readingisgood.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateIntervalDTO {
    @NotNull
    Date startDate;

    @NotNull
    Date endDate;
}
