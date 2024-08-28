package org.bank.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FeatureInfoDto {
    private String single;
    private String plural;
}
