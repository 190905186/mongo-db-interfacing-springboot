package com.example.mongoservice.controller.leaveDb;


import com.example.mongoservice.model.leaveDb.Leave;
import com.example.mongoservice.model.leaveDb.LeavesTemp;
import com.example.mongoservice.exception.leaveDb.EmployeeFoundException;
import com.example.mongoservice.exception.leaveDb.EmployeeIdNotFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveNotFoundException;
import com.example.mongoservice.service.leaveDb.ILeavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeavesController {
    @Autowired
    private ILeavesService leaveService;

    //get leave history of all employees
    @GetMapping("/leaveService")
    public List<LeavesTemp> getAllLeaveHistories() {
        return leaveService.getAllLeaveHistories();
    }

    //post a new entry of leave template for a new employee
    @PostMapping("/leaveService/leaveTemplate/add")
    public LeavesTemp addCustomer(@RequestBody LeavesTemp leaveTempObj) throws EmployeeFoundException {
        return leaveService.addLeaveTemp(leaveTempObj);
    }

    //post a new entry of leave for an existing employee
    @PostMapping("/leaveService/leave/add/{id}")
    public LeavesTemp addCustomer(@RequestBody Leave leaveObj, @PathVariable("id") int empId) throws EmployeeIdNotFoundException, LeaveFoundException {
        return leaveService.addLeave(leaveObj,empId);
    }

    //PUT - update more than one property of the leave template
    @PutMapping("/leaveService/update")
    public LeavesTemp updateCustomer(@RequestBody LeavesTemp leaveTempObj) throws EmployeeIdNotFoundException {
        return leaveService.updateLeaveTemp(leaveTempObj);
    }

    //Patch - update the leave property of the leave template
    @PutMapping("/leaveService/update/{id}")
    public LeavesTemp updateCustomer(@RequestBody Leave leaveObj, @PathVariable("id") int  empId) throws LeaveNotFoundException, EmployeeIdNotFoundException {
        return leaveService.updateLeaveById(leaveObj,empId);
    }

    //Delete- leaveHistory entry of an employee returns nothing
    @DeleteMapping("/leaveService/deleteVoid/{id}")
    public void deleteLeaveHistoryByEmpIdVoidReturn(@PathVariable("id") int empId) throws EmployeeIdNotFoundException {
        leaveService.deleteLeaveHistoryByEmpIdVoidReturn(empId);
    }

    //Delete- leaveHistory entry of an employee returns removed leave template
    @DeleteMapping("/leaveService/deleteReturn/{id}")
    public LeavesTemp deleteLeaveHistoryByEmpId(@PathVariable("id") int empId) throws EmployeeIdNotFoundException {
        return leaveService.deleteLeaveHistoryByEmpId((empId));
    }

}
