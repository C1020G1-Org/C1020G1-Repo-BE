package c1020g1.social_network.controller;

import c1020g1.social_network.config.JwtTokenUtil;
import c1020g1.social_network.model.*;
import c1020g1.social_network.service.user.UserService;

import c1020g1.social_network.service.account.imp.JwtAccountDetailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

    @Value("${google.clienId}")
    String googleClientId;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtAccountDetailService jwtAccountDetailService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public UserService userService;

    /**
     * Method: Create authen
     * Author:Dương LQ
     * @param jwtRequest
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = login(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * Method: convert facebook access to jwt token
     * Author: DươngLQ
     * @param jwtResponseSocial
     * @return
     * @throws IOException
     */
    @PostMapping("oauth/google")
    public ResponseEntity<?> google(@RequestBody SocialResponse jwtResponseSocial) throws IOException {
        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder builder =
                new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(builder.getJsonFactory(), jwtResponseSocial.getToken());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        User newUser = userService.getUserByEmail(payload.getEmail());
        JwtResponse jwtResponse = new JwtResponse("");

        if (newUser == null) {
            newUser = new User();
            newUser.setEmail(payload.getEmail());
            jwtResponse.setUser(newUser);
            return ResponseEntity.ok(jwtResponse);
        }
        Account account = newUser.getAccount();
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(), account.getPassword());
        jwtResponse = loginSocial(jwtRequest);
        jwtResponse.setAccountName(account.getAccountName());

        newUser.setStatus(new Status(1,"Online"));
        jwtResponse.setUser(newUser);
        return ResponseEntity.ok(jwtResponse);
    }


    /**
     * Method: convert facebook access to jwt token
     * Author: DươngLQ
     * @param jwtResponseSocial
     * @return
     */
    @PostMapping("oauth/facebook")
    public ResponseEntity<?> facebook(@RequestBody SocialResponse jwtResponseSocial) {
        Facebook facebook = new FacebookTemplate(jwtResponseSocial.getToken());

        final String[] fields = {"email", "name"};
        org.springframework.social.facebook.api.User user = facebook
                .fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        User newUser = userService.getUserByEmail(user.getEmail());
        JwtResponse jwtResponse = new JwtResponse("");

        if (newUser == null) {
            newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setUserName(user.getName());
            jwtResponse.setUser(newUser);
            return ResponseEntity.ok(jwtResponse);
        }
        Account account = newUser.getAccount();
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(), account.getPassword());
        jwtResponse = loginSocial(jwtRequest);
        jwtResponse.setAccountName(account.getAccountName());
        newUser.setStatus(new Status(1,"Online"));
        jwtResponse.setUser(newUser);
        return ResponseEntity.ok(jwtResponse);
    }


    /**
     * Method: send email for user when revocer password
     * Author: DươngLQ
     * @param accountName
     * @return
     * @throws MessagingException
     */
    @GetMapping("/recover/{accountName}")
    public ResponseEntity<?> mailSender(@PathVariable("accountName") String accountName) throws MessagingException {
        Account account = jwtAccountDetailService.getAccount(accountName);

        if (account == null) {
            return new ResponseEntity<>("The account you received does not exist", HttpStatus.NOT_FOUND);
        }

        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        jwtAccountDetailService.update(accountName, generatedString);

        User user = account.getUser();


        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Your new password is <i>"+generatedString+"<i></h3>" +
                "<h2><img src='https://apprecs.org/ios/images/app-icons/256/19/547702041.jpg'> C10tinder <h2>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(user.getEmail());

        helper.setSubject("C10Tinder Support Recover Password");


        emailSender.send(message);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    /**
     * Method:create userDetails and create Jwt token
     * Author: DươngLQ
     * @param jwtRequest
     * @return
     */
    private JwtResponse loginSocial(JwtRequest jwtRequest) {
        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }


    /**
     * Method: catch exeption from method authenticate, if not exception create userDetails and create Jwt token
     * Author: DươngLQ
     * @param jwtRequest
     * @return
     */
    private JwtResponse login(JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getAccountName(), jwtRequest.getPassword());
        } catch (Exception e) {
            return new JwtResponse(e.getMessage());
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token);
        User user = jwtAccountDetailService.getAccount(jwtRequest.getAccountName()).getUser();
        user.setStatus(new Status(1,"Online"));
        jwtResponse.setUser(user);
        return jwtResponse;
    }


    /**
     * Method: check authenticate with authenticationManager throw exeption if exits
     * Author: DươngLQ
     * @param accountName
     * @param password
     * @throws Exception
     */
    private void authenticate(String accountName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
