package org.kaiLearn.spring.core.assignment1.javaConfig;

import org.springframework.stereotype.Component;

@Component
public class MSQLDataSource implements DataSource {
    @Override
    public String[] getEmails() {
        String[] emails ={
                "kyz@gmail.com",
                "xyz@gmail.com",
                "pyz@gmail.com",
        };
        return emails;
    }
}
