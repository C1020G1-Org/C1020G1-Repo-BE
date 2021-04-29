package c1020g1.social_network.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "required")
    private String userName;

    @Column(name = "birthday", nullable = false)
    @NotNull(message = "required")
    private Date birthday;

    @Pattern(regexp = "^(male|female)$", message = "unexpected_value")
    @Column(name = "gender", nullable = false)
    @NotBlank(message ="required")
    private String gender;

    @Column(name = "occupation", nullable = false)
    @NotBlank(message = "required")
    private String occupation;

    @Column(name = "email", nullable = false)
    @Email(message = "wrong_format")
    @NotBlank(message = "required")
    private String email;

    @Column(name = "user_avatar")
    @NotBlank(message = "required")
    private String userAvatar;

    @Column(name = "user_background")
    @NotBlank(message = "required")
    private String userBackground;

    @Column(name = "marriaged", nullable = false)
    @NotBlank(message = "required")
    @Pattern(regexp = "^(single|married)$",message = "unexpected_value")
    private String marriaged;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id")
    @Valid
    @NotNull(message = "required")
    private Ward ward;

    @Column(name = "address")
    @NotBlank(message = "required")
    private String address;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id",nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id",nullable = false)
    private Account account;


    @OneToMany(mappedBy = "user")
    private Set<GroupRequest> groupRequests;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserBackground() {
        return userBackground;
    }

    public void setUserBackground(String userBackground) {
        this.userBackground = userBackground;
    }

    public String getMarriaged() {
        return marriaged;
    }

    public void setMarriaged(String marriaged) {
        this.marriaged = marriaged;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
