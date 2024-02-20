package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.DepartmentNode;
import cn.ganxy03.xh_website.antity.ShetuanDepartment;
import cn.ganxy03.xh_website.repository.ShetuanDepartmentRepository;
import cn.ganxy03.xh_website.service.ShetuanDepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShetuanDepartmentImpl implements ShetuanDepartmentService {
    private final ShetuanDepartmentRepository shetuanDepartmentRepository;
    public ShetuanDepartmentImpl(ShetuanDepartmentRepository shetuanDepartmentRepository) {
        this.shetuanDepartmentRepository = shetuanDepartmentRepository;
    }
//    @Override
//    public List<DepartmentNode> GetAllDepartmentByMass(String mass) {
//        List<ShetuanDepartment> shetuanDepartments = shetuanDepartmentRepository.findAllByMass(mass);
//        // 创建根节点
//        DepartmentNode rootNode = new DepartmentNode();
//        rootNode.setLabel(mass);
//        rootNode.setPriority(1);
//        rootNode.setNums(0);
//        rootNode.setChildren(new ArrayList<>());
//        // 遍历并转换部门列表
//        for (ShetuanDepartment department : shetuanDepartments) {
//            DepartmentNode childNode = new DepartmentNode();
//            childNode.setLabel(department.getDepartment());
//            childNode.setPriority(department.getPriority());
//            childNode.setNums(0);
//            // 将子节点添加到根节点的子节点列表中
//            rootNode.getChildren().add(childNode);
//        }
//        List<DepartmentNode> result = new ArrayList<>();
//        result.add(rootNode);
//        return result;
//    }
    @Override
    public List<DepartmentNode> GetAllDepartmentByMass(String mass) {
        List<ShetuanDepartment> shetuanDepartments = shetuanDepartmentRepository.findAllByMass(mass);
        // 创建根节点
        DepartmentNode rootNode = new DepartmentNode();
        int totalNums = 0;
        rootNode.setLabel(mass);
        rootNode.setPriority(1);
        rootNode.setChildren(new ArrayList<>());
        // 遍历并转换部门列表
        for (ShetuanDepartment department : shetuanDepartments) {
            DepartmentNode childNode = new DepartmentNode();
            childNode.setLabel(department.getDepartment());
            childNode.setPriority(department.getPriority());
            childNode.setNums(department.getNums());
            // 将子节点添加到根节点的子节点列表中，但只有在父节点与子节点标签不同时添加
            if (!rootNode.getLabel().equals(childNode.getLabel())) {
                rootNode.getChildren().add(childNode);
                totalNums += department.getNums();
            }
        }
        rootNode.setNums(totalNums);
        List<DepartmentNode> result = new ArrayList<>();
        result.add(rootNode);
        return result;
    }

    @Override
    public boolean CreateDepartment(String mass, String department) {
        ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
        if(shetuanDepartment != null) {
            return false;
        } else {
            ShetuanDepartment shetuanDepartment1 = new ShetuanDepartment();
            shetuanDepartment1.setMass(mass);
            shetuanDepartment1.setDepartment(department);
            shetuanDepartment1.setPriority(2);
            shetuanDepartmentRepository.save(shetuanDepartment1);
            return true;
        }
    }

    @Override
    public boolean DeleteDepartment(String mass, String department) {
        ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
        if(shetuanDepartment != null) {
            shetuanDepartmentRepository.delete(shetuanDepartment);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ShetuanDepartment> GetJustDepartmentByMass(String mass) {
        List<ShetuanDepartment> shetuanDepartments = shetuanDepartmentRepository.findAllByMass(mass);

        return shetuanDepartments;
    }
}
