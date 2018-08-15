package com.ximeo.zhivo365.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question implements Serializable {
    private static final long serialVersionUID = -6242110760472438895L;
    private Long id;
    private String customerName;
    private String phoneNumber;
    private String eMail;
    private String messageBody;
    private Customer customer;
    private Date createDate;
    private Date reviewDate;
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private int version;

    public Question() {
//        this.createDate = new Date();
    }

    public Question(String name, String phoneNumber, String eMail) {
        this.customerName = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.createDate = new Date();
    }

    public Question(String messageBody, String customerName) {
        this.messageBody = messageBody;
        this.customerName = customerName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "E_MAIL")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Column(name = "MESSAGE")
    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @ManyToOne()
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

//    @Transient
//    public String getCreateDateString() {
//        String createDateString = "";
//        if (createDate != null) {
//            createDateString = sdf.format(createDate);
//        }
//        return createDateString;
//    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REVIEW_DATE")
    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", customer=" + customer +
                ", createDate=" + createDate +
                ", reviewDate=" + reviewDate +
                '}';
    }
}