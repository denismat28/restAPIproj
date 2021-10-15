package com.task.restAPIproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passports")
public class PassportEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String number;

        @OneToOne(mappedBy = "passport", cascade = {
                CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                CascadeType.REFRESH
        })
        private UserEntity user;

}
