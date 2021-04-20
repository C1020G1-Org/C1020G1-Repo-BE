package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "referent_account")
public class ReferentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referent_account_id")
    private int referenceAccountId;
    @Column(name = "referent_account_url")
    private String referenceAccountEmail;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;
    @Column(name = "email_referent")
    private String emailReferent;

    public int getReferenceAccountId() {
        return referenceAccountId;
    }

    public void setReferenceAccountId(int referenceAccountId) {
        this.referenceAccountId = referenceAccountId;
    }

    public String getReferenceAccountEmail() {
        return referenceAccountEmail;
    }

    public void setReferenceAccountEmail(String referenceAccountEmail) {
        this.referenceAccountEmail = referenceAccountEmail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmailReferent() {
        return emailReferent;
    }

    public void setEmailReferent(String emailReferent) {
        this.emailReferent = emailReferent;
    }
}
