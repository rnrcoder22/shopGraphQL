package com.rnr.example.graphql.shop.main.controllers;

import com.rnr.example.graphql.shop.main.exceptions.ShopException;
import com.rnr.example.graphql.shop.main.model.Staff;
import com.rnr.example.graphql.shop.main.model.input.SalaryRange;
import com.rnr.example.graphql.shop.main.model.input.StaffInput;
import com.rnr.example.graphql.shop.main.services.StaffService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class StaffController {
    
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
 
 
    @QueryMapping(name="staffId")
    public Staff findStaffById(@Argument Integer id) {
        try {
            return staffService.findById(id);
        } catch(RuntimeException e){
            throw new ShopException("--> findById with errors",  e);
        }
    }

    /*
     * The querymapping's name property matches with what is defined in staff.graphqls file
     */
    
    @QueryMapping(name="staff")
    public List<Staff> findCurrentStaff() {
        return staffService.findCurrentStaff();
    }
    
    @QueryMapping(name="formerStaff")
    public List<Staff> findFormerStaff() {
        return staffService.findFormerStaff();
    }
    
    @QueryMapping(name="allStaff")
    public List<Staff> findAllStaff() {
        return staffService.findAll();
    }
    
    @MutationMapping(name="newStaff")
    public Staff createStaff(@Argument StaffInput staff) { 
        return staffService.createStaff(staff);
    }
    
    @QueryMapping(name="staffSalary")
    public List<Staff> getStaffBySalaryRange(@Argument(name="range") SalaryRange salaryRange) {
        return staffService.findStuffBySalaryRange(salaryRange);
    }
}
