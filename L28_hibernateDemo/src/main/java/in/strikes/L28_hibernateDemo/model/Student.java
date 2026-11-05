package in.strikes.L28_hibernateDemo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "student_name",
            nullable = false,
            length =  100
    )
    private String name;

    @Column(
            unique = true,
            nullable = false,
            length = 150,
            insertable = true,
            updatable = true
    )
    private String email;

    private int age;

    @Embedded
    private Address addresses;

//    @ElementCollection
//    @CollectionTable(
//            name = "student_skills",
//            joinColumns = @JoinColumn(name = "student_id")
//    )
//    private Set<String> skills;


//    @Column(precision = 5, scale = 2)
//    private BigDecimal percentage;
//
//    private LocalDate dateOfBirth;
//
//    private LocalDateTime createdAt;
//
//    @Enumerated(EnumType.STRING)
//    private StudentStatus status;
//
//    @Lob
//    private String profileDescription;
//
//    @Transient
//    private String displayName;
//
//    @Convert(converter =  BooleanToStringConverter.class)
//    private  Boolean isMonitory;

    public Student() {
    }

    public Student(Long id, String name, String email, int age, Address addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }
}
