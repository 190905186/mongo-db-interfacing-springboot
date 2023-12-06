package com.example.mongoservice.model.leaveDb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class LeavesTemp {
    @Id
    private int empId;
    private List<Leave> leaveList;
}
