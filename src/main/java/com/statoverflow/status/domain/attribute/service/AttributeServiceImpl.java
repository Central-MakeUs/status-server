package com.statoverflow.status.domain.attribute.service;

import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final UsersAttributeProgressRepository usersAttributeProgressRepository;

    @Override
    public List<AttributesReturnDto> getAttributes(Long userId) {
        List<UsersAttributeProgress> attributes = usersAttributeProgressRepository.findByUserId(userId);

        return attributes.stream()
                .map(AttributesReturnDto::fromEntity)
                .collect(Collectors.toList());
    }
}
