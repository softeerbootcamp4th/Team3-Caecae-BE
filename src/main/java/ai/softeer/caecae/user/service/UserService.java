package ai.softeer.caecae.user.service;

import ai.softeer.caecae.user.domain.dto.request.FindAndRegisterUserRequestDto;
import ai.softeer.caecae.user.domain.entity.User;
import ai.softeer.caecae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 휴대폰번호로 사용자를 조회하고, 없으면 등록하여 반환하는 로직
     *
     * @param req 휴대폰 번호
     * @return 등록된 유저의 userId
     */
    // 다른 서비스에서 사용해야 해서 엔티티를 반환하는게 맞을까...?
    public User findAndRegisterUser(FindAndRegisterUserRequestDto req) {
        Optional<User> optionalUser = userRepository.findByPhone(req.phone());
        return optionalUser.orElseGet(() -> registerUser(req.phone()));
    }


    /**
     * 휴대폰 번호로 유저를 등록하는 내부 로직
     *
     * @param phone 휴대폰 번호
     * @return 저장된 유저 엔티티
     */
    private User registerUser(String phone) {
        User user = User.builder()
                .phone(phone)
                .build();
        return userRepository.save(user);
    }

}
