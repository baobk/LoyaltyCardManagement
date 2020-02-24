package com.neo.project.LoyaltyCard.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LoyaltyCard {
    Integer id;
    String code;
    String phone;
    Integer loyaltyCartTypeId;
    double point;
    double totalSpent;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date endDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date createdOn;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date modifiedOn;
}
