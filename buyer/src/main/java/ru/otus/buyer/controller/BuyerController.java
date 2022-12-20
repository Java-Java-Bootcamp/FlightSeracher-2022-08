package ru.otus.buyer.controller;

import dto.BuyerRecordDto;
import dto.TicketRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.buyer.model.TicketModel;
import ru.otus.buyer.service.BuyerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/buyer-info-save")
    public void saveUserData(@RequestBody BuyerRecordDto buyerRecordDto) {
        buyerService.saveUserInfo(buyerRecordDto);
    }

    @PostMapping("/ticket-info-save")
    public void saveTicketData(@RequestBody TicketRecordDto ticketRecordDto) {
        buyerService.saveTicketData(ticketRecordDto);
    }

    @GetMapping("/tickets-info-show")
    public List<TicketRecordDto> getTicketData(/*@RequestParam Long usedId*/) {
        return buyerService.getTickets(/*usedId*/);
    }
}

