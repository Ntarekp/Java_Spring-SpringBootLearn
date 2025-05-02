package org.kaiLearn.spring.core.assignment1.JavaConfig;

public class MySQLDataSource implements DataSource{

    @Override
    public String[] getEmails() {

        String [] emails = {
                "ntkprince@gmail.com",
                "kyz@gmail.com",
                "tusht@gmail.com"
        };
        return emails;
    }
}
