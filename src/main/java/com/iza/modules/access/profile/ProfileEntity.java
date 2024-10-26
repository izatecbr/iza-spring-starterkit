package com.iza.modules.access.profile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tab_profile")
@Data
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String label;
    private String description;
    @ElementCollection
    @CollectionTable(schema = "public", name="tab_profile_role",
            joinColumns=@JoinColumn(name="profile")
    )
    @Column(name = "role")
    private List<String> roles;
}
