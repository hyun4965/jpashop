package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //order의 Many to one 이랑 연결  order table에 있는 Member member..맵핑된 거울. 읽기전용
    private List<Order> orders = new ArrayList<Order>();
}
