package com.esintcorp.logic.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.domain.BillResume;
import com.esintcorp.data.model.Bill;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.BillRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class BillController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    @PostMapping("/getIvaTotals")
    public BillResume getIvaTotals(HttpServletRequest request) {
        System.out.println("** IVA TOTALS ** " );
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

            List<Bill> billes = billRepository.findThisMonth(user, start, end);
            BigDecimal sellTotal = BigDecimal.ZERO;
            BigDecimal buyTotal = BigDecimal.ZERO;
            BigDecimal paymentTotal = BigDecimal.ZERO;
            for (Bill bill : billes) {
                if (bill.getTotal().compareTo(BigDecimal.ZERO) != -1) {
                    sellTotal = sellTotal.add(bill.getTotal());
                } else {
                    buyTotal = buyTotal.add(bill.getTotal().multiply(new BigDecimal(-1)));
                }
                paymentTotal = paymentTotal.add(bill.getIva());
            }
            return new BillResume(buyTotal, sellTotal, paymentTotal);
        }
        return null;
    }
}
