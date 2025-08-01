package com.statoverflow.status.global.event;

import java.util.List;

import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;

public record UsersAttributeProgressEvent(List<UsersAttributeProgress> progresses) {
}
