package com.interview.ct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CTCoindeskDTO {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private OffsetDateTime updateTime;

    private List<CTCurrency> list;

    public String getUpdateTime(){
        return dateFormat.withZone(ZoneId.systemDefault()).format(updateTime);
    }

    public void setUpdateTime(String isoDate){
        this.updateTime = OffsetDateTime.parse(isoDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @ToString
    @Getter @Setter
    @NoArgsConstructor
    public static class CTCurrency{
        private String name;

        private String nameZh;

        private BigDecimal rate;
    }

    public Date pickUpdateTimeAsDate(){
        return new Date(this.updateTime.toInstant().toEpochMilli());
    }
}
