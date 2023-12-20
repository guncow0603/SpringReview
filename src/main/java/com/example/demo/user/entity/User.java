package com.example.demo.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="users")// 이 어노테이션은 엔티티 클래스가 매핑되는 데이터베이스 테이블의 이름을 지정합니다. 위의 코드에서는 "users" 테이블에 매핑되도록 지정되어 있습니다.
@AllArgsConstructor//Lombok에서 제공하는 어노테이션으로, 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해줍니다. 이 어노테이션이 붙은 클래스는 모든 필드를 사용하여 객체를 생성할 수 있는 생성자를 자동으로 생성합니다.
@NoArgsConstructor//Lombok에서 제공하는 어노테이션으로, 매개변수가 없는 기본 생성자를 자동으로 생성해줍니다. 이 어노테이션이 붙은 클래스는 매개변수가 없는 생성자를 자동으로 생성합니다.
public class User {

    @Id//이 어노테이션은 해당 필드가 엔티티의 주 식별자(primary key)임을 나타냅니다. JPA에서는 각 엔티티가 반드시 하나의 주 식별자를 가져야 합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//이 어노테이션은 주 식별자의 값을 자동으로 생성하는 전략을 지정합니다. GenerationType.IDENTITY는 데이터베이스의 자동 증가(Auto Increment) 컬럼을 이용하여 식별자 값을 생성하는 전략입니다. 이를 통해 데이터베이스가 자동으로 고유한 값을 생성하고 할당합니다.
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Builder
    private User(String username, String password,UserRoleEnum role){
        this.password=password;
        this.username=username;
        this.role=role;
    }
}
