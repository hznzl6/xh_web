package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanCredit;
import cn.ganxy03.xh_website.antity.ShetuanCreditApply;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanCreditApplyRepository;
import cn.ganxy03.xh_website.repository.ShetuanCreditRepository;
import cn.ganxy03.xh_website.repository.ShetuanUserRepository;
import cn.ganxy03.xh_website.service.ShetuanCreditApplyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShetuanCreditApplyImpl implements ShetuanCreditApplyService {
    private final ShetuanCreditApplyRepository shetuanCreditApplyRepository;
    private final ShetuanUserRepository shetuanUserRepository;
    public ShetuanCreditApplyImpl(ShetuanCreditApplyRepository shetuanCreditApplyRepository, ShetuanUserRepository shetuanUserRepository) {
        this.shetuanCreditApplyRepository = shetuanCreditApplyRepository;
        this.shetuanUserRepository = shetuanUserRepository;
    }
    @Override
    public boolean ApplyCredit(String[] snos, String semester, String mass) {
        List<Integer> userIds = new ArrayList<>();
        for (String sno : snos) {
            // 使用 shetuanUserRepository.getByName() 获取对应的用户 ID
            ShetuanUser user = shetuanUserRepository.getBySno(sno);
            if (user != null) {
                userIds.add(user.getId());
            } else {
                // 处理未找到用户的情况
                System.out.println("无法找到用户：" + sno);
            }
        }
//        System.out.println(userIds);
        String userIdsStr = String.join(",", userIds.stream().map(Object::toString).collect(Collectors.toList()));

        ShetuanCreditApply shetuanCreditApply = shetuanCreditApplyRepository.findBySemesterAndMass(semester, mass);
        ShetuanCreditApply targetCreditApply;
        boolean isCreate = false;
        if(shetuanCreditApply != null) {
            targetCreditApply = shetuanCreditApply;
            targetCreditApply.setUpdate_time(LocalDateTime.now());
        } else {
            targetCreditApply = new ShetuanCreditApply();
            targetCreditApply.setApply_time(LocalDateTime.now());
            isCreate = true;
        }
        targetCreditApply.setSemester(semester);
        targetCreditApply.setMass(mass);
        targetCreditApply.setList(userIdsStr);
        targetCreditApply.setStatus(0);
        shetuanCreditApplyRepository.save(targetCreditApply);
        return isCreate;
    }

    @Override
    public void AddCredit(String[] snos, String semester, String mass) {
        List<Integer> userIds = new ArrayList<>();
        for (String sno : snos) {
            // 使用 shetuanUserRepository.getByName() 获取对应的用户 ID
            ShetuanUser user = shetuanUserRepository.getBySno(sno);
            if (user != null) {
                userIds.add(user.getId());
            } else {
                // 处理未找到用户的情况
                System.out.println("无法找到用户：" + sno);
            }
        }
        System.out.println(userIds);
        // 查找已存在的申请
        ShetuanCreditApply shetuanCreditApply = shetuanCreditApplyRepository.findBySemesterAndMass(semester, mass);
        if (shetuanCreditApply != null) {
            // 将新搜索到的 userIds 添加到已有的用户 ID 中
            String currentList = shetuanCreditApply.getList(); // 获取当前的用户 ID 字符串
            List<Integer> currentIds = Arrays.stream(currentList.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            currentIds.addAll(userIds);
            String updatedList = String.join(",", currentIds.stream().map(Object::toString).collect(Collectors.toList()));

            // 更新用户 ID 列表
            shetuanCreditApply.setList(updatedList);
            shetuanCreditApply.setUpdate_time(LocalDateTime.now());
            shetuanCreditApplyRepository.save(shetuanCreditApply);
        } else {
            // 如果申请不存在，可以根据需要进行特殊处理，比如报错或者创建新的申请等
            System.out.println("找不到对应的申请，无法添加用户 ID。");
        }
    }

    @Override
    public void RemoveCredit(String sno, String semester, String mass) {
        ShetuanUser user = shetuanUserRepository.getBySno(sno);
        if (user != null) {
            // 获取到要删除的用户ID
            int userId = user.getId();
            // 查找已存在的申请
            ShetuanCreditApply shetuanCreditApply = shetuanCreditApplyRepository.findBySemesterAndMass(semester, mass);
            if (shetuanCreditApply != null) {
                // 获取当前的用户ID列表
                String currentList = shetuanCreditApply.getList();
                List<String> currentIds = new ArrayList<>(Arrays.asList(currentList.split(",")));
                // 从当前用户ID列表中移除要删除的用户ID
                currentIds.remove(String.valueOf(userId));
                // 更新用户ID列表
                String updatedList = String.join(",", currentIds);
                // 更新申请的用户ID列表
                shetuanCreditApply.setList(updatedList);
                shetuanCreditApply.setUpdate_time(LocalDateTime.now());
                shetuanCreditApplyRepository.save(shetuanCreditApply);
            } else {
                System.out.println("找不到对应的申请，无法移除用户ID。");
            }
        } else {
            System.out.println("无法找到用户：" + sno);
        }
    }

    @Override
    public List<ShetuanCreditApply> GetItemByMass(String mass) {
        List<ShetuanCreditApply> shetuanCreditApplies = shetuanCreditApplyRepository.findAllByMass(mass);
        return shetuanCreditApplies;
    }

    @Override
    public Integer SearchId(String sno) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        return shetuanUser.getId();
    }

    @Override
    public String SearchBelong(String sno) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        return shetuanUser.getBelong();
    }

    @Override
    public List<ShetuanCreditApply> GetAllCredit() {
        List<ShetuanCreditApply> shetuanCreditApplies = shetuanCreditApplyRepository.findAll();
        return shetuanCreditApplies;
    }

    @Override
    public void PassCredit(String semester, String mass) {
        ShetuanCreditApply shetuanCreditApply = shetuanCreditApplyRepository.findBySemesterAndMass(semester, mass);
        shetuanCreditApply.setStatus(1);
        shetuanCreditApplyRepository.save(shetuanCreditApply);
    }

    @Override
    public void ReBackCredit(String semester, String mass) {
        ShetuanCreditApply shetuanCreditApply = shetuanCreditApplyRepository.findBySemesterAndMass(semester, mass);
        shetuanCreditApply.setStatus(0);
        shetuanCreditApplyRepository.save(shetuanCreditApply);
    }

    @Override
    public List<ShetuanUser> GetInfoById(String[] ids) {
        List<ShetuanUser> result = new ArrayList<>();
        for (String id : ids) {
            Optional<ShetuanUser> optionalUser = shetuanUserRepository.findById(Integer.parseInt(id));
            optionalUser.ifPresent(result::add);
            System.out.println(id);
        }
        return result;
    }

}
