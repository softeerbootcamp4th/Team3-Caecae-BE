package ai.softeer.caecae.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받은 엔티티가 아래 컬럼을 인식할 수 있음
@EntityListeners(AuditingEntityListener.class) // 자동으로 컬럼에 해당 값을 넣어주는 audit 기능
public abstract class BaseEntity {
    // 엔티티가 생성된 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 엔티티가 업데이트된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
