package io.choerodon.platform.app.service.impl;

import io.choerodon.platform.app.service.OnlineUserC7nService;
import io.choerodon.platform.infra.mapper.AuditLoginC7nMapper;
import org.hzero.platform.app.service.OnlineUserService;
import org.hzero.platform.domain.entity.AuditLogin;
import org.hzero.platform.infra.mapper.AuditLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OnlineUserC7nServiceImpl implements OnlineUserC7nService {

    @Autowired
    OnlineUserService onlineUserService;

    @Autowired
    AuditLoginMapper auditLoginMapper;

    @Autowired
    AuditLoginC7nMapper auditLoginC7nMapper;

    @Override
    public Map<String, Integer> getCurrentCount() {
        Map<String, Integer> map = new HashMap<>();

        Date date = getDayStartCalendar().getTime();

        Integer numberOfVisitorsToday = auditLoginC7nMapper.getNumberOfVisitors(date, null);

        map.put("NumberOfVisitorsToday", numberOfVisitorsToday);
        map.put("OnlineCount", onlineUserService.countOnline());
        return map;
    }

    @Override
    public Map<String, Integer> getCurrentCountPerTwoHour() {
        Map<String, Integer> result = new HashMap<>();
        Map<String, Integer> timeAndCount = new HashMap<>();

        SimpleDateFormat hourDateFormat = new SimpleDateFormat("HH:00");

        Calendar dayStartCalendar = getDayStartCalendar();

        List<AuditLogin> auditLoginList = auditLoginC7nMapper.getAuditLoginBetweenTime(dayStartCalendar.getTime(), new Date());

        Map<String, List<AuditLogin>> timeAndLogin = auditLoginList.stream().collect(Collectors.groupingBy(
                auditLogin -> hourDateFormat.format(auditLogin.getLoginDate()
                )
        ));
        timeAndLogin.forEach((key, value) -> {
            List<AuditLogin> filter = value.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(AuditLogin::getUserId))), ArrayList::new));
            timeAndCount.put(key, filter.size());
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        for (int i = 0; i <= calendar.get(Calendar.HOUR_OF_DAY); i++) {
            String time = hourDateFormat.format(dayStartCalendar.getTime());
            Integer onlineCount = timeAndCount.get(time) == null ? 0 : timeAndCount.get(time);
            result.put(time, onlineCount);
            dayStartCalendar.add(Calendar.HOUR, +1);
        }
        return result;
    }

    // 获取当天的最开始时刻
    private Calendar getDayStartCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }
}
