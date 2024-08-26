package org.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CustomerDto extends BaseDto {
    private String name;
    private String gender;
    private CustomerType customerType;
    private AddressDto address;
    private String phoneNumber;
    private String email;
    private Date dateOfBirth;
}
