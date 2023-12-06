package com.example.mongoservice.model.leaveDb;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Getter
@Setter
@Document
public class Leave {
    @Id
    @Generated
    private int leaveId;
    private String type;
    private Date startingDate;
    private Date endingDate;
}
