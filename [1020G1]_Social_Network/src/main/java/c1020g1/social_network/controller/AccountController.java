package c1020g1.social_network.controller;

import c1020g1.social_network.model.Account;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.AccountService;
import c1020g1.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    /**
     * method: sent email to mail of user
     * author: HanTH
     *
     * @param id
     * @param code
     */
    @GetMapping("/account/{idAccount}/changePassword")
    public ResponseEntity<?> sendMailConfirmChangePassword(@PathVariable("idAccount") Integer id,
                                                           @RequestParam("code") Integer code) throws MessagingException {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Your new password is <i style='color: blue'>" + code + "<i></h3>" +
                "<h2><img src='https://apprecs.org/ios/images/app-icons/256/19/547702041.jpg'> C10tinder <h2>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(user.getEmail());

        helper.setSubject("C10Tinder Support Recover Password");


        emailSender.send(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * method: change password of user through account.
     * author: HanTH
     *
     * @param accountName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @PutMapping("/account/{accountName}/changePassword")
    public ResponseEntity<?> ChangePassword(@PathVariable("accountName") String accountName,
                                            @RequestParam("oldPassword") String oldPassword,
                                            @RequestParam("newPassword") String newPassword,
                                            @RequestParam("confirmPassword") String confirmPassword) {
        Account account = accountService.findAccountById(accountName);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        accountService.changePassword(account, oldPassword, newPassword, confirmPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
