package ai.softeer.caecae.global.entity;

import java.time.LocalDateTime;

//TODO : JPA 의존성 설정 후 Audit, CreatedAt 등 어노테이션 설정
public abstract class TimeStampEntity {
    // 엔티티가 생성된 시간
    private LocalDateTime createdAt;
    // 엔티티가 업데이트된 시간
    private LocalDateTime updatedAt;
}
