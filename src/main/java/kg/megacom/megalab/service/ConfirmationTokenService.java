package kg.megacom.megalab.service;

import kg.megacom.megalab.model.entity.ConfirmationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}
