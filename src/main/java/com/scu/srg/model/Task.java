package com.scu.srg.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Task {
    String Id;
    Date startDate;
    Date endDate;
    Employee personAssigned;
}
