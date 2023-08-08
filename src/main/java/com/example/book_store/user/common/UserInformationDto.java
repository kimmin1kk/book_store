package com.example.book_store.user.common;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserInformationDto {
    private String name;
    private String nickname;
    private Grade grade;
    private Integer mileage;
    private Timestamp createdDate;
}
