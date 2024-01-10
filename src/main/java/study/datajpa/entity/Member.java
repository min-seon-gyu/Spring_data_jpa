package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"

)
@NamedEntityGraph(name = "Member.all", attributeNodes = {@NamedAttributeNode("team")})
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private int age;
    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name) {
        this.username = name;
    }

    public Member(String name, int age, Team team) {
        this.username = name;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.age = age;
        this.username = username;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    public void changeName(String name){
        this.username = name;
    }
}
