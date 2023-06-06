package onlineretailsystem.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSenderService {
    @Value("${outgoingMailServer}")
    private String outgoingMailServer;

    RestTemplate restTemplate = new RestTemplate();
    @Data
    @AllArgsConstructor
    public class EmailSendRequest{
        private String toEmail;
        private String message;
    }
    public void sendEmail (String email, String message){
        restTemplate.postForLocation(outgoingMailServer, new EmailSendRequest(email,message));
    }
}

