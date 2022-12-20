package ru.otus.buyer.controller;

import dto.BuyerRecord;
import dto.TicketRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.buyer.model.Ticket;
import ru.otus.buyer.service.BuyerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/buyer-info-save")
    public void saveUserData(@RequestBody BuyerRecord buyerRecord) {

        buyerService.saveUserInfo(buyerRecord);
    }

    @PostMapping("/ticket-info-save")
    public void saveTicketData(@RequestBody TicketRecord ticketRecord) {
        buyerService.saveTicketData(ticketRecord);
    }
}

