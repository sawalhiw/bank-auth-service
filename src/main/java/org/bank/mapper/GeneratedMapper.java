package org.bank.mapper;

import org.mapstruct.AnnotateWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@AnnotateWith(GeneratedMapper.class)
public @interface GeneratedMapper {
}
