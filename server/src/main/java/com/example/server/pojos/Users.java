/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.UniqueElements;

/**
 *
 * @author maidv
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByBgImage", query = "SELECT u FROM Users u WHERE u.bgImage = :bgImage"),
    @NamedQuery(name = "Users.findByPasswordResetToken", query = "SELECT u FROM Users u WHERE u.passwordResetToken = :passwordResetToken"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Users.findByStudentId", query = "SELECT u FROM Users u WHERE u.studentId = :studentId")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username", unique = true)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 255)
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @NotNull
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "bg_image")
    private String bgImage;
    @Size(max = 255)
    @Column(name = "password_reset_token")
    private String passwordResetToken;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "is_active")
    private Boolean isActive;
    @Size(max = 20)
    @Column(name = "role")
    private String role;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 255)
    @Column(name = "student_id")
    private String studentId;
    @JoinTable(name = "user_letter", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "letter_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Letters> lettersSet;
    @JoinTable(name = "survey_participant", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "survey_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Surveys> surveysSet;
    @ManyToMany(mappedBy = "usersSet")
    private Set<Groups> groupsSet;
    @OneToMany(mappedBy = "userId")
    private Set<Comments> commentsSet;
    @OneToMany(mappedBy = "userId")
    private Set<Surveys> surveysSet1;
    @OneToMany(mappedBy = "creatorId")
    private Set<Groups> groupsSet1;
    @OneToMany(mappedBy = "userId")
    private Set<Posts> postsSet;
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Majors majorId;
    @OneToMany(mappedBy = "userId")
    private Set<Shares> sharesSet;
    @OneToMany(mappedBy = "userId")
    private Set<Reactions> reactionsSet;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @XmlTransient
    public Set<Letters> getLettersSet() {
        return lettersSet;
    }

    public void setLettersSet(Set<Letters> lettersSet) {
        this.lettersSet = lettersSet;
    }

    @XmlTransient
    public Set<Surveys> getSurveysSet() {
        return surveysSet;
    }

    public void setSurveysSet(Set<Surveys> surveysSet) {
        this.surveysSet = surveysSet;
    }

    @XmlTransient
    public Set<Groups> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<Groups> groupsSet) {
        this.groupsSet = groupsSet;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Surveys> getSurveysSet1() {
        return surveysSet1;
    }

    public void setSurveysSet1(Set<Surveys> surveysSet1) {
        this.surveysSet1 = surveysSet1;
    }

    @XmlTransient
    public Set<Groups> getGroupsSet1() {
        return groupsSet1;
    }

    public void setGroupsSet1(Set<Groups> groupsSet1) {
        this.groupsSet1 = groupsSet1;
    }

    @XmlTransient
    public Set<Posts> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Posts> postsSet) {
        this.postsSet = postsSet;
    }

    public Majors getMajorId() {
        return majorId;
    }

    public void setMajorId(Majors majorId) {
        this.majorId = majorId;
    }

    @XmlTransient
    public Set<Shares> getSharesSet() {
        return sharesSet;
    }

    public void setSharesSet(Set<Shares> sharesSet) {
        this.sharesSet = sharesSet;
    }

    @XmlTransient
    public Set<Reactions> getReactionsSet() {
        return reactionsSet;
    }

    public void setReactionsSet(Set<Reactions> reactionsSet) {
        this.reactionsSet = reactionsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.server.pojos.Users[ id=" + id + " ]";
    }
    
}