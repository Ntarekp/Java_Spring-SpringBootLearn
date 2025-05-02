package org.kaiLearn.spring.core.assignment1.JavaConfig;

public class PostgreSQLDataSource implements DataSource{

    @Override
    public String[] getEmails(){
        String [] emails ={
                "email1@gmail.com",
                "email2@gmail.com",
                "em23@gmail.com"
        };

        return emails;
    }
}
