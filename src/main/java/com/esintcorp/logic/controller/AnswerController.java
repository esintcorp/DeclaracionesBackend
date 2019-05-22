package com.esintcorp.logic.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.Answer;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.AnswerRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class AnswerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("/getIvaAnswers")
    public List<Answer> getIvaAnswers(HttpServletRequest request) {
        System.out.println("** IVA ANSWERS ** " );
        User user = userRepository.findById((Long) request.getSession().getAttribute("UserID")).get();
        if (user != null) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = 1;
            c.set(year, month, day);
            int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            Date start = c.getTime();
            c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
            Date end = c.getTime();

            return answerRepository.findThisMonth(user, start, end);
        }
        return null;
    }
}
