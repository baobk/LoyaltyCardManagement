package com.neo.project.LoyaltyCard.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class LoyaltyCard {
    @Id
    Integer id;

    @Column(name = "code")
    String code;

    @Column(name = "phone")
    String phone;

    @Column(name = "loyalty_card_type_id")
    Integer loyaltyCartTypeId;

    @Column(name = "point")
    double point;

    @Column(name = "total_spent")
    double totalSpent;

    @Column(name = "start_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date startDate;

    @Column(name = "end_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date endDate;

    @Column(name = "created_on")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date createdOn;

    @Column(name = "modified_on")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy",timezone="GMT")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date modifiedOn;
}
