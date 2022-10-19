# Chat server

### 채팅방에 있는 값 가져오기
``` java
// GET http://localhost:3999/chat/roomNum/{roomNum}

// Controller
@GetMapping(value = "/chat/roomNum/{roomNum}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
    return chatRepository.mFindByRoomNum(roomNum)
            .subscribeOn(Schedulers.boundedElastic());
            
//Repository
@Tailable
@Query("{'roomNum':?0}")
Flux<Chat> mFindByRoomNum(Integer roomNum);
}
```

### 채팅방에 메세지 전송하기
``` java
// POST http://localhost:3999/chat 

// Controller
@PostMapping("/chat")
public Mono<Chat> setMsg(@RequestBody Chat chat){
    chat.setCreatedAt(LocalDateTime.now());
    return chatRepository.save(chat);
}

// Entity
Chat chat = {
        sender: username,
        roomNum: roomNum,
        msg: msgInput.value
}
```

### monggoDB 설정
`test> use chatdb`

```
switched to db chatdb
```

`chatdb> show collections`

```
chat
```

`chatdb> db.runCommand({convertToCapped: 'chat', size: 8192});`

```
{ ok: 1 }
```

db.runConmand 로 사이즈 설정은 Spring 에서 @Tailabl 사용시 버퍼사이즈가 작아서 오류나기때문에 버퍼 사이즈를 늘려주는 기능