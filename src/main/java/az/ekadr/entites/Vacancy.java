package az.ekadr.entites;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class Vacancy{

    private Long id;

    private String vacancyName;

    private String information;

    private String requirements;

    private String salary;

    private String address;

    private Date dataDate;

    private Date expDate;

    private Blob logo;

    private Integer active;

    private Age ageId;

    private Category categoryId;

    private Company companyId;

    private Education educationId;

    private Experience experienceId;

    private Workmode workmodeId;

}
