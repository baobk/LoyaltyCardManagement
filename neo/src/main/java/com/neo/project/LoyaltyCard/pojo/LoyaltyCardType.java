package com.neo.project.LoyaltyCard.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LoyaltyCardType {
    int id;
    String name;
    Double spentThreshold;
    int duration;
    Double discountPercent;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date createdOn;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date modifiedOn;
}
