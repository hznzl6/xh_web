package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.DepartmentNode;
import cn.ganxy03.xh_website.antity.ShetuanDepartment;
import cn.ganxy03.xh_website.service.ShetuanDepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Shetaun-department")
public class ShetuanDepartmentController {

    private final ShetuanDepartmentService shetuanDepartmentService;
    public ShetuanDepartmentController(ShetuanDepartmentService shetuanDepartmentService) {
        this.shetuanDepartmentService = shetuanDepartmentService;
    }

    @PostMapping("getAllDepartmentByMass")
    public ResponseEntity<List> GetAllDepartmentByMass(@RequestParam String mass) {
        List<DepartmentNode> departmentNodes = shetuanDepartmentService.GetAllDepartmentByMass(mass);
        return new ResponseEntity<>(departmentNodes, HttpStatus.OK);
    }

    @PostMapping("createDepartment")
    public ResponseEntity<String> CreateDepartment(@RequestParam String mass, @RequestParam String department) {
        boolean isCreated = shetuanDepartmentService.CreateDepartment(mass, department);
        if(isCreated) {
            return new ResponseEntity<>("Created success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department have exited", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deleteDepartment")
    public ResponseEntity<String> DeleteDepartment(@RequestParam String mass, @RequestParam String department) {
        boolean isDelete = shetuanDepartmentService.DeleteDepartment(mass, department);
        if(isDelete) {
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getJustDepartmentByMass")
//    public ResponseEntity<List> DeleteDepartment(@RequestParam String mass) {
//        List<ShetuanDepartment> shetuanDepartments = shetuanDepartmentService.GetJustDepartmentByMass(mass);
//
//        return new ResponseEntity<>(shetuanDepartments, HttpStatus.OK);
//    }
    public ResponseEntity<List<String>> GetJustDepartmentByMass(@RequestParam String mass) {
        List<ShetuanDepartment> shetuanDepartments = shetuanDepartmentService.GetJustDepartmentByMass(mass);
        List<String> result = new ArrayList<>();
        for (ShetuanDepartment department : shetuanDepartments) {
            if (department.getPriority() == 2) {
                result.add(department.getDepartment());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
