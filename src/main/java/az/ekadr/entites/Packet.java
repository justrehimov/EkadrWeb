package az.ekadr.entites;

import lombok.Data;

@Data
public class Packet {
    private Long id;
    private String packet_name;
    private Float price;
    private Integer count_ad;
    private String about;
    private Integer active;

}
