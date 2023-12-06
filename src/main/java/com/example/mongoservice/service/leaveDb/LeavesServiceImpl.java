package com.example.mongoservice.service.leaveDb;

import com.example.mongoservice.model.leaveDb.Leave;
import com.example.mongoservice.model.leaveDb.LeavesTemp;
import com.example.mongoservice.exception.leaveDb.EmployeeFoundException;
import com.example.mongoservice.exception.leaveDb.EmployeeIdNotFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveFoundException;
import com.example.mongoservice.exception.leaveDb.LeaveNotFoundException;
import com.example.mongoservice.repository.leaveDb.ILeavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LeavesServiceImpl implements ILeavesService {

    @Autowired
    private ILeavesRepository leaveTempRepo;

    @Override
    public List<LeavesTemp> getAllLeaveHistories() {
        return leaveTempRepo.findAll();
    }

    @Override
    public LeavesTemp addLeave(Leave leave, int empId) throws EmployeeIdNotFoundException, LeaveFoundException {
        LeavesTemp leaveTempObj = getLeaveTempByEmpId(empId);

        //the returned leave object is null exception is not handled here
        List<Leave> leaveListObj = leaveTempObj.getLeaveList();
        for(Leave leaveTemp:leaveListObj){
            if(leaveTemp.getLeaveId()==leave.getLeaveId()){
                throw new LeaveFoundException("A leave record with the given leave id is already present in the database. If you want to update the information please use appropriate API or modify the leave Id");
            }
        }
        leaveListObj.add(leave);
        leaveTempObj.setLeaveList(leaveListObj);
        return leaveTempRepo.save(leaveTempObj);
    }

    @Override
    public LeavesTemp addLeaveTemp(LeavesTemp leaveTemp) throws EmployeeFoundException {
        List<LeavesTemp> leaveHistoriesObj=getAllLeaveHistories();
        for(LeavesTemp leaveTempObj:leaveHistoriesObj){
            if(leaveTempObj.getEmpId()==leaveTemp.getEmpId()){
                throw new EmployeeFoundException("The Leave history record for the employee with the id"+leaveTemp.getEmpId()+"already exits in the database. Please verify the employee id or use API intended for Update operation");
            }
        }
        return leaveTempRepo.save(leaveTemp);
    }

    @Override
    public LeavesTemp getLeaveTempByEmpId(int empId) throws EmployeeIdNotFoundException {
        Optional<LeavesTemp> leaveTempOptional = leaveTempRepo.findById(empId);
        return leaveTempOptional.orElseThrow(() -> new EmployeeIdNotFoundException("No record found in LeaveHistory Database with Employee Id:" + empId));
    }

    @Override
    public LeavesTemp updateLeaveById(Leave leave, int empId) throws EmployeeIdNotFoundException, LeaveNotFoundException {
        Optional<LeavesTemp> leaveTempOptional = leaveTempRepo.findById(empId);
        if(leaveTempOptional.isPresent()) {
            try{
                LeavesTemp leaveTempObj=leaveTempOptional.get();
                //update the leaves properly
                for(Leave leaveObj: leaveTempObj.getLeaveList()){
                    if(leaveObj.getLeaveId()==leave.getLeaveId()){

                    }
                }
                leaveTempObj.getLeaveList().set(leave.getLeaveId(),leave);
                return leaveTempRepo.save(leaveTempObj);}
            catch(Exception e){
                System.out.println(e.toString());
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
                throw new LeaveNotFoundException("No leave with the given leave id"+leave.getLeaveId()+" is found for the Employee with the given employee id"+empId);
            }
        }
        throw new EmployeeIdNotFoundException("No record found in LeaveHistory Database with Employee Id:" + empId);
    }

    @Override
    public LeavesTemp updateLeaveTemp(LeavesTemp leaveTemp) throws EmployeeIdNotFoundException {
        int empId=leaveTemp.getEmpId();
        Optional<LeavesTemp> leaveTempOptional = leaveTempRepo.findById(empId);
        if(leaveTempOptional.isPresent()) {
            return leaveTempRepo.save(leaveTemp);
        }
        throw new EmployeeIdNotFoundException("No record found in LeaveHistory Database with Employee Id"+ empId +"present in the provided information:" );
    }


    @Override
    public void deleteLeaveHistoryByEmpIdVoidReturn(int empId) throws EmployeeIdNotFoundException {
        Optional<LeavesTemp> leaveTempOptional = leaveTempRepo.findById(empId);
        if(leaveTempOptional.isPresent()) {
            leaveTempRepo.deleteById(empId);
            return;
        }
        throw new EmployeeIdNotFoundException("No record found in LeaveHistory Database with Employee Id:" + empId);
    }

    @Override
    public LeavesTemp deleteLeaveHistoryByEmpId(int empId) throws EmployeeIdNotFoundException {
        Optional<LeavesTemp> leaveTempOptional = leaveTempRepo.findById(empId);
        if(leaveTempOptional.isPresent()) {
            leaveTempRepo.deleteById(empId);
            return leaveTempOptional.get();
        }
        throw new EmployeeIdNotFoundException("No record found in LeaveHistory Database with Employee Id:" + empId);
    }
}
