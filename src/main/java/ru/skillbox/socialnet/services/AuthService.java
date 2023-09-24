
package ru.skillbox.socialnet.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnet.dto.PersonRs;
import ru.skillbox.socialnet.dto.response.CaptchaRs;
import ru.skillbox.socialnet.dto.response.CommonRsComplexRs;
import ru.skillbox.socialnet.dto.response.CommonRsPersonRs;
import ru.skillbox.socialnet.dto.response.ComplexRs;
import ru.skillbox.socialnet.entity.Person;
import ru.skillbox.socialnet.exception.ExceptionBadRq;
import ru.skillbox.socialnet.model.LoginRq;
import ru.skillbox.socialnet.repository.PersonRepository;
import ru.skillbox.socialnet.security.util.JwtTokenUtils;
import ru.skillbox.socialnet.util.ValidationUtilsRq;
import ru.skillbox.socialnet.util.mapper.PersonMapper;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    public final ValidationUtilsRq validationUtils;
    private final AccountService accountService;
    private final JwtTokenUtils jwtTokenUtils;
    private final PersonRepository personRepository;


    public CommonRsComplexRs<ComplexRs> logout(String authorization) {
        CommonRsComplexRs<ComplexRs> commonRsComplexRs = new CommonRsComplexRs<>();
        ComplexRs complexRs = new ComplexRs();
        commonRsComplexRs.setData(complexRs);
        commonRsComplexRs.setTimeStamp(new Date().getTime());
        SecurityContextHolder.clearContext();
        return commonRsComplexRs;
    }

    public CommonRsPersonRs<PersonRs> login(LoginRq loginRq) throws ExceptionBadRq {
        validationUtils.validationEmail(loginRq.getEmail());
        Person person = personRepository.findByEmail(loginRq.getEmail()).orElseThrow(
                () -> new ExceptionBadRq("Пользователь не найден"));
        String password = accountService.getDecodedPassword(person.getPassword());
        validationUtils.validationPassword(password, loginRq.getPassword());
        CommonRsPersonRs<PersonRs> commonRsPersonRs = new CommonRsPersonRs<>();
        PersonRs personRs = PersonMapper.INSTANCE.personToPersonRs(person, "", false);
        personRs.setToken(getToken(person));
        commonRsPersonRs.setData(personRs);
        commonRsPersonRs.setTimeStamp(new Date().getTime());
        return commonRsPersonRs;
    }

    public CaptchaRs captcha() {
//        GCage gCage = new GCage();
//        String token = gCage.getTokenGenerator().next();
        CaptchaRs captchaRs = new CaptchaRs();
//        captchaRs.setCode();
        return captchaRs;
    }

    private String getToken(Person person) {
        return jwtTokenUtils.generateToken(person);
    }

    private PersonRs getDataPersonRs(Person person, String token) {
        PersonRs personRs = new PersonRs();
//        personRs.setEmail(person.getEmail());
        personRs.setToken(token);
        return personRs;
    }
}

