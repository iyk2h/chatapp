package com.beamo.chat;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "chat")
public class Chat {
    @Id
    private String id;
    private String msg;
    private String sender;
    private String receiver;

    private LocalDateTime createdAt;
}
