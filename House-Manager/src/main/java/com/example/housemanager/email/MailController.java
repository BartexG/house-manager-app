package com.example.housemanager.email;

import com.example.housemanager.services.DwellerService;
import com.example.housemanager.services.HtmlPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private DwellerService dwellerService;

    @Autowired
    private HtmlPageService htmlPageService;

    @GetMapping("/sendMails")
    public String sendMails() throws MessagingException {
        try {
            List<String> emails = dwellerService.getEmails();
            for (int i = emails.size() - 1; i >= 0; i--) {
                if (emails.get(i) == null) {

                } else {
                    mailService.sendMail(emails.get(i), "Przypomnienie", "Witam,\nprzypominamy o zaległej zapłacie za czynsz. Prosimy o natychmiastową wpłatę, inaczej naliczone będą odsetki.\nTwoja ukochana wspólnota mieszkaniowa");
                }
            }
            return htmlPageService.emailsSend();
        } catch (Exception ex) {
            return htmlPageService.errorInMails();
        }
    }

}
