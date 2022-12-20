package dto;

import org.springframework.data.annotation.Id;

public record BuyerRecordDto(@Id Long id, String firstName, boolean isBot, String lastName, String userName) {
}
