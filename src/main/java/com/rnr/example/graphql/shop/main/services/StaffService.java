package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Staff;
import com.rnr.example.graphql.shop.main.model.input.SalaryRange;
import com.rnr.example.graphql.shop.main.model.input.StaffInput;
import com.rnr.example.graphql.shop.main.repository.StaffMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final StaffMapper staffRepository;

    public StaffService(StaffMapper staffRepository) {
        this.staffRepository = staffRepository;
    }
    
    public Staff findById(Integer id) {
        return staffRepository.findById(id);
    }
    public List<Staff> findCurrentStaff() {
        return staffRepository.findCurrentStaff();
    }
    
    public List<Staff> findFormerStaff() {
        return staffRepository.findFormerStaff();
    }
    
    public List<Staff> findAll() { 
        return staffRepository.findAll(); 
    }
    
    public Staff createStaff(StaffInput staffInput) { 
        Staff staff = new Staff();
        staff.setSalary(staffInput.getSalary());
        staff.setFirstName(staffInput.getFirstName());
        staff.setLastName(staffInput.getLastName());
        staff.setHireDate(staffInput.getHireDate());
        int rows = staffRepository.insertStaff(staff);
        if(rows == 0){
            LOGGER.info("No staff was added. Staff {}", staff);
        }
        return staff;
    }

    public List<Staff> findStuffBySalaryRange(SalaryRange salaryRange) {
        return staffRepository.findStaffWithinSalary(salaryRange);
    }
}
