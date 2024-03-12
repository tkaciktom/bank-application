package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "processed_messages")
@Getter
@Setter
public class ProcessedMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String messageId;

}
