package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.*;
import cn.ganxy03.xh_website.repository.*;
import cn.ganxy03.xh_website.service.ShetuanApplyService;
import cn.ganxy03.xh_website.service.ShetuanManagerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShetuanApplyImpl implements ShetuanApplyService {
    private final ShetuanManagerService shetuanManagerService;
    private final ShetuanApplyRepository shetuanApplyRepository;
    private final ShetuanLoginRepository shetuanLoginRepository;
    private final ShetuanUserRepository shetuanUserRepository;
    private final ShetuanMassRepository shetuanMassRepository;
    private final ShetuanDepartmentRepository shetuanDepartmentRepository;

    public ShetuanApplyImpl(ShetuanManagerService shetuanManagerService, ShetuanApplyRepository shetuanApplyRepository, ShetuanLoginRepository shetuanLoginRepository, ShetuanUserRepository shetuanUserRepository, ShetuanMassRepository shetuanMassRepository, ShetuanDepartmentRepository shetuanDepartmentRepository) {
        this.shetuanManagerService = shetuanManagerService;
        this.shetuanApplyRepository = shetuanApplyRepository;
        this.shetuanLoginRepository = shetuanLoginRepository;
        this.shetuanUserRepository = shetuanUserRepository;
        this.shetuanMassRepository = shetuanMassRepository;
        this.shetuanDepartmentRepository = shetuanDepartmentRepository;
    }

    @Override
    public Integer PostApplication(String sno, String mass, String department, String info) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        if(shetuanLogin != null) {
            ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
            if(shetuanApply == null) {
                ShetuanApply shetuanApply1 = new ShetuanApply();
                shetuanApply1.setSno(sno);
                shetuanApply1.setMass(mass);
                shetuanApply1.setDepartment(department);
                shetuanApply1.setInfo(info);
                shetuanApply1.setPosition("成员");
                shetuanApply1.setApply_time(LocalDateTime.now());
                shetuanApplyRepository.save(shetuanApply1);
                return 1;
            } else if(shetuanApply.getStatus() == 1){
//                存在且status = 1 即审通过的
                return 2;
            } else {
//                存在但未审核通过
                return 3;
            }
        } else {
//            找不到sno
            return 4;
        }
    }

    @Override
    public Integer UpdateApplication(String sno, String mass, String department, String info) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        if(shetuanLogin != null) {
            ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
            if(shetuanApply != null) {
                shetuanApply.setSno(sno);
                shetuanApply.setMass(mass);
                shetuanApply.setDepartment(department);
                shetuanApply.setInfo(info);
                shetuanApply.setPosition("成员");
                shetuanApply.setStatus(0);
                shetuanApply.setUpdate_time(LocalDateTime.now());
                shetuanApplyRepository.save(shetuanApply);
                return 1;
            }  else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    @Override
    public Integer AgreeApplication(String sno, String mass, String department) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        if(shetuanLogin != null) {
            ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
            ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
            if (shetuanDepartment != null) {
                shetuanDepartment.setNums((shetuanDepartment.getNums() + 1));
                shetuanDepartmentRepository.save(shetuanDepartment);
            }
            if(shetuanApply != null) {
                shetuanApply.setStatus(1);
                shetuanApply.setOperate_time(LocalDateTime.now());
                shetuanApplyRepository.save(shetuanApply);

                ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
                ShetuanMass shetuanMass = shetuanMassRepository.findByName(mass);
                String member = shetuanMass.getMember();
                if (member == null || member.isEmpty()) {
                    member = String.valueOf(shetuanUser.getId());
                } else {
                    member += "," + shetuanUser.getId();
                }
                shetuanMass.setMember(member);

                shetuanMassRepository.save(shetuanMass);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    @Override
    public Integer RejectApplication(String mass, String sno) {
        ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
        if(shetuanApply != null) {
            shetuanApply.setStatus(2);
            shetuanApply.setOperate_time(LocalDateTime.now());
            shetuanApplyRepository.save(shetuanApply);
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public List<ShetuanApply> GetWaitAgree(String mass) {
        List<ShetuanApply> shetuanApplies =shetuanApplyRepository.findAllByMassAndStatus(mass, 0);
        List<ShetuanApply> mergedList = new ArrayList<>();
        for (ShetuanApply shetuanApply : shetuanApplies) {
            String sno = shetuanApply.getSno();
            ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);

            // 将查询到的数据与ShetuanApply对象合并
            shetuanApply.setShetuanUser(shetuanUser);

            // 将合并后的对象添加到新的列表中
            mergedList.add(shetuanApply);
        }
        return mergedList;
    }

    @Override
    public Integer GetStatus(String sno) {
        ShetuanApply shetuanApply = shetuanApplyRepository.findBySno(sno);
        return shetuanApply.getStatus();
    }

    @Override
    public List<ShetuanApply> GetAllPassByMass(String mass) {
        List<ShetuanApply> shetuanApplies =shetuanApplyRepository.findAllByMassAndStatus(mass, 1);
        List<ShetuanApply> mergedList = new ArrayList<>();
        for (ShetuanApply shetuanApply : shetuanApplies) {
            String sno = shetuanApply.getSno();
            ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);

            // 将查询到的数据与ShetuanApply对象合并
            shetuanApply.setShetuanUser(shetuanUser);

            // 将合并后的对象添加到新的列表中
            mergedList.add(shetuanApply);
        }
        return mergedList;
    }

    @Override
    public boolean UpdateInfo(String mass, String sno, String beforeDepartment, String department, String position) {
        ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, beforeDepartment);
        if (shetuanDepartment != null) {
            shetuanDepartment.setNums((shetuanDepartment.getNums() - 1));
            shetuanDepartmentRepository.save(shetuanDepartment);
        }
        ShetuanDepartment shetuanDepartment1 = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
        if (shetuanDepartment1 != null) {
            shetuanDepartment1.setNums((shetuanDepartment1.getNums() + 1));
            shetuanDepartmentRepository.save(shetuanDepartment);
        }

        ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
        shetuanApply.setDepartment(department);
        shetuanApply.setPosition(position);
        shetuanApply.setOperate_time(LocalDateTime.now());
        shetuanApplyRepository.save(shetuanApply);
        return true;
    }

//    @Override
//    public boolean DeleteMember(String mass, String sno, String department) {
//        ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
//        ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
//        if (shetuanDepartment != null) {
//            shetuanDepartment.setNums((shetuanDepartment.getNums() - 1));
//            shetuanDepartmentRepository.save(shetuanDepartment);
//        }
//        if(shetuanApply != null) {
//            shetuanApplyRepository.delete(shetuanApply);
//        }
//        return true;
//    }
    @Override
    public boolean DeleteMember(String mass, String sno, String department) {
        ShetuanApply shetuanApply = shetuanApplyRepository.findByMassAndSno(mass, sno);
        if(shetuanApply != null) {
            ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);

            ShetuanDepartment shetuanDepartment = shetuanDepartmentRepository.findAllByMassAndDepartment(mass, department);
            if (shetuanDepartment != null) {
                shetuanDepartment.setNums((shetuanDepartment.getNums() - 1));
                shetuanDepartmentRepository.save(shetuanDepartment);
            }
            ShetuanMass shetuanMass = shetuanMassRepository.findByName(mass);
            String member = shetuanMass.getMember();
            if (member != null && !member.isEmpty()) {
                String[] memberIds = member.split(",");
                StringBuilder updatedMember = new StringBuilder();
                for (String memberId : memberIds) {
                    if (!memberId.equals(String.valueOf(shetuanUser.getId()))) {
                        if (updatedMember.length() > 0) {
                            updatedMember.append(",");
                        }
                        updatedMember.append(memberId);
                    }
                }
                shetuanMass.setMember(updatedMember.toString());
            }
            shetuanMassRepository.save(shetuanMass);

            shetuanApplyRepository.delete(shetuanApply);
            return true;
        }
        return false;
    }


    @Override
    public List<ShetuanApply> GetAllJoinMass(String sno) {
        List<ShetuanApply> shetuanApplies = shetuanApplyRepository.findAllBySnoAndStatus(sno, 1);
        return shetuanApplies;
    }


    @Override
    public List<ShetuanApply> GetAllCheckBySno(String sno) {
        List<ShetuanApply> shetuanApplies = shetuanApplyRepository.findAllBySno(sno);
        return shetuanApplies;
    }
}
