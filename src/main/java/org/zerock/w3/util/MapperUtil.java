package org.zerock.w3.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

//DTO ↔ Entity 간 변환
public enum MapperUtil {

    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                // 필드 이름이 같으면 자동으로 매핑 가능하도록 설정
                // 기본적으로 ModelMapper는 getter/setter를 통해 매핑하지만, 이 옵션을 true로 설정하면 필드 자체를 기준으로 매핑
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) // private으로 선언된 필드도 접근 가능

                // Loose: 필드명이 일부 일치하면 매핑
                // Standard: 일반적인 매핑
                // Strict: 완전히 일치하는 필드만 매핑
                .setMatchingStrategy(MatchingStrategies.STRICT); // 필드명이 완전히 일치해야만 매핑
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

}
