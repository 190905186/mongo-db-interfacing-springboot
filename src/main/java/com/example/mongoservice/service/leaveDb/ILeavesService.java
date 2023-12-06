package com.example.mongoservice.service.leaveDb;

import com.example.mongoservice.model.leaveDb.Leave;
import com.example.mongoservice.model.leaveDb.LeavesTemp;
import com.example.mongoservice.exception.leaveDb.EmployeeFoundException;
import com.example.mongoservice.exception.leaveDb.EmployeeIdNotFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveNotFoundException;

import java.util.List;

public interface ILeavesService {

        List<LeavesTemp> getAllLeaveHistories();
        LeavesTemp addLeave(Leave leave, int empId) throws EmployeeIdNotFoundException, LeaveFoundException;
        LeavesTemp addLeaveTemp(LeavesTemp leave) throws EmployeeFoundException;
        LeavesTemp getLeaveTempByEmpId(int empId) throws EmployeeIdNotFoundException;
        LeavesTemp updateLeaveById(Leave leave, int empId) throws EmployeeIdNotFoundException, LeaveNotFoundException;
        LeavesTemp updateLeaveTemp(LeavesTemp leaveTemp) throws EmployeeIdNotFoundException;
        void deleteLeaveHistoryByEmpIdVoidReturn(int empId) throws EmployeeIdNotFoundException;
        LeavesTemp deleteLeaveHistoryByEmpId(int empId) throws EmployeeIdNotFoundException;

}
