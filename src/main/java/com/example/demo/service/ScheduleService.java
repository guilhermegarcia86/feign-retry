package com.example.demo.service;

import com.example.demo.model.Response;
import com.example.demo.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component("scheduleService")
public class ScheduleService {

    @Value("${cronTimer.reprocess}")
    private String reprocess;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private ResponseRepository repository;

    @Autowired
    private ConsultService consultService;

    @PostConstruct
    public void init() {
        taskScheduler.schedule(() -> {
            repository.findAllBySuccessIsFalse().ifPresent(cepList -> cepList.forEach(cep -> consultService.consultCep(cep.getCep())));
        }, new CronTrigger(reprocess));
    }
}
