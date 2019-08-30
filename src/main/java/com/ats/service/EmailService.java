package com.ats.service;

import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.FeedBackRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;


@EnableAsync
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private FeedBackRepository feedBackRepository;

    String footer =
            "<p style='font-size:150%;font-family:verdana;'>" +
                    "<p><b><u>Nga&#768;y g&#432;&#777;i mail:</u>&ensp;&ensp;&ensp;" + new SimpleDateFormat("EEEE").format(new Date()) +
                    ", &ensp;" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "  </b></p>" +
                    "&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;C&ocirc;ng vi&ecirc;n ph&acirc;&#768;n m&ecirc;&#768;m Quang Trung," +
                    "Tan Chanh Hiep, Qu&acirc;&#803;n 12,Tha&#768;nh Ph&ocirc;&#769; Ho Chi Minh</b></p>" +
                    "<p>Thanks and Best Regards,</p>" +
                    "<p>Team 17</p>" +
                    "<p>To&#768;a nha&#768; Innovation,C&ocirc;ng vi&ecirc;n ph&acirc;&#768;n m&ecirc;&#768;m " +
                    "Quang Trung ,Qu&acirc;&#803;n 12,Tha&#768;nh Ph&ocirc;&#769; Ho Chi Minh</p>" +
                    "<p>Tel: (+84) 077 761 4243</p>" +
                    "<p>Mobi: (+84) 077 761 4243</p>" +
                    "<p>Email: jobboard.system@gmail.com</p>" +
                    "<p>Website: www.jobboard.com</p>" +
                    "<p>We Make it Happen. Better.</p></p>";

    private static final Logger LOGGER = LogManager.getLogger(EmailService.class);


    @Async
    public void sendEmailForJob(String email, String jobTitle, String userFullname, String result) {
        try {
            LOGGER.info("Begin SendEmail in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;
            String subject = "";
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");

            String confirm = "<p>Dear Mr/Ms.<b>" + userFullname + "</b>,</p>" +
                    "<p></p>" +
                    "<p>CV Cu&#777;a Ba&#803;n &#272;a&#771; &#272;&#432;&#417;&#803;c Ch&acirc;&#769;p Nh&acirc;&#803;n Va&#768;o C&ocirc;ng Vi&ecirc;&#803;c : <b>" + jobTitle + "</b></p>";

            String deny = "<p>Dear Mr/Ms.<b>" + userFullname + "</b></p>" +
                    "<p> </p>" +
                    "<p>CV cu&#777;a ba&#803;n &#273;a&#771; bi&#803; t&#432;&#768; ch&ocirc;&#769;i va&#768;o c&ocirc;ng vi&ecirc;&#803;c : <b>" + jobTitle + "</b></p>";

            String apply = "<p>Dear Mr/Ms.<b>" + userFullname + "</b></p>" +
                    "<p> </p>" +
                    "<p>B&#7841;n &#273;&atilde; &#7913;ng tuy&#7875;n th&agrave;nh c&ocirc;ng v&agrave;o c&ocirc;ng vi&#7879;c: " + jobTitle + "</b></p>";


            if (result.equals("confirm")) {
                message.setContent(confirm + footer, "text/html; charset=UTF-8");
                subject = "Confirm Mail";
            }
            if (result.equals("deny")) {
                message.setContent(deny + footer, "text/html; charset=UTF-8");
                subject = "Deny Mail";
            }
            if (result.equals("apply")) {
                message.setContent(apply + footer, "text/html; charset=UTF-8");
                subject = "Apply Mail";
            }

            helper.setTo(email);
            helper.setSubject(subject);

            this.javaMailSender.send(message);
            LOGGER.info("End SendEmail in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    @Async
    public void sendActiveUserEmail(String token, String email, boolean result) {
        try {
            LOGGER.info("Begin sendActiveUserEmail in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;
            String subject = "Complete your account registration";
            Users users = usersRepository.findAccountByToken(token);
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
            String welcome = "<p>Dear <b>" + users.getFullName() + "</b>,</p>" +
                    "<p>Ca&#769;m &#417;n ba&#803;n &#273;a&#771; ta&#803;o ta&#768;i khoa&#777;n ta&#803;i ATS.</p>";
            String confirm = "<p>&#272;&ecirc;&#777; ki&#769;ch hoa&#803;t ta&#768;i " +
                    "khoa&#777;n, xin ba&#803;n click va&#768;o &#273;&#432;&#417;&#768;ng link b&ecirc;n d&#432;&#417;&#769;i:</p> ";
//            EncrytedPasswordUtils encrytedPasswordUtils = new EncrytedPasswordUtils();
            String rand = generateRandomString(20);

            String emp = "<a href='http://localhost:8090/#/kiem-tra-thanh-cong/" + users.getId() + "." + rand + "'>Confirm your account</a></p>";
            String js = "<a href='http://localhost:8090/#/kiem-tra-thanh-cong-mail/" + users.getId() + "." + rand + "'>Confirm your account</a></p>";
            String end = "<p>Tr&acirc;n tro&#803;ng,</p><p> ATS Team</p>";
            if (result) {
                message.setContent(welcome + confirm + emp + end, "text/html; charset=UTF-8");
            }

            if (!result) {
                message.setContent(welcome + confirm + js + end, "text/html; charset=UTF-8");
            }
            helper.setTo(email);
            helper.setSubject(subject);
            this.javaMailSender.send(message);
            LOGGER.info("End sendActiveUserEmail in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Async
    public void sendAcceptUserEmail(String employerName, String email, String companyName, String result) {
        try {
            LOGGER.info("Begin sendAcceptUserEmail in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
            String subject = "";
            String welcome = "<p>Dear <b>" + employerName + "</b>,</p>";
            String confirm = "<p>Ba&#803;n &#273;a&#771; &#273;&#432;&#417;&#803;c ch&acirc;&#769;p nh&acirc;&#803;n va&#768;o c&ocirc;ng ty <b>"
                    + companyName + "</b></p>";
            String deny = "<p>Ba&#803;n &#273;a&#771; bi&#803; t&#432;&#768; ch&ocirc;&#769;i va&#768;o c&ocirc;ng ty <b>"
                    + companyName + "</b></p>";
            String end = "<p>Tr&acirc;n tro&#803;ng,</p><p> ATS Team</p>";

            if (result.equals("approved")) {
                message.setContent(welcome + confirm + end, "text/html; charset=UTF-8");
                subject = "Approved User Enter Company";
            }
            if (result.equals("deny")) {
                message.setContent(welcome + deny + end, "text/html; charset=UTF-8");
                subject = "Deny User Enter Company";
            }

            helper.setTo(email);
            helper.setSubject(subject);
            this.javaMailSender.send(message);
            LOGGER.info("End sendAcceptUserEmail in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Async
    public void sendEmailStatus(String email, String title, String userFullname, String result, String type) {
        try {
            LOGGER.info("Begin sendEmailStatus in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;
            String subject = "";
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
            String begin = "<p>Dear Mr/Ms.<b>" + userFullname + "</b>,</p><p></p>";
            String company = "<p>C&ocirc;ng ty :<b>" + title + "</b> &ensp;cu&#777;a ba&#803;n &#273;a&#771; ";
            String job = "<p>c&ocirc;ng vi&ecirc;&#803;c:<b>" + title + "</b>&ensp; cu&#777;a ba&#803;n &#273;a&#771; ";
            String account = "<p>Ta&#768;i khoa&#777;n cu&#777;a ba&#803;n &#273;a&#771; ";

            if (result.equals("approved") || result.equals("active") || result.equals("new")) {
                if (type.equals("company")) {
                    message.setContent(begin + company +
                            "&ensp;&#273;&#432;&#417;&#803;c &#273;&#432;a va&#768;o hoa&#803;t &#273;&ocirc;&#803;ng</p>" + footer, "text/html; charset=UTF-8");
                    subject = "Approved company mail";
                }
                if (type.equals("job")) {
                    message.setContent(begin + job
                            + "&#273;&#432;&#417;&#803;c duy&ecirc;&#803;t tha&#768;nh c&ocirc;ng</p>" + footer, "text/html; charset=UTF-8");
                    subject = "Approved job mail";
                }
                if (type.equals("user")) {
                    message.setContent(begin + account +
                            "&#273;&#432;&#417;&#803;c ta&#769;i ki&#769;ch hoa&#803;t</p>" + footer, "text/html; charset=UTF-8");
                    subject = "Approved user mail";
                }


            }
            if (result.equals("ban") || result.equals("approved ban")
                    || result.equals("active ban") || result.equals("new ban")) {
                if (type.equals("company")) {
                    message.setContent(begin + company +
                            "bi&#803; ch&#259;&#803;n</p>" + footer, "text/html; charset=UTF-8");
                    subject = "Ban company mail";
                }
                if (type.equals("job")) {
                    message.setContent(begin + job +
                            "bi&#803; ch&#259;&#803;n</p>" + footer, "text/html; charset=UTF-8");
                    subject = "Ban job mail";
                }
                if (type.equals("user")) {
                    message.setContent(begin + account +
                            "bi&#803; ch&#259;&#803;n</p> " + footer, "text/html; charset=UTF-8");
                    subject = "Ban user mail";
                }

            }


            helper.setTo(email);
            helper.setSubject(subject);

            this.javaMailSender.send(message);
            LOGGER.info("End sendEmailStatus in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    @Async
    public void sendReplyUserEmail(int userId, int jobId, String contain) {
        try {
            Users users = usersRepository.findOne(userId);
            Job job = jobRepository.findOne(jobId);
            LOGGER.info("Begin sendReplyUserEmail in EmailService with Email : " + users.getEmail());
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
            String subject = "Reply User Feedback";
            String welcome = "<p>Dear <b>" + users.getFullName() + "</b>,</p>";
            String auto = "<p>Chu&#769;ng t&ocirc;i &#273;a&#771; nh&acirc;&#803;n &#273;&#432;&#417;&#803;c Feedback cu&#777;a ba&#803;n v&ecirc;&#768; c&ocirc;ng vi&ecirc;&#803;c&ensp;" + job.getTitle() + "</p>";
            String end = "<p>Tr&acirc;n tro&#803;ng,</p><p> ATS Team</p>";
            if (contain.isEmpty()) {
                message.setContent(welcome + auto + end, "text/html; charset=UTF-8");
            }
            if (!contain.isEmpty()) {
                message.setContent(welcome + "<p>" + contain + "</p>" + end, "text/html; charset=UTF-8");
            }
            if (!contain.isEmpty()) {
                feedBackRepository.checkIsReply(userId, jobId);
            }

            helper.setTo(users.getEmail());
            helper.setSubject(subject);
            this.javaMailSender.send(message);

            LOGGER.info("End sendReplyUserEmail in EmailService with Email : " + users.getEmail());
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }


    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwasdasdxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }
}
