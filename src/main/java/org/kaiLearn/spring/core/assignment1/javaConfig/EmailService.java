package org.kaiLearn.spring.core.assignment1.javaConfig;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private DataSource dataSource;

    public EmailService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

   void sendEmail(){
      String[] emails = dataSource.getEmails();

      for(String email: emails){
          System.out.println(email);
      }
    }
}
