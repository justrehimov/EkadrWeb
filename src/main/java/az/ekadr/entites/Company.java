package az.ekadr.entites;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class Company {

    private Long id;
    private String companyName;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String aboutCompany;
    private Blob logo;
    private Date dataDate;
    private Integer verified;
    private Float balance;
    private Integer active;
    private String website;
    private City cityId;
}
