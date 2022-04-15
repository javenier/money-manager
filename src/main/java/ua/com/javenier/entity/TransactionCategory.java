package ua.com.javenier.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean isIncome;
    @OneToMany(mappedBy = "transactionCategory", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Transaction> transactions;

    public TransactionCategory() {
        transactions = new HashSet<>();
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

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}