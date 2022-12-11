package ru.otus.buyer.controller;

import dto.BuyerRecord;
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

    @GetMapping("/ticket-info-save")
    @ResponseBody
    public void saveTicketData(@RequestParam Ticket ticket) /*throws JsonMappingException, JsonProcessingException */{
        /*Ticket ticket = objectMapper.readValue(ticket, Ticket.class);
        return prod;*/
    }
}

