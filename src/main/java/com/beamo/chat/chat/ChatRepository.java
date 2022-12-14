package com.beamo.chat.chat;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat,String> {

    @Tailable //커서 계속 유지
    @Query("{'sender':?0,'receiver':?1}")
    Flux<Chat> mFindBySender(String sender, String receiver); // response 유지하면서 데이터 계속 보내기

    @Tailable //커서 계속 유지
    @Query("{'roomNum':?0}")
    Flux<Chat> mFindByRoomNum(Integer roomNum);
}
