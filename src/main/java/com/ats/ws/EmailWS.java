package com.ats.ws;

import com.ats.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailWS {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    @CrossOrigin(origins = "*")
    public void sendEmail(@RequestParam("email") String email,
                          @RequestParam("jobTitle") String jobTitle,
                          @RequestParam("userFullName") String userFullName,
                          @RequestParam("result") String result) {
        emailService.sendEmailForJob(email, jobTitle, userFullName, result);
    }

    @GetMapping("/reply")
    @CrossOrigin(origins = "*")
    public void sendEmail(@RequestParam("jobId") int jobId,
                          @RequestParam("userId") int userId,
                          @RequestParam("contain") String contain) {
        emailService.sendReplyUserEmail(userId, jobId, contain);
    }


}
