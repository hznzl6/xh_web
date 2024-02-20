package cn.ganxy03.xh_website.antity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ai_conversation")
public class AiConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String usecode;
    private String uuid;
    private String initiator;


}
