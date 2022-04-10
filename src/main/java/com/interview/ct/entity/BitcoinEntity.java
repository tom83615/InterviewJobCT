package com.interview.ct.entity;

import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bitcoin")
@ToString
@Getter @Setter
@NoArgsConstructor
public class BitcoinEntity {
    @Id
    @Column(nullable = false, length = 4)
    String name;

    @Column(name = "name_zh", nullable = false, length = 16)
    String nameZh = "未定義";

    @Column(nullable = false, precision=10, scale=4)
    BigDecimal rate;


    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.INSERT)
    @Column(name = "create_time", updatable = false, insertable = false, nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "update_time", insertable = false, nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    Date updateTime;
}