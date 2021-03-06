package com.yalday.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(min = 4, max = 40)
    @Column(name = "login", length = 40, nullable = false)
    private String login;

    @NotNull
    @Size(min = 4, max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @NotNull
    @Size(min = 3, max = 40)
    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 40)
    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 4, max = 40)
    @Column(name = "email", length = 40, nullable = false)
    private String email;

    @NotNull
    @Column(name = "activated", nullable = false)
    private Boolean activated;

    //@Size(min = 4, max = 40)
    @Column(name = "lang_key", length = 40, nullable = false)
    private String langKey;

    @Size(min = 4, max = 40)
    @Column(name = "activation_key", length = 40, nullable = false)
    private String activationKey;

    @Size(min = 4, max = 40)
    @Column(name = "reset_key", length = 40, nullable = false)
    private String resetKey;

    @Column(name = "reset_date")
    private ZonedDateTime resetDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Authority> authorities;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_edited")
    private Timestamp lastEdited;
}
