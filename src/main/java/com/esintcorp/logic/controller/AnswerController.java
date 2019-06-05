package com.esintcorp.logic.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.domain.AnswerDTO;
import com.esintcorp.data.model.Answer;
import com.esintcorp.data.model.Bill;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.AnswerRepository;
import com.esintcorp.data.repository.BillRepository;
import com.esintcorp.data.repository.QuestionRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class AnswerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private BillRepository billRepository;

    @PostMapping("/getIvaAnswers")
    public List<AnswerDTO> getIvaAnswers(HttpServletRequest request) {
        User user = userRepository.findById((Long) request.getSession().getAttribute("UserID")).get();
        if (user != null) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = 1;
            c.set(year, month, day, 0, 0, 0);
            c.set(Calendar.MILLISECOND, 0);
            int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            Date start = c.getTime();

            c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth);
            Date end = c.getTime();

            List<Answer> thisMonthAnswers = answerRepository.findThisMonth(user, start, end);
            List<AnswerDTO> thisMonthAnswersDTO = new ArrayList<AnswerDTO>();

            for (Answer answer : thisMonthAnswers) {
                AnswerDTO answerDTO = new AnswerDTO(
                    answer.getQuestion().getId(),
                    answer.getBill().getId(),
                    answer.getQuestion().getBillType().getId(),
                    answer.getQuestion().getName(),
                    answer.getQuestion().getDatatype(),
                    answer.getValue()
                );
                thisMonthAnswersDTO.add(answerDTO);

            }
            return thisMonthAnswersDTO;
        }
        return null;
    }

    @Transactional
    @PostMapping("/saveAnswers")
    public Bill saveAnswers(@RequestBody List<AnswerDTO> answers, HttpServletRequest request) {
        System.out.println("** SAVE ANSWERS ** " + answers);

        Boolean sellBill = answers.get(0).getBillType_id() == 2;
        BigDecimal mutiplier = BigDecimal.ONE;
        if (!sellBill) {
            mutiplier = new BigDecimal(-1);
        }
System.out.println("iva: " + answers.get(5).getValue());
        BigDecimal iva = assignValue(answers.get(5).getValue());
        BigDecimal subtotal = assignValue(answers.get(4).getValue());
        BigDecimal total = assignValue(answers.get(6).getValue());
        User user = userRepository.getOne((Long) request.getSession().getAttribute("UserID"));

        Bill bill = new Bill();
        bill.setIva(iva.multiply(mutiplier));
        bill.setSubtotal(subtotal.multiply(mutiplier));
        bill.setTotal(total.multiply(mutiplier));
        bill.setUser(user);
        bill = billRepository.save(bill);

        for (AnswerDTO answerDTO : answers) {
            System.out.println("ANSWER: ** " + answerDTO);
            Answer answer = new Answer();
            answer.setValue(answerDTO.getValue());
            answer.setQuestion(questionRepository.getOne(answerDTO.getId()));
            answer.setUser(user);
            answer.setBill(bill);
            answer = answerRepository.save(answer);
        }

        return bill;
    }

    private BigDecimal assignValue(String value) {

        if (value != null && !value.isEmpty()) {
            return new BigDecimal(value);
        }
        return BigDecimal.ZERO;
    }
}
