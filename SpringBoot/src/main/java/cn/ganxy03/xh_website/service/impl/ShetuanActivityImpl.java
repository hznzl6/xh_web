package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanActivity;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanActivityRepository;
import cn.ganxy03.xh_website.repository.ShetuanUserRepository;
import cn.ganxy03.xh_website.service.ShetuanActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShetuanActivityImpl implements ShetuanActivityService{
    private final ShetuanActivityRepository shetuanActivityRepository;
    private final ShetuanUserRepository shetuanUserRepository;
    @Autowired
    public ShetuanActivityImpl(ShetuanActivityRepository shetuanActivityRepository, ShetuanUserRepository shetuanUserRepository) {
        this.shetuanActivityRepository = shetuanActivityRepository;
        this.shetuanUserRepository = shetuanUserRepository;
    }

    @Override
    public boolean ApplyEvent(String mass, String event, String describe, String person, String phone, String expenditure, String start_time, String end_time, String mark) {
//        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
//        if(shetuanActivity != null) {
//            shetuanActivity.setMass(mass);
//            shetuanActivity.setEvent(event);
//            shetuanActivity.setDescribe(describe);
//            shetuanActivity.setPerson(person);
//            shetuanActivity.setPhone(phone);
//            shetuanActivity.setExpenditure(expenditure);
//            shetuanActivity.setStart_time(start_time);
//            shetuanActivity.setEnd_time(end_time);
//            shetuanActivity.setUpdate_time(LocalDateTime.now());
//            shetuanActivity.setStatus(0);
//            shetuanActivity.setMark(mark);
//            shetuanActivityRepository.save(shetuanActivity);
//            return false;
//        } else {
//            ShetuanActivity shetuanActivity1 = new ShetuanActivity();
//            shetuanActivity1.setMass(mass);
//            shetuanActivity1.setEvent(event);
//            shetuanActivity1.setDescribe(describe);
//            shetuanActivity1.setPerson(person);
//            shetuanActivity1.setPhone(phone);
//            shetuanActivity1.setExpenditure(expenditure);
//            shetuanActivity1.setStart_time(start_time);
//            shetuanActivity1.setEnd_time(end_time);
//            shetuanActivity1.setCreate_time(LocalDateTime.now());
//            shetuanActivity1.setStatus(0);
//            shetuanActivity1.setMark(mark);
//            shetuanActivityRepository.save(shetuanActivity1);
//            return false;
//        }
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        ShetuanActivity targetActivity;
        boolean isCreate = false;
        if (shetuanActivity != null) {
            targetActivity = shetuanActivity;
            targetActivity.setUpdate_time(LocalDateTime.now());
        } else {
            targetActivity = new ShetuanActivity();
            targetActivity.setCreate_time(LocalDateTime.now());
            isCreate = true;
        }
        targetActivity.setMass(mass);
        targetActivity.setEvent(event);
        targetActivity.setDescribes(describe);
        targetActivity.setPerson(person);
        targetActivity.setPhone(phone);
        targetActivity.setExpenditure(expenditure);
        targetActivity.setStart_time(start_time);
        targetActivity.setEnd_time(end_time);
        targetActivity.setStatus(0);
        targetActivity.setMark(mark);
        shetuanActivityRepository.save(targetActivity);
        return isCreate;
    }

    @Override
    public boolean AgreeEvent(String mass, String event) {
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        shetuanActivity.setStatus(1);
        shetuanActivityRepository.save(shetuanActivity);
        return true;
    }

    @Override
    public boolean RejectEvent(String mass, String event) {
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        shetuanActivity.setStatus(2);
        shetuanActivityRepository.save(shetuanActivity);
        return true;
    }

    @Override
    public boolean OverEvent(String mass, String event) {
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        if(shetuanActivity != null) {
            shetuanActivity.setStatus(1);
            shetuanActivityRepository.save(shetuanActivity);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<ShetuanActivity> GetActiveEvent(String mass) {
        List<ShetuanActivity> shetuanActivities  = shetuanActivityRepository.findAllByMassAndStatus(mass, 0);
        return shetuanActivities;
    }

    @Override
    public List<ShetuanActivity> findAllByMass(String mass) {
        List<ShetuanActivity> shetuanActivities = shetuanActivityRepository.findAllByMass(mass);
        return shetuanActivities;
    }

    @Override
    public List<ShetuanActivity> getAllActivity() {
        List<ShetuanActivity> shetuanActivities = shetuanActivityRepository.findAll();
        return shetuanActivities;
    }

    @Override
    public List<ShetuanActivity> getAllByMassAndStatus(String mass) {
        List<ShetuanActivity> shetuanActivities = shetuanActivityRepository.findAllByMassAndStatus(mass, 1);
        return shetuanActivities;
    }

    @Override
    public boolean SignEvent(String sno, String mass, String event) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        String sign = shetuanActivity.getSign();
        // 检查是否有指定的值
        if (sign.contains(String.valueOf(shetuanUser.getId()))) {
            return false;
        } else {
            if (sign == null || sign.isEmpty()) {
                sign = String.valueOf(shetuanUser.getId());
            } else {
                sign += "," + shetuanUser.getId();
            }
            shetuanActivity.setSign(sign);
            shetuanActivityRepository.save(shetuanActivity);
            return true;
        }
    }

    @Override
    public boolean ActualEvent(String mass, String event, String sno) {
        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        String actual = shetuanActivity.getActual();
        if (actual != null && actual.contains(String.valueOf(shetuanUser.getId()))) {
            return false;
        } else {
            if (actual == null || actual.isEmpty()) {
                actual = String.valueOf(shetuanUser.getId());
            } else {
                actual += "," + shetuanUser.getId();
            }
            shetuanActivity.setActual(actual);
            shetuanActivityRepository.save(shetuanActivity);
            return true;
        }
    }

//    @Override
//    public Integer YiActualEvent(String mass, String event, String snos) {
//        ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
//        List<ShetuanUser> shetuanUsers = Arrays.asList(snos.split(",")).stream()
//                .map(s -> shetuanUserRepository.getBySno(s))
//                .collect(Collectors.toList());
//        String actual = shetuanActivity.getActual();
//        if (actual != null && !actual.isEmpty()) {
//            List<String> ids = Arrays.asList(actual.split(","));
//            for (String id : ids) {
//                for (ShetuanUser user : shetuanUsers) {
//                    if (id.equals(String.valueOf(user.getId()))) {
//                        return 1;
//                    }
//                }
//            }
//        } else {
//            if (actual == null || actual.isEmpty()) {
//                actual = shetuanUsers.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
//            } else {
//                actual += "," + shetuanUsers.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
//            }
//            shetuanActivity.setActual(actual);
//            shetuanActivityRepository.save(shetuanActivity);
//            return 2;
//        }
//        return 3;
//    }
@Override
public Integer YiActualEvent(String mass, String event, String snos) {
    ShetuanActivity shetuanActivity = shetuanActivityRepository.getByMassAndEvent(mass, event);
    List<ShetuanUser> shetuanUsers = Arrays.asList(snos.split(",")).stream()
            .map(s -> shetuanUserRepository.getBySno(s))
            .collect(Collectors.toList());
    String actual = shetuanActivity.getActual();
    if (actual != null && !actual.isEmpty()) {
        List<String> ids = Arrays.asList(actual.split(","));
        for (String id : ids) {
            for (ShetuanUser user : shetuanUsers) {
                if (id.equals(String.valueOf(user.getId()))) {
                    return 1;
                }
            }
        }
        // 如果actual中不包含snos中的任何用户ID，将snos中的用户ID添加到actual中
        actual += "," + shetuanUsers.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
        shetuanActivity.setActual(actual);
        shetuanActivityRepository.save(shetuanActivity);
        return 2;
    } else {
        if (actual == null || actual.isEmpty()) {
            actual = shetuanUsers.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
        } else {
            actual += "," + shetuanUsers.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
        }
        shetuanActivity.setActual(actual);
        shetuanActivityRepository.save(shetuanActivity);
        return 2;
    }
}

}
