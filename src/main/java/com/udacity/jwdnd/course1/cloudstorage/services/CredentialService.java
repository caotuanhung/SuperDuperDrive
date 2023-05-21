package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public boolean isValidCredentialInput(Credential credential, BindingResult bindingResult) {
        boolean result;
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ObjectError> concernErrors = allErrors.stream().filter(error ->
                ErrorMessage.URL_NOT_VALID.equals(error.getDefaultMessage()) ||
                        ErrorMessage.USERNAME_REQUIRED_MESSAGE.equals(error.getDefaultMessage())
                        || ErrorMessage.PASSWORD_REQUIRED_MESSAGE.equals(error.getDefaultMessage())).collect(Collectors.toList());
        result = concernErrors.isEmpty();
        return result;
    }

    public Integer saveOne(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credentialMapper.insertOne(credential);
    }

    public Credential findByUserIdAndId(Integer userId, Integer id) {
        Credential credential = credentialMapper.selectOneByUserIdAndId(userId, id);
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public Integer updateOne(Credential credential) {
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credentialMapper.updateOne(credential);
    }

    public Integer deleteOneByUserIdAndId(Integer userId, Integer id) {
        Integer numberDeleted = 0;
        Credential credential = credentialMapper.selectOneByUserIdAndId(userId, id);
        if (Objects.nonNull(credential)) {
            credentialMapper.deleteOneByUserIdAndId(userId, id);
            numberDeleted++;
        }
        return numberDeleted;
    }

    public List<Credential> findAllByUserIdOrderByIdDesc(Integer userId) {
        return credentialMapper.selectAllByUserIdOrderByIdDesc(userId);
    }

    public List<Credential> findAllByUsernameOrderByIdDesc(String username) {
        return credentialMapper.selectAllByUsernameOrderByIdDesc(username);
    }
}
