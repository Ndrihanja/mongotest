package com.ndrianja.mongotest.mfa.service.impl;

import com.ndrianja.mongotest.mfa.service.TotpManager;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotpManagerImpl implements TotpManager {

    private final SecretGenerator secretGenerator;
    private final QrGenerator qrGenerator;
    private final CodeVerifier myCodeVerifier;

    @Override
    public String generateSecretKey() {
        return secretGenerator.generate(); // 32 Byte Secret Key
    }

    @Override
    public String getQRCode(String secret) throws QrGenerationException {
        QrData qrData = new QrData.Builder().label("2FA Server")
                .issuer("Youtube 2FA Demo")
                .secret(secret)
                .digits(6)
                .period(30)
                .algorithm(HashingAlgorithm.SHA512)
                .build();

        return Utils.getDataUriForImage(qrGenerator.generate(qrData), qrGenerator.getImageMimeType());
    }

    @Override
    public boolean verifyTotp(String code, String secret) {
        return myCodeVerifier.isValidCode(secret, code);
    }
}
