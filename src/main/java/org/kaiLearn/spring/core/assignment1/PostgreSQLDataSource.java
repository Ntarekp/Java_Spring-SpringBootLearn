package org.kaiLearn.spring.core.assignment1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostgreSQLDataSource implements DataSource{

    @Override
    public String[] getEmails() {
        String [] emails = {
                "kaiz2@gmail.com",
                "kyz4@gmail.com",
                "kai2@gmail.com",
        };
return emails;
    }
}
