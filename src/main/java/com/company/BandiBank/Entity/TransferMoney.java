package com.company.BandiBank.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transfermoney")
@Getter
@Setter
@NoArgsConstructor
public class TransferMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "from_num")
    private Long fromNum;
    @Column(name = "to_num")
    private Long toNum;
    @Column(name = "transfer_size")
    private int transferSize;
    @Column(name = "transfer_time")
    private Date transferTime = new Date();
    @Column(name = "from_name")
    private String fromName;
    @Column(name = "to_phone")
    private String toPhone;
    @Column(name = "comment")
    private String comment;

    public TransferMoney(Long fromNum, Long toNum, int transferSize, String fromName, String toPhone) {
        this.fromNum = fromNum;
        this.toNum = toNum;
        this.transferSize = transferSize;
        this.fromName = fromName;
        this.toPhone = toPhone;
    }
}
